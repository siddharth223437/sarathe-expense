package com.sarathe.expense.service;

import com.sarathe.expense.domain.Users;
import com.sarathe.expense.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = usersRepository.findUsersByUsername(s);
        if(users == null){
            throw new UsernameNotFoundException("User not found " + s);
        }
        return users;
    }
}
