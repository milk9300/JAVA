package com.studentSysOfLsh.pojo;

import com.studentSysOfLsh.vo.UserAll;

public class User extends UserAll {
    private String idCard;
    private String uphone;
    private String stuId;

    public User() {
    }

    public User(String name, String password, String idCard, String uphone, String stuId) {
        super(name, password);
        this.idCard = idCard;
        this.uphone = uphone;
        this.stuId = stuId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
