package com.sarathe.expense.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sarathe.expense.dto.RegisterUserDto;
import com.sarathe.expense.dto.UserDto;
import com.sarathe.expense.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/v1")
@CrossOrigin
@RestController
public class UserController extends BaseController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/name")
    public Map<String,String> register(@RequestParam("user") String json,
                                       @RequestParam(value = "image", required = false)MultipartFile multipartFile){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            RegisterUserDto registerUserDto = objectMapper.readValue(json, RegisterUserDto.class);
            // todo

        }catch (Exception e){
            throw new RuntimeException("Error in registration ---- " +  e.getMessage());
        }
        return null;
    }

    @GetMapping("/login")
    public Map<String,String> login(Principal principal){
        Map<String,String> resp = new HashMap<>();
        if(principal == null){
            resp.put("user",null);
        }
        assert principal != null;
        resp.put("user",principal.getName());
        return resp;
    }

    @GetMapping("/user")
    public UserDto findUserInfo(Principal principal){
        if(principal != null){
            return usersService.findUser(principal.getName());
        }
        return null;
    }
}
