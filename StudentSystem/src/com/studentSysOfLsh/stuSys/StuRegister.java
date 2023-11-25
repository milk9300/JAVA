package com.studentSysOfLsh.stuSys;

import com.studentSysOfLsh.dao.StudentList;
import com.studentSysOfLsh.dao.UserList;
import com.studentSysOfLsh.pojo.Student;
import com.studentSysOfLsh.pojo.User;

import java.util.Scanner;

public class StuRegister {
    public void stuRegisterFunction(User user) throws Exception {
//        代码完善后将这个作为参数
//        如何进行用户匹配
//        1.输入学号在数据库进行查找
        Scanner sc = new Scanner(System.in);
        StudentList stul = new StudentList();
        UserList userl = new UserList();
        toRegisterLoop:while (true){
            System.out.println("请输入您的学号: ");
            String sid = sc.next();
//            进行学号唯一性判断
//            通过user表进行查询---如果查询到了那么就说明该学号存在
            Boolean conId = userl.userConfirmSid(sid);
            if(conId){
//                如果学号存在  ---  提示该学号已经注册过
                System.out.println("该学号已经被注册！");
                break toRegisterLoop;
            }else {
//                当学号不存在时，进行注册操作
                Student stu = stul.stuSelect(sid);
                if(stu != null){
//              1.1如果查找到学号---将学号添加到user表中
//                  1.1.1先判断user表对应的stu_id是否为null
                    if (user.getStuId() == null || user.getStuId().equals("")) {
//                为空可以进行用户与学生信息的匹配
                        userl.userToRegister(sid,user.getName());
                        System.out.println("学生信息匹配成功，感谢配合");
                        System.out.println("匹配信息后需要重新登录");
                        System.exit(0);
                    } else {
                        System.out.println("您已经注册了学生信息,需要修改请联系管理员");
                        break toRegisterLoop;
                    }
                }else {
                    //        1.2如果查不到学号进行提示---确认学生信息
                    System.out.println("查找不到学号为"+sid+"的学生信息");
                    System.out.println("是否继续匹配学生信息？");
                    System.out.println("1.继续;0.退出");
                    String choice = sc.next();
                    switch (choice){
                        case "1" -> System.out.println("请确认信息后继续-----");
                        case "0"-> {
                            return;
                        }
                        default -> System.out.println("输入有误");
                    }
                }
            }
        }
    }

}
