package com.sarathe.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDto extends UserDto {
    private Long expenseId;
    private LocalDate date;
    private Long accountId;
    private String accountName;
    private Long categoryId;
    private String categoryName;
    private String type;
    private BigDecimal amount;
    private String notes;
    private ExpenseSearchDto expenseSearch;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public ExpenseSearchDto getExpenseSearch() {
        return expenseSearch;
    }

    public void setExpenseSearch(ExpenseSearchDto expenseSearch) {
        this.expenseSearch = expenseSearch;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public static class ExpenseSearchDto {
        private LocalDate toDate;
        private LocalDate fromDate;

        public LocalDate getToDate() {
            return toDate;
        }

        public void setToDate(LocalDate toDate) {
            this.toDate = toDate;
        }

        public LocalDate getFromDate() {
            return fromDate;
        }

        public void setFromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
        }
    }
}
