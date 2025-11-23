package com.example.SchoolWebApp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Table(name = "roles")
@Entity
public class Roles extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
