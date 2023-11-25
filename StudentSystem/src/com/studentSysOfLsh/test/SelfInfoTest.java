package com.studentSysOfLsh.test;

import com.studentSysOfLsh.dao.UserList;
import com.studentSysOfLsh.pojo.User;
import com.studentSysOfLsh.stuSys.SelfInfo;

public class SelfInfoTest {
    public static void main(String[] args) throws Exception {
        UserList userl = new UserList();
        SelfInfo selfInfo = new SelfInfo();
        String username = "stulsh";
        User user = userl.userSelect(username);
        selfInfo.selfInfoFunction(user);

    }
}
