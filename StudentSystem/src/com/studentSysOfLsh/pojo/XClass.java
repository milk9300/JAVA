package com.studentSysOfLsh.pojo;

public class XClass {
    private String id;
    private String name;
    private String teacher;
    private String ctime;
    private String cplace;
    private String ctype;
    private Integer cgrade;
    private int count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCplace() {
        return cplace;
    }

    public void setCplace(String cplace) {
        this.cplace = cplace;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public Integer getCgrade() {
        return cgrade;
    }

    public void setCgrade(Integer cgrade) {
        this.cgrade = cgrade;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }




    public XClass(String id, String name, String teacher, String ctime, String cplace, String ctype, Integer cgrade, Integer count) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.ctime = ctime;
        this.cplace = cplace;
        this.ctype = ctype;
        this.cgrade = cgrade;
        this.count = count;
    }

    public XClass() {
    }


}
