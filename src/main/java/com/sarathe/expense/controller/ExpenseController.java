package com.sarathe.expense.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sarathe.expense.dto.AccountDto;
import com.sarathe.expense.dto.CategoryDto;
import com.sarathe.expense.dto.ExpenseDto;
import com.sarathe.expense.dto.PlaidDto;
import com.sarathe.expense.service.ExpenseService;
import com.sarathe.expense.service.PlaidService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class ExpenseController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private PlaidService plaidService;

    @PostMapping("/category")
    public Map<String,String> addUpdateCategory(@RequestBody CategoryDto categoryDto){
        Map<String,String> map = new HashMap<>();
        if(categoryDto != null){
            try {
                expenseService.addUpdateCategory(categoryDto);
                map.put("resp","success");
                return map;
            }catch (Exception e){
                log.error("Error in adding/updating category ---- " + e.getMessage());
                map.put("resp","error");
                return map;
            }
        }
        map.put("resp","error");
        return map;
    }

    @GetMapping("/category")
    public List<CategoryDto> getAllCategory(){
        List<CategoryDto> allCategories = expenseService.getAllCategories();
        return allCategories;
    }

    @DeleteMapping("/category/{id}")
    public Map<String,String> deleteCategory(@PathVariable("id") Long id){
        Map<String,String> resp = new HashMap<>();
        try{
            expenseService.deleteCategory(id);
            resp.put("resp","success");
            return resp;
        }catch (Exception e){
            log.error("error in deleting category");
            resp.put("resp","error");
            return resp;
        }
    }

    @PostMapping("/account")
    public Map<String,String> addUpdateAccount(@RequestBody AccountDto accountDto){
        Map<String,String> map = new HashMap<>();
        try{
            if(accountDto != null){
                expenseService.addUpdateAccount(accountDto);
                map.put("resp","success");
                return map;
            }

        }catch (Exception e){
            log.error("Error in saving/updating account !! " +e.getMessage());
            map.put("resp", "error");
            return map;
        }
        map.put("resp","error");
        return map;
    }

    @GetMapping("/account")
    public List<AccountDto> getAllAccounts(){
        return expenseService.getAllAccounts();
    }

    @DeleteMapping("/account/{id}")
    public Map<String,String> deleteAccount(@PathVariable("id") Long id){
        Map<String,String> map = new HashMap<>();
        try{
            if(id != null){
                expenseService.deleteAccount(id);
                map.put("resp","success");
                return map;
            }
        }catch (Exception e){
            log.error("Error in deleting account ---- " + id);
            map.put("resp","error");
        }
        map.put("resp","error");
        return  map;
    }

    @PostMapping("/expense")
    public Map<String,String> addUpdateExpense(@RequestBody ExpenseDto expenseDto){
        Map<String,String> resp = new HashMap<>();
        try{
            expenseService.addUpdateExpense(expenseDto);
            resp.put("resp","success");
        }catch (Exception e){
            log.error("Error in saving expense ---- " + e.getMessage());
            resp.put("resp","error");
        }
        return resp;
    }

    //using plaid transaction
    @PostMapping("/expense/search")
    public List<ExpenseDto> findExpenseBetweenDates(@RequestBody ExpenseDto.ExpenseSearchDto expenseSearchDto){
//        return expenseService.searchExpense(expenseSearchDto);
        try {
            return plaidService.buildPlaidTransaction(expenseSearchDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/expense/delete/{id}")
    public Map<String,String> deleteExpense(@PathVariable("id") Long expenseId){
        Map<String,String> map = new HashMap<>();
        try{
            expenseService.deleteExpense(expenseId);
            map.put("resp","success");
        }catch (Exception e){
            log.error("Error in deleting expense --- " +expenseId);
            map.put("resp","error");
        }
        return map;
    }

    @PostMapping("/plaid/link/token")
    public Map<String,Object> getPlaidLinkToken(@RequestBody PlaidDto plaidDto){
        Map<String,Object> resp = new HashMap<>();
        try{
            Map plaidLinkToken = plaidService.findPlaidLinkToken(plaidDto);
            resp.put("resp",plaidLinkToken);
        }catch (Exception e){
            log.error("error in creating plaid link token --- "+e.getMessage());
            resp.put("resp","error");
        }
        return resp;
    }

    @GetMapping("/plaid/account")
    public List<PlaidDto.Account> getAccountAssociateToUser(){
        try {
            return plaidService.findAllPlaidAccounts();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    @PostMapping("/plaid/account/add")
    public Map<String,String> addNewPlaidAccount(@RequestParam("plaid") String plaid){
        PlaidDto plaidDto = new Gson().fromJson(plaid, PlaidDto.class);
        Map<String,String> map = new HashMap<>();
        try{
            plaidService.addNewPlaidAccount(plaidDto);
            map.put("resp","success");
        }catch (Exception e){
            log.error("error in adding new account "+e.getMessage());
            map.put("resp","error");
        }
        return map;
    }
}
