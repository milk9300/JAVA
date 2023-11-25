package com.studentSysOfLsh.stuSys;

import com.studentSysOfLsh.pojo.User;

import java.util.Scanner;

public class UserInterface {
    public static void userInfo(User user) throws Exception {
        final String SelectClass = "1";
        final String SelfInfo = "2";
        final String StuRegister = "3";
        final String Break = "0";
        Scanner sc = new Scanner(System.in);
        SelectClass selectClass = new SelectClass();
        SelfInfo selfInfo = new SelfInfo();
        StuRegister stuRegister = new StuRegister();
        while (true) {
            System.out.println("----用户功能界面----");
            System.out.println("1.选课 ");
            System.out.println("2.个人中心 ");
            System.out.println("3.学生信息注册 ");
            System.out.println("0.退出 ");

            String choice = sc.next();

            switch (choice) {
                case SelectClass -> {
                    String stuId = user.getStuId();
                    if (stuId == null || stuId.isEmpty()) {
                        System.out.println("没有注册学生信息，该功能还不可以使用哦！");
                    } else {
                        selectClass.selectClassFunction(user);
                    }
                }
                case SelfInfo -> selfInfo.selfInfoFunction(user);
                case StuRegister -> {
//                    如果学生已经注册过，那么提示
                    String stuId = user.getStuId();
                    if (stuId == null || stuId.isEmpty()) {
//                        当用户的stu_id为空时，才进行用户注册
                        stuRegister.stuRegisterFunction(user);
                    } else {
                        System.out.println("你已经注册过学生信息了，去体验别的功能吧！");
                    }
                }
                case Break -> {
                    return;
                }
                default -> System.out.println("输入有误");
            }
        }
    }
}
