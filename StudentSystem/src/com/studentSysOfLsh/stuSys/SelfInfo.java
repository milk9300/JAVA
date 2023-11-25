package com.studentSysOfLsh.stuSys;

import com.studentSysOfLsh.dao.UserList;
import com.studentSysOfLsh.pojo.User;

import java.util.Scanner;

public class SelfInfo {
    public void selfInfoFunction(User user) throws Exception {
        Scanner sc = new Scanner(System.in);
//        UserList userl = new UserList();
        while (true){
            System.out.println("-------个人中心-------");
            System.out.println("1.修改密码");
            System.out.println("2.完善信息");
            System.out.println("3.注销账号");
            System.out.println("0.退出");

            String choice = sc.next();

            switch (choice){
                case "1" -> updatePwd(user);
                case "2" -> System.out.println("等待开发");
                case "3" -> delectAccount(user);
                case "0" -> {
                    return;
                }
                default -> System.out.println("输入有误");
            }
        }
    }
//    1.修改密码
    public static void updatePwd(User user) throws Exception {
        UserList userl  =new UserList();
        Scanner sc = new Scanner(System.in);
        confirmIDLoop:while (true){
//                那么然后获取对应的idcard和phone进行比较
            System.out.println("输入身份证:");
            String idCard = sc.next();
            System.out.println("输入手机号:");
            String phone = sc.next();
//                进行信息校验，如果两个私人信息都对应，那么可以修改
            if(user.getIdCard().equals(idCard) && user.getUphone().equals(phone)){
//                    身份核对成功---进行修改
                System.out.println("身份核对成功");
                confirmPwd(sc,user);
//                密码确认后进行数据添加
                userl.userUpdate(user.getName(),user.getPassword(),user.getIdCard(),user.getUphone());
                System.out.println("密码修改成功");
                break confirmIDLoop;
            }else {
//                    身份核对失败---进行报错
                System.out.println("身份信息错误！");
//                        flag = false;
                System.out.println("是否退出修改密码操作？");
                System.out.println("1.继续，0.退出");
                String choice = sc.next();
                if(choice.equals("1")){
                    System.out.println("请认真确认身份信息");
                }else if(choice.equals("0")){
                    break confirmIDLoop;
                }
            }
        }
    }
//    2.完善信息
    public static void fullSelfInfo(){


    }
//      3.注销账号
    public static void delectAccount(User user) throws Exception {
        UserList userl = new UserList();
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("确定注销您的账户吗？");
            System.out.println("确定注销您的账户吗？");
            System.out.println("确定注销您的账户吗？");
            System.out.println("1-确认，0-取消");
            String choice = sc.next();
            if("1".equals(choice)){
                userl.userDelect(user.getName());
                System.out.println("感谢您的使用，欢迎反馈系统任何问题！/<->/ ");
                System.exit(0);
            } else if ("0".equals(choice)) {
                System.out.println("感谢您对系统的信任！");
                break;
            }else {
                System.out.println("输入有误，推荐输入 0 !");
            }
        }
    }

    public static void confirmPwd(Scanner sc,User user){
        //        2.密码判断
        System.out.println("请输入您的密码:");
        String pwd1 = sc.next();
        passwordLoop:while (true){
            System.out.println("请确认您的密码:");
            String pwd2 = sc.next();
//        2.1密码输入两次，一致进行注册
            if(!pwd1.equals(pwd2)){
//                密码不一致继续输入确定
                System.out.println("您两次输入的密码不一致!");
            }else {
//                密码一直存储
                user.setPassword(pwd1);
                break passwordLoop;
            }
        }
    }
}
