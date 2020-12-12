package com.sarathe.expense.repository;

import com.sarathe.expense.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
}
