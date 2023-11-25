package com.studentSysOfLsh.stuSys;
import com.studentSysOfLsh.dao.StudentList;
import com.studentSysOfLsh.pojo.Student;
import java.util.Scanner;
public class StudentInfo {
    public void stuFunction() throws Exception {
        StudentList stul = new StudentList();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("-------学生信息功能-------");
            System.out.println("1.添加学生信息 ");
            System.out.println("2.查询学生信息 ");
            System.out.println("3.删除学生信息 ");
            System.out.println("4.修改学生信息 ");
            System.out.println("0.退出 ");

            String choice = sc.next();

            switch (choice) {
//                对应5个函数
                case "1" -> addStu(stul);
                case "2" -> selectStu(stul);
                case "3" -> delectStu(stul);
                case "4" -> updateStu(stul);
                case "0" -> {
                    return;
                }
                default -> System.out.println("输入有误");
            }
        }
    }

    //    1.添加学生信息
    public static void addStu(StudentList stul) throws Exception {
        Scanner sc = new Scanner(System.in);
        Student stu = new Student();
//        可以进行条件约束，对输入进行限制
        System.out.println("请输入您的**学号**:");
        String sid = sc.next();
        stu.setSid(sid);
        System.out.println("请输入您的**姓名**:");
        String sname = sc.next();
        stu.setName(sname);
        System.out.println("请输入您的**年龄**:");
        int sage = sc.nextInt();
        stu.setAge(sage);
        System.out.println("请输入您的**性别**:");
        String sgender = sc.next();
        stu.setGender(sgender);
        System.out.println("请输入您的**学院**:");
        String scolllege = sc.next();
        stu.setCollege(scolllege);
        System.out.println("请输入您的**专业**:");
        String profession = sc.next();
        stu.setProfession(profession);
        System.out.println("请输入您的**联系方式**:");
        String sphone = sc.next();
        stu.setPhone(sphone);
//            这里以后研究如何将数据存入到数据库中
        Boolean flag = stul.stuAdd(stu.getSid(), stu.getName(), stu.getAge(), stu.getGender(), stu.getCollege(), stu.getProfession(), stu.getPhone());
        if (flag) {
            System.out.println("信息在数据库添加成功");
        } else {
            System.out.println("信息添加失败！");
        }

    }

    //    2.查询学生信息
//    使用学号查询
    public static void selectStu(StudentList stul) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入查询的学生学号: ");
        String sid = sc.next();
//        查询返回一个Student对象
        Student stu = stul.stuSelect(sid);
        if (stu != null) {
//        使用对象获取对应的信息
            System.out.println("-------学生id: " + sid + "的信息-------");
            System.out.println(stu.getSid() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getGender() + "\t" + stu.getCollege() + "\t" + stu.getProfession() + "\t" + stu.getPhone());
        } else {
            System.out.println("查无此人！请再次确认信息");
        }
    }

    //    3.删除学生信息
    public static void delectStu(StudentList stul) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要删除的学生学号: ");
        String sid = sc.next();
        Boolean b = stul.stuDelect(sid);
        if (b) {
            System.out.println("学生id: " + sid + "的信息已被删除");
        } else {
            System.out.println("操作异常，删除失败");
        }
    }

    //    4.修改学生信息
    public static void updateStu(StudentList stul) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改信息的学生学号: ");
        String sid = sc.next();
        Student mstu = stul.stuSelect(sid);
        if (mstu != null) {
            updateLoop:while (true) {
//                        修改操作可以写一个方法-----代码太长了
                System.out.println("请输入修改的方式:");
                System.out.println("1.修改姓名 - 2.修改年龄 - 3.修改性别 - 4.修改学院 - 5.修改专业 - 6.修改联系方式 ");
                System.out.println("0.退出操作 ");
                System.out.println("-----------------------------------------");
                String choice = sc.next();

                switch (choice) {
                    case "1" -> {
                        // 1.修改姓名
                        System.out.println("请输入要修改的姓名:");
                        String mname = sc.next();
                        mstu.setName(mname);
                    }
                    case "2" -> {
                        // 2.修改年龄
                        System.out.println("请输入要修改的年龄:");
                        int mage = sc.nextInt();
                        mstu.setAge(mage);
                    }
                    case "3" -> {
                        // 3.修改性别
                        System.out.println("请输入要修改的性别:");
                        String mgender = sc.next();
                        mstu.setGender(mgender);
                    }
                    case "4" -> {
                        // 4.修改学院
                        System.out.println("请输入要修改的学院:");
                        String mcollege = sc.next();
                        mstu.setCollege(mcollege);
                    }
                    case "5" -> {
                        //  5.修改班级
                        System.out.println("请输入要修改的专业:");
                        String mprefession = sc.next();
                        mstu.setProfession(mprefession);
                    }
                    case "6" -> {
                        //   6.修改联系方式
                        System.out.println("请输入要修改的联系方式:");
                        String mphone = sc.next();
                        mstu.setPhone(mphone);
                    }
                    case "0" -> {
                        break updateLoop;
                    }
                    default -> System.out.println("输入有误");
                }
            }
            Boolean b = stul.stuUpdate(mstu.getSid(), mstu.getName(), mstu.getAge(), mstu.getGender(), mstu.getCollege(), mstu.getProfession(), mstu.getPhone());
            if(b){
                System.out.println("学生id: "+sid+"信息修改成功");
            }else {
                System.out.println("操作异常，修改失败");
            }
        }else {
//            当查询返回对象为空时，说明学生不存在
            System.out.println("找不到id为"+sid+"的学生");
        }
    }
}
