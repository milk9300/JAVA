package com.studentSysOfLsh.pojo;

import com.studentSysOfLsh.vo.UserAll;

public class Administrator extends UserAll {
    private String rank;

    public Administrator() {
    }

    public Administrator(String name, String password,String rank) {
        super(name, password);
        this.rank = rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
