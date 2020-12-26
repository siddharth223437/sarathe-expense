package com.sarathe.expense.service;

import com.sarathe.expense.domain.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract  class SaratheAbstract {

    protected Users getUser(){
        return  (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
