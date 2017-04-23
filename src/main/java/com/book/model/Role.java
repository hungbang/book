package com.book.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Hung on 4/23/2017.
 */
@Entity
public class Role {
    private int roleKey;
    private String roleType;

    @Id
    @Column(name = "role_key", nullable = false)
    public int getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(int roleKey) {
        this.roleKey = roleKey;
    }

    @Basic
    @Column(name = "role_type", nullable = true)
    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (roleKey != role.roleKey) return false;
        if (roleType != null ? !roleType.equals(role.roleType) : role.roleType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleKey;
        result = 31 * result + (roleType != null ? roleType.hashCode() : 0);
        return result;
    }
}
