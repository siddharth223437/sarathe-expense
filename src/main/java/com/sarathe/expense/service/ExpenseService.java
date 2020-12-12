package com.sarathe.expense.service;

import com.sarathe.expense.domain.Account;
import com.sarathe.expense.domain.Category;
import com.sarathe.expense.dto.AccountDto;
import com.sarathe.expense.dto.CategoryDto;
import com.sarathe.expense.repository.AccountRepository;
import com.sarathe.expense.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService extends SaratheAbstract {

    Logger log = LoggerFactory.getLogger(ExpenseService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AccountRepository accountRepository;

    public void addUpdateCategory(CategoryDto dto) throws Exception{
        Category category;
        if(dto.getCategoryId() == null){
            category = new Category();
        }else {
            category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
        }
        if(category == null){
            category = new Category();
        }
        category.setCategoryName(dto.getCategoryName());
        categoryRepository.save(category);
        log.info("category saved successfully ----- " + category.getId());
    }

    public List<CategoryDto> getAllCategories(){
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        categoryList.forEach(c -> {
            CategoryDto dto = new CategoryDto();
            dto.setCategoryName(c.getCategoryName());
            dto.setCategoryId(c.getId());
            categoryDtoList.add(dto);
        });
        return categoryDtoList;
    }

    public void deleteCategory(Long categoryId) throws Exception{
        categoryRepository.findById(categoryId).ifPresent(category -> categoryRepository.delete(category));
    }

   public void addUpdateAccount(AccountDto dto) throws Exception{
        Account account = null;
        if(dto.getAccountId() == null){
            account = new Account();
        }else{
            account = accountRepository.findById(dto.getAccountId()).orElse(null);
        }

        if(account == null){
            account = new Account();
        }
        account.setAccountName(dto.getAccountName());
        account.setUsers(getUser());
        accountRepository.save(account);
       log.info("category saved successfully ----- " + account.getId());
   }

   public List<AccountDto> getAllAccounts(){
       List<AccountDto> accountDtoList = new ArrayList<>();
        accountRepository.findAll().forEach(a -> {
            AccountDto accountDto = new AccountDto();
            accountDto.setAccountName(a.getAccountName());
            accountDto.setAccountId(a.getId());
            accountDtoList.add(accountDto);
        });
        return accountDtoList;
   }

    public void deleteAccount(Long accountId) throws Exception{
        accountRepository.findById(accountId).ifPresent(account -> accountRepository.delete(account));
    }
}
