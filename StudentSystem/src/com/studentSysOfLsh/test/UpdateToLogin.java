package com.studentSysOfLsh.test;

import com.studentSysOfLsh.dao.AdministratorList;
import com.studentSysOfLsh.dao.UserList;
import com.studentSysOfLsh.pojo.Administrator;
import com.studentSysOfLsh.pojo.User;
import com.studentSysOfLsh.stuSys.AdministratorInterface;
import com.studentSysOfLsh.stuSys.UserInterface;

import java.util.Scanner;

public class UpdateToLogin {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.用户登录 ");
        System.out.println("2.管理员登录 ");
        System.out.println("0.退出 ");
        String choice = sc.next();

        System.out.println("请输入用户名: ");
        String userName = sc.next();
        System.out.println("请输入密码: ");
        String pwd = sc.next();

//        进行用户名唯一性判断---这里需要分---用户--还是管理员--
        switch (choice){
            case "1" ->{
                User user = UserList.userSelect(userName);
                if(user != null){
//                用户存在--进行密码核对
                    while (true){
                        if (user.getPassword().equals(pwd)){
//                    进入用户界面
                            System.out.println("登录成功");
                            UserInterface.userInfo(user);
                        }else {
                            System.out.println("密码不对");
//                    进入密码重复输入--给一个函数---返回一个字符串
                            pwd = inputPwdAgain();
                        }
                    }
                }else {
                    System.out.println("用户"+userName+"不存在!请先注册");
                }
            }
            case "2" ->{
                Administrator admin = AdministratorList.adminSelect(userName);
                if(admin != null){
//                用户存在--进行密码核对
                    while (true){
                        if (admin.getPassword().equals(pwd)){
//                    进入用户界面
                            System.out.println("登录成功");
                            AdministratorInterface.adminInfo();
                        }else {
                            System.out.println("密码不对");
//                    进入密码重复输入--给一个函数---返回一个字符串
                            pwd = inputPwdAgain();
                        }
                    }
                }else {
                    System.out.println("用户"+userName+"还没有申请管理员，请先申请管理员");
                }
            }
            case "0" -> {
                return;
            }
            default -> System.out.println("输入有误！");
        }
    }

    public static String inputPwdAgain(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请再次输入您的密码: ");
        return sc.next();
     }

}
