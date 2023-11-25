package com.studentSysOfLsh.vo;

public class UserAll {
    private String name;
    private String password;

    public UserAll() {
    }

    public UserAll(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
