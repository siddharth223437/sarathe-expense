package com.sarathe.expense.repository;

import com.sarathe.expense.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    
}
