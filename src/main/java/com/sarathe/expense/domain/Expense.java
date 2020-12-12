package com.sarathe.expense.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expense")
public class Expense extends Persistent {

    private LocalDate expenseDate;
    private String notes;
    private String type;
    private BigDecimal expenseAmount;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Category category;



}
