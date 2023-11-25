package com.studentSysOfLsh.test;

import com.studentSysOfLsh.dao.UserList;
import com.studentSysOfLsh.pojo.User;
import com.studentSysOfLsh.stuSys.SelectClass;

public class SelectClassTest {
    public static void main(String[] args) throws Exception {
        SelectClass selectClass = new SelectClass();
        UserList userl = new UserList();
        String username = "stulsh";
        User user = userl.userSelect(username);
        selectClass.selectClassFunction(user);
    }
}
