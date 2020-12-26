package com.sarathe.expense.domain;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plaid")
public class Plaid extends Persistent {

    @Column(name = "plaid_inst_id")
    private String plaidInstId;
    @Column(name = "institution_name")
    private String institutionName;
    @Column(name = "access_token")
    private String accessToken;
    @OneToMany(mappedBy = "plaid",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<PlaidAccount> plaidAccounts = new ArrayList<>();
    @ManyToOne
    private Users users;

    public String getPlaidInstId() {
        return plaidInstId;
    }

    public void setPlaidInstId(String plaidInstId) {
        this.plaidInstId = plaidInstId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<PlaidAccount> getPlaidAccounts() {
        return plaidAccounts;
    }

    public void addPlaidAccounts(PlaidAccount plaidAccounts) {
        this.plaidAccounts.add(plaidAccounts);
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
