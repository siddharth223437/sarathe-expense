package com.sarathe.expense.repository;

import com.sarathe.expense.domain.Plaid;
import com.sarathe.expense.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaidRepository extends JpaRepository<Plaid, Long> {

    public Plaid findPlaidByAccessToken(String accessToken);
}
