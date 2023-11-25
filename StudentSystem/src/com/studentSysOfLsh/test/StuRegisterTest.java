package com.studentSysOfLsh.test;

import com.studentSysOfLsh.dao.UserList;
import com.studentSysOfLsh.pojo.User;
import com.studentSysOfLsh.stuSys.StuRegister;

import java.util.Scanner;

public class StuRegisterTest {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        UserList userl = new UserList();
        StuRegister stuRegister = new StuRegister();
        String username = "wanghc";
        User user = userl.userSelect(username);
//        String stuId = sc.next();
        stuRegister.stuRegisterFunction(user);
//        System.out.println(user.getStuId());
    }
}
