package com.sarathe.expense.repository;

import com.sarathe.expense.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    public Roles findRolesByRoleName(String roleName);
}
