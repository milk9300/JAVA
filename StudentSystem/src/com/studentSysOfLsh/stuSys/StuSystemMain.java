package com.studentSysOfLsh.stuSys;

import com.studentSysOfLsh.dao.AdministratorList;
import com.studentSysOfLsh.dao.UserList;
import com.studentSysOfLsh.pojo.Administrator;
import com.studentSysOfLsh.pojo.User;
import com.studentSysOfLsh.vo.UserAll;

import java.util.Scanner;

public class StuSystemMain {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--------学生管理系统--------");
            System.out.println("1.登录");
            System.out.println("2.注册 ");
            System.out.println("3.忘记密码 ");
            System.out.println("0.退出系统 ");

            String choice = sc.next();
            switch (choice) {
                case "1" -> toLogin();
                case "2" -> toRegister();
                case "3" -> forgetPassword();
                case "0" -> {
                    System.out.println("欢迎您再次使用！");
                    return;
                }
                default -> System.out.println("输入有误");
            }
        }
    }

    //    用户登录和管理员登录是两种不同的写法  ---  比较优化


    public static void toLogin() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("------登录操作------");
        System.out.println("1.用户登录----2.管理员登录 ");
        System.out.println("0.退出 ");
        String choice = sc.next();
        if (choice.equals("0")) return;

        System.out.println("请输入用户名: ");
        String userName = sc.next();
        System.out.println("请输入密码: ");
        String pwd = sc.next();

        UserAll userAll = null;
        if(choice.equals("1")){
            userAll = UserList.userSelect(userName);
        } else if (choice.equals("2")) {
            userAll = AdministratorList.adminSelect(userName);
        }else {
            System.out.println("输入有误!");
        }
        if(userAll != null){
            while (true) {
                if (userAll.getPassword().equals(pwd)) {
                    System.out.println("登录成功");
                    if(userAll instanceof User user){
                        UserInterface.userInfo(user);
                        break;
                    } else {
                        Administrator admin = (Administrator) userAll;
                        AdministratorInterface.adminInfo();
                        break;
                    }
                } else {
                    System.out.println("密码不对");
                    pwd = inputPwdAgain();
                }
            }
        }else {
            if (choice.equals("1")) {
                System.out.println("用户" + userName + "不存在!请先注册");
            } else {
                System.out.println("用户" + userName + "还没有申请管理员，请先申请管理员");
            }
        }
    }
    public static String inputPwdAgain(){
        Scanner sc = new Scanner(System.in);
        System.out.println("请再次输入您的密码: ");
        return sc.next();
    }

    public static void toRegister() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("------注册操作------");
        System.out.println("1.用户注册----2.管理员注册 ");
        System.out.println("0.退出 ");
        String toRegisterWay = sc.next();
        UserAll userAll = new User();
        if (toRegisterWay.equals("1")) {
//            通过父类创建User
            User user = (User) userAll;
//            1.进行用户名输入---唯一性判断
            usernameLoop:
            while (true) {
                System.out.println("请输入用户名:");
                String username = sc.next();
//        1.1进行用户名唯一性判断---便利集合（数据库）
                User u = UserList.userSelect(username);
                if (u != null) {
//            用户名重复
                    System.out.println("用户名重复，请重新输入");
                } else {
//            用户名不重复，进行注册
                    user.setName(username);
                    break usernameLoop;
                }
            }
//            2.密码判断
            confirmPwd(sc, userAll);
//            3.输入身份证 --- 唯一性判断
            idCardLoop:
            while (true) {
                System.out.println("请输入您的身份证号码:");
                String idCare = sc.next();
                if (idCare.length() != 18) {
//            3.1长度为18位
                    System.out.println("身份证格式长度不合理");
                } else if (idCare.charAt(0) == '0') {
//            3.2不能以数字0开头
                    System.out.println("身份证开头不可以是0");
                } else if (!judgeIdCare(idCare)) {
//            前17位必须是数字，最后一位可以是数字、x、X；----使用一个判断方法
//                判断方法返回false时,进行报错
                    System.out.println("最后一位只能是*数字或x或X*");
                } else if (judgeIdCare(idCare)) {
//            判断方法返回true时,表明这个idcard满足条件---可以进行存储
                    user.setIdCard(idCare);
                    break idCardLoop;
                }
            }
//            4.输入手机号
            phoneLoop:
            while (true) {
                System.out.println("请输入您的手机号:");
                String phone = sc.next();
                if (phone.length() != 11) {
                    System.out.println("手机号长度不合理！");
                } else if (!judgePhoneIsNum(phone)) {
                    System.out.println("手机格式不合理！");
                } else if (judgePhoneIsNum(phone)) {
                    user.setUphone(phone);
                    break phoneLoop;
                }
            }
//            0.将数据添加到数据库中
            Boolean b = UserList.userAdd(user.getName(), user.getPassword(), user.getIdCard(), user.getUphone());
            if (b) {
                System.out.println("注册成功");
            } else {
                System.out.println("注册失败");
            }

        } else if (toRegisterWay.equals("2")) {
//            1.进行用户名输入---唯一性判断
            usernameLoop:
            while (true) {
                System.out.println("请输入用户名:");
                String administratorname = sc.next();
//        1.1进行用户名唯一性判断---便利集合（数据库）
                Administrator a = AdministratorList.adminSelect(administratorname);
                if (a != null) {
//            用户名重复
                    System.out.println("用户名重复，请重新输入");
                } else {
//            用户名不重复，进行注册
                    userAll.setName(administratorname);
                    break usernameLoop;
                }
            }
//            2.密码判断
            confirmPwd(sc, userAll);
            System.out.println("通行密码:");
            String ad = sc.next();
            if (ad.equals("HappyEveryday")) {
//                0.数据库存储
                Boolean b = AdministratorList.adminAdd(userAll.getName(), userAll.getPassword());
                if (b) {
                    System.out.println("注册成功");
                } else {
                    System.out.println("注册失败");
                }
            } else {
                System.out.println("通行密码不对");
                System.out.println("通行密码去问刘书淮!!!");
            }
        } else if (toRegisterWay.equals("0")) {
        } else {
            System.out.println("输入有误！");
        }
    }
    public static void forgetPassword() throws Exception {
        Scanner sc = new Scanner(System.in);
//        输入用户名
        System.out.println("输入用户名:");
        String name = sc.next();
//        遍历集合查找用户名
        User user = UserList.userSelect(name);
//        Boolean flag = true;
//            如果用户名存在---user在数据表中查找到值，就是不为null
        if (user != null) {
            confirmIDLoop:
            while (true) {
//                那么然后获取对应的idcard和phone进行比较
                System.out.println("输入身份证:");
                String idCard = sc.next();
                System.out.println("输入手机号:");
                String phone = sc.next();
//                进行信息校验，如果两个私人信息都对应，那么可以修改
                if (user.getIdCard().equals(idCard) && user.getUphone().equals(phone)) {
//                    身份核对成功---进行修改
                    System.out.println("身份核对成功");
                    confirmPwd(sc, user);
                    System.out.println("密码修改成功");
                    break confirmIDLoop;
                } else {
//                    身份核对失败---进行报错
                    System.out.println("身份信息错误！");
//                        flag = false;
                    System.out.println("是否退出修改密码操作？");
                    System.out.println("1.继续，2.退出");
                    String choice = sc.next();
                    if (choice == "2") {
                        break confirmIDLoop;
                    }
                }
            }

        } else {
            System.out.println("用户不存在");
            return;
        }
        Boolean b = UserList.userUpdate(user.getName(), user.getPassword(), user.getIdCard(), user.getUphone());
        if (b) {
            System.out.println("数据库信息修改成功");
        } else {
            System.out.println("数据库信息修改失败");
        }
//            if(flag){
//                Boolean b = ul.userUpdate(user.getName(), user.getPassword(), user.getIdCard(), user.getUphone());
//                if(b){
//                    System.out.println("数据库信息修改成功");
//                }else {
//                    System.out.println("数据库信息修改失败");
//                }
//            }else {
//
//            }

    }

    public static Boolean judgeIdCare(String idCare) {
//        遍历前17位,进行数字判断
        for (int i = 0; i < idCare.length() - 1; i++) {
            char id = idCare.charAt(i);
            if (id >= '0' && id <= '9') {
//              前17位满足条件
//              最后一位可以是  数字  or  x   or   X;
                char lastid = idCare.charAt(17);
                if (lastid == 'x' || lastid == 'X' || (lastid >= '0' && lastid <= '9')) {
                    return true;
                }
            }
        }
        return false;
    }

    public static Boolean judgePhoneIsNum(String phone) {
        for (int i = 0; i < phone.length(); i++) {
            char n = phone.charAt(i);
            if (n < '0' || n > '9') {
                return false;
            }
        }
        return true;
    }

    public static void confirmPwd(Scanner sc, UserAll userAll) {
        //        2.密码判断
        System.out.println("请输入您的密码:");
        String pwd1 = sc.next();
        passwordLoop:
        while (true) {
            System.out.println("请确认您的密码:");
            String pwd2 = sc.next();
//        2.1密码输入两次，一致进行注册
            if (!pwd1.equals(pwd2)) {
//                密码不一致继续输入确定
                System.out.println("您两次输入的密码不一致!");
            } else {
//                密码一直存储
                userAll.setPassword(pwd1);
                break passwordLoop;
            }
        }
    }

}
