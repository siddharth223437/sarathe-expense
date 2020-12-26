package com.sarathe.expense.repository;

import com.sarathe.expense.domain.PlaidAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaidAccountRepository extends JpaRepository<PlaidAccount, Long> {

}
