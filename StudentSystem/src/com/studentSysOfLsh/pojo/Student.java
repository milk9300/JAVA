package com.studentSysOfLsh.pojo;

public class Student {
    private String sid;
    private String name;
    private Integer age;
    private String gender;
    private String college;
    private String profession;
    private String phone;

    public Student() {
    }

    public Student(String sid, String name, Integer age, String gender, String college, String profession, String phone) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.college = college;
        this.profession = profession;
        this.phone = phone;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
