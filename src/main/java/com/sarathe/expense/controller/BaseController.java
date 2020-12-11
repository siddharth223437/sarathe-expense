package com.sarathe.expense.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public abstract class BaseController {

    public String getUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
