package com.studentSysOfLsh.pojo;

public class SelectCourseResult {
    private String cid;
    private String cName;
    private String sid;
    private String sName;

    public SelectCourseResult() {
    }

    public SelectCourseResult(String cid, String cName, String sid, String sName) {
        this.cid = cid;
        this.cName = cName;
        this.sid = sid;
        this.sName = sName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
}
