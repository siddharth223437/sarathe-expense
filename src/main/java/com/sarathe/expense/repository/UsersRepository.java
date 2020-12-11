package com.sarathe.expense.repository;

import com.sarathe.expense.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    public Users findUsersByUsername(String username);
    public Users findUsersByEmail(String email);
    public Users findUsersByEmailOrUsernameOrPhoneNum(String value);
}
