package com.sarathe.expense.repository;

import com.sarathe.expense.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    public List<Expense> findExpenseByExpenseDateBetween(LocalDate fromDate, LocalDate toDate);
}
