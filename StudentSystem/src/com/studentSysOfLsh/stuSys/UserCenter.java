package com.studentSysOfLsh.stuSys;

import com.studentSysOfLsh.dao.UserList;
import com.studentSysOfLsh.pojo.User;

import java.util.Scanner;

public class UserCenter {
    public void ucFunction() throws Exception {
        Scanner sc = new Scanner(System.in);
        UserList userl = new UserList();
        while (true) {
            System.out.println("-------用户中心功能-------");
            System.out.println("1.查找用户 ");
            System.out.println("2.注销用户 ");
            System.out.println("3.修改用户信息 ");
            System.out.println("0.退出 ");

            String choice = sc.next();

            switch (choice) {
//                对应5个函数
                case "1" -> selectUser(userl);
                case "2" -> delectUser(userl);
                case "3" -> updateUser(userl);
                case "0" -> {
                    return;
                }
                default -> System.out.println("输入有误");
            }
        }
    }

    //    1.查找用户
    public static void selectUser(UserList userl) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要查找的用户名: ");
        String username = sc.next();
        User user = userl.userSelect(username);
        if(user != null){
            System.out.println("------用户"+username+"的信息------");
            System.out.println(user.getName()+"\t"+user.getPassword()+"\t"+user.getIdCard()+"\t"+user.getUphone());
        }else {
            System.out.println("查找不到用户"+username+"的信息");
        }
    }

    //    2.注销用户
    public static void delectUser(UserList userl) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要删除的用户名: ");
        String username = sc.next();
        Boolean b = userl.userDelect(username);
        if(b){
            System.out.println("用户 :"+username+"的信息已被删除");
        }else {
            System.out.println("查找不到-"+username+"-用户");
        }
    }

    //    3.修改用户信息
    public static void updateUser(UserList userl) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要修改的用户名: ");
        String username = sc.next();
        User user = userl.userSelect(username);
        if(user != null){
            updateLoop:while (true){
                System.out.println("-----请选择修改的事务-----");
                System.out.println("1.修改密码 - 2.修改身份证 - 3.修改手机号");
                System.out.println("0.退出");

                String choice = sc.next();
                switch (choice){
                    case "1" -> {
                        System.out.println("请输入密码:");
                        String pwd = sc.next();
                        user.setPassword(pwd);
                    }
                    case "2" -> {
                        System.out.println("请输入身份证:");
                        String idCard = sc.next();
                        user.setIdCard(idCard);
                    }
                    case "3" -> {
                        System.out.println("请输入手机号:");
                        String phone = sc.next();
                        user.setUphone(phone);
                    }
                    case "0" -> {
                        break updateLoop;
                    }
                    default -> System.out.println("输入有误");
                }
            }
            Boolean b = userl.userUpdate(user.getName(), user.getPassword(), user.getIdCard(), user.getUphone());
            if(b){
                System.out.println("用户"+username+"的信息已经成功修改");
            }else {
                System.out.println("操作异常,修改失败");
            }
        }else {
            System.out.println("查询不到用户"+username+"的信息");
        }
    }


}
