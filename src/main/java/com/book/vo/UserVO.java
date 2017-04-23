package com.book.vo;

/**
 * Created by Hung on 4/23/2017.
 */
public class UserVO {
    private String username;
    private String password;
    private String roleType;

    public UserVO(String username, String password, String roleType){
        this.username = username;
        this.password = password;
        this.roleType = roleType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
