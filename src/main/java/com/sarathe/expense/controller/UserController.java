package com.sarathe.expense.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController {

    @GetMapping("/name")
    public String getName(){
        return getUser();
    }
}
