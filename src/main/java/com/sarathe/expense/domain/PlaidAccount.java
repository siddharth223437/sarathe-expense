package com.sarathe.expense.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "plaid_account")
public class PlaidAccount extends Persistent {

    @Column(name = "account_name")
    private String accountName;
    private String type;
    @Column(name = "sub_type")
    private String subtype;
    @ManyToOne
    private Plaid plaid;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Plaid getPlaid() {
        return plaid;
    }

    public void setPlaid(Plaid plaid) {
        this.plaid = plaid;
    }
}
