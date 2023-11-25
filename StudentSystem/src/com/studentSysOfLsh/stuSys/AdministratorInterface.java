package com.studentSysOfLsh.stuSys;

import java.util.Scanner;

public class AdministratorInterface {
    public static void adminInfo() throws Exception {
        final String StudentInfo = "1";
        final String ClassInfo = "2";
        final String UserCenter = "3";
        final String Break = "0";
        StudentInfo studentInfo = new StudentInfo();
        ClassInfo classInfo = new ClassInfo();
        UserCenter userCenter = new UserCenter();

        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("----管理员功能界面----");
            System.out.println("1.学生信息 ");
            System.out.println("2.课程信息 ");
            System.out.println("3.用户中心 ");
            System.out.println("0.退出 ");

            String choice = sc.next();
            switch (choice){
                case StudentInfo -> studentInfo.stuFunction();
                case ClassInfo -> classInfo.classFunction();
                case UserCenter -> userCenter.ucFunction();
                case Break -> {
                    return;
                }
                default -> System.out.println("输入有误");
            }
        }
    }
}
