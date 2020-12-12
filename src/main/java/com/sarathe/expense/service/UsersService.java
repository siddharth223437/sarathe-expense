package com.sarathe.expense.service;

import com.sarathe.expense.domain.Users;
import com.sarathe.expense.dto.RegisterUserDto;
import com.sarathe.expense.dto.UserDto;
import com.sarathe.expense.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
public class UsersService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository usersRepository;

    public Map<String,String> registerUser(RegisterUserDto dto){
        Users users = new Users();
        users.setUsername(dto.getUsername());
        users.setPhoneNum(dto.getPhoneNum());
        users.setEmail(dto.getEmail());
        users.setPassword(passwordEncoder.encode(dto.getPassword()));
        users.setFirstName(dto.getFirstName());
        users.setLastName(dto.getLastName());
        return null;
    }

    public UserDto findUser(String username){
        Users users = usersRepository.findUsersByUsername(username);
        if(users != null) {
            UserDto dto = new UserDto();
            dto.setEmail(users.getEmail());
            dto.setFirstName(users.getFirstName());
            dto.setLastName(users.getLastName());
            dto.setPhoneNum(users.getPhoneNum());
            dto.setUsername(users.getUsername());
            dto.setImageUrl(users.getImageUrl());

            return dto;

        }

        return null;

    }

}
