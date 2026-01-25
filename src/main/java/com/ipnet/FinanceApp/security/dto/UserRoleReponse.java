package com.ipnet.FinanceApp.security.dto;


import java.util.Date;


public class UserRoleReponse {
    private Long id;
    private String fullName;
    private String username;
    private Date createdAt;
    private boolean enable;
    private String roles;

    public UserRoleReponse() {
    }

    public UserRoleReponse(Long id, String fullName, String username, Date createdAt, boolean enable, String roles) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.createdAt = createdAt;
        this.enable = enable;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
   
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
