package com.sarathe.expense.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Roles extends Persistent {

    @Column(name = "role_name")
    private String roleName;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private List<Users> users = new ArrayList<>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void addUsers(Users users) {
        this.users.add(users);
    }
}
