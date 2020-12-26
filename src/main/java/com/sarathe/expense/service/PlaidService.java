package com.sarathe.expense.service;

import com.plaid.client.PlaidClient;
import com.plaid.client.request.AccountsGetRequest;
import com.plaid.client.request.CategoriesGetRequest;
import com.plaid.client.request.ItemPublicTokenExchangeRequest;
import com.plaid.client.request.TransactionsGetRequest;
import com.plaid.client.response.*;
import com.sarathe.expense.domain.Plaid;
import com.sarathe.expense.domain.PlaidAccount;
import com.sarathe.expense.domain.Users;
import com.sarathe.expense.dto.ExpenseDto;
import com.sarathe.expense.dto.PlaidDto;
import com.sarathe.expense.repository.PlaidAccountRepository;
import com.sarathe.expense.repository.PlaidRepository;
import com.sarathe.expense.repository.UsersRepository;
import com.sarathe.expense.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import retrofit2.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

@Service
public class PlaidService extends SaratheAbstract {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${plaid_clientid}")
    private String clientId;
    @Value("${plaid_secret}")
    private String secret;
    @Value("${plaid.baseUrl}")
    private String plaidBaseUrl;
    @Autowired
    private PlaidClient plaidClient;
    @Autowired
    private PlaidRepository plaidRepository;
    @Autowired
    private PlaidAccountRepository plaidAccountRepository;
    @Autowired
    private UsersRepository usersRepository;

    public Map findPlaidLinkToken(PlaidDto plaidDto) throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("client_id",clientId);
        map.put("secret",secret);
        map.put("client_name",plaidDto.getAppName());
        map.put("products",plaidDto.getProducts());
        List<String> lst = new ArrayList<>();
        lst.add("US");
        map.put("country_codes",lst);
        map.put("language","en");
        Map<String,String> userMap = new HashMap<>();
        userMap.put("client_user_id","sarathe@mail.usf.edu");
        map.put("user",userMap);

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(map);
        return restTemplate.postForObject(plaidBaseUrl+"/link/token/create", request, Map.class);
    }

    public void addNewPlaidAccount(PlaidDto plaidDto) throws Exception {
        Plaid plaidByAccessToken = plaidRepository.findPlaidByAccessToken(plaidDto.getCredentials().getAccessToken());
        if(plaidByAccessToken == null) {
            Response<ItemPublicTokenExchangeResponse> response = plaidClient.service()
                    .itemPublicTokenExchange(new ItemPublicTokenExchangeRequest(plaidDto.getCredentials().getPublicLinkToken())).execute();
            if (response.isSuccessful()) {
                String accessToken = response.body().getAccessToken();
                if(accessToken != null){
                    Plaid plaid = new Plaid();
                    plaid.setAccessToken(accessToken);
                    plaid.setInstitutionName(plaidDto.getInstitution().getInstitutionName());
                    plaid.setPlaidInstId(plaidDto.getInstitution().getPlaidInstitutionId());
                    plaid.setUsers(getUser());
                    plaidRepository.save(plaid);

                    plaidDto.getAccounts().forEach(a -> {
                        PlaidAccount plaidAccount = new PlaidAccount();
                        plaidAccount.setAccountName(a.getName());
                        plaidAccount.setSubtype(a.getSubtype());
                        plaidAccount.setType(a.getType());
                        plaidAccount.setPlaid(plaid);
                        plaidAccountRepository.save(plaidAccount);
                    });
                }
            }
        }
    }

    @Transactional
    public List<ExpenseDto> buildPlaidTransaction(ExpenseDto.ExpenseSearchDto expenseSearchDto) throws Exception{
        List<ExpenseDto> expenseDtos = new ArrayList<>();
        Date startDate = DateUtil.convert(expenseSearchDto.getFromDate());
        Date endDate = DateUtil.convert(expenseSearchDto.getToDate());
        Users user = usersRepository.findUsersByUsername(getUser().getUsername());
        BigDecimal total = BigDecimal.ZERO;
        for (Plaid p : user.getPlaids()) {
            TransactionsGetRequest request = new TransactionsGetRequest(p.getAccessToken(), startDate, endDate);
            Response<TransactionsGetResponse> executeResp = plaidClient.service().transactionsGet(request).execute();
            if(executeResp.isSuccessful()){
                for(TransactionsGetResponse.Transaction transaction : executeResp.body().getTransactions()){
                    if(!transaction.getName().contains("UNIV. OF IOWA DES")) {
                        ExpenseDto expenseDto = new ExpenseDto();
                        expenseDto.setDate(DateUtil.convert(transaction.getDate()));
                        expenseDto.setAmount(BigDecimal.valueOf(transaction.getAmount()));
                        expenseDto.setAccountName(p.getInstitutionName());
                        expenseDto.setCategoryName(transaction.getCategory().get(0));
                        expenseDto.setType(transaction.getName());

                        ExpenseDto.ExpenseSearchDto es = new ExpenseDto.ExpenseSearchDto();
                        es.setPlaidAccountId(transaction.getAccountId());
                        expenseDto.setExpenseSearch(es);
                        expenseDtos.add(expenseDto);

                        total = total.add(expenseDto.getAmount());
                    }
                }
            }
        }
        expenseDtos.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        return expenseDtos;
    }


    public List<PlaidDto.Account> findAllPlaidAccounts() throws Exception{
        List<PlaidDto.Account> accountList = new ArrayList<>();
        Users user = usersRepository.findUsersByUsername(getUser().getUsername());
        for(Plaid plaid : user.getPlaids()){
            Response<AccountsGetResponse> execute = plaidClient.service().accountsGet(new AccountsGetRequest(plaid.getAccessToken())).execute();
            if(execute.isSuccessful()){
                AccountsGetResponse accountsGetResponse = execute.body();
                for(Account account : accountsGetResponse.getAccounts()){
                    PlaidDto.Account acc = new PlaidDto.Account();
                    acc.setName(account.getName());
                    acc.setType(account.getType());
                    acc.setSubtype(account.getSubtype());
                    acc.setPlaidAccountId(account.getAccountId());
                    acc.setOfficialName(account.getOfficialName());
                    accountList.add(acc);
                }
            }
        }
        return accountList;
    }

}
