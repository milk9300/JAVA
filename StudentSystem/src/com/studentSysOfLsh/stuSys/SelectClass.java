package com.studentSysOfLsh.stuSys;

import com.studentSysOfLsh.dao.SelectCourseResultList;
import com.studentSysOfLsh.dao.UserList;
import com.studentSysOfLsh.dao.XClassList;
import com.studentSysOfLsh.pojo.User;
import com.studentSysOfLsh.pojo.XClass;

import java.util.List;
import java.util.Scanner;

public class SelectClass {
    public void selectClassFunction(User user) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("-------选课功能-------");
            System.out.println("1.选择课程 ");
            System.out.println("2.查询选课结果 ");
            System.out.println("3.退选课程 ");
            System.out.println("4.查询所有课程 ");
            System.out.println("0.退出 ");

            String choice = sc.next();

//            能否实现用户信息查找学生信息

            switch (choice) {
                case "1" -> {
//                    进行学生信息注册判断
//                    注册了学生信息才可以使用选课功能
                    String stuId = user.getStuId();
                    if(stuId == null || stuId.isEmpty()){
                        System.out.println("没有注册学生信息，该功能还不可以使用哦！");
                    }else {
                        selectCourse(user);
                    }
                }
                case "2" -> inquireResult(user);
                case "3" -> delectCourse(user);
                case "4" -> inquireAllCourse();
                case "0" -> {
                    return;
                }
                default -> System.out.println("输入有误");
            }
        }
    }
//        1.选择课程（1.课程编号）
//        1.1.选课   ---->  向xclass_stu表中添加信息
//        1.1.1创建一个stu_class表----这个表的用途---返回stu信息和class信息
//        1.1.2当某个课被确认选择后，对课程的count进行操作  +1
//        1.2.形成选课结果
//        1.2.1 遍历xclass表获取对应课程的全部信息  然后按照一定的格式进行打印

    public static void selectCourse(User user) throws Exception {
        Scanner sc = new Scanner(System.in);
        XClassList xclassl = new XClassList();
        UserList userl = new UserList();
        SelectCourseResultList scrl = new SelectCourseResultList();
        String stuId = user.getStuId();
        List<String> courses = scrl.scrSelectBySId(stuId);
        mainLoop:while (true){
            System.out.println("请输入选择的课程编码: ");
            String classId = sc.next();
            XClass xclass = xclassl.classSelect(classId);
            //判断课程是否存在
            if (xclass == null) {
                System.out.println("该课程不存在!");
                return;
            }
            //判断学生是否已经选过课程
//        唯一性判断  ---  一个学生可以对应多个课程，一个课程也可以对应多个学生，但是一个学生id只能对应一个课程，
            if (courses.contains(classId)) {
                System.out.println("你已经选过该课程!");
                return;
            }

            confirmLoop:while (true) {
//        2.将确认选择的课程和对应的学生信息进行添加----stu_class表  ----需要一个选课结果对象  --  用于获取信息
                System.out.println("请确认课程编码: -->" + classId);
                System.out.println("请选择下一步操作(1-确认，2-修改，0-退出) :");
                String choice = sc.next();
                if ("1".equals(choice)) {
//                添加选课记录
                    scrl.scrAdd(classId, stuId);
//                选课成功后，对应课程的count进行+1操作
                    xclass.setCount(xclass.getCount()+1);
//                对应数据库进行修改
                    xclassl.classUpdate(xclass.getId(), xclass.getName(), xclass.getTeacher(), xclass.getCtime(), xclass.getCplace(), xclass.getCtype(), xclass.getCgrade(), xclass.getCount());
                    System.out.println("选课成功!");
                    break mainLoop;
                } else if ("2".equals(choice)) {
//                这个是跳出添加确认循环
                    break confirmLoop;
                } else if ("0".equals(choice)) {
                    break mainLoop;
                } else {
                    System.out.println("输入有误，请重新选择！");
                }
            }
        }
    }


    //        2.查询选课结果
//    如何获取学生id------用户对应id
//    需要信息-----学生id
//    mysql> select class_id from stu_class where stu_id = "xxxx";
//    查看课程时，可以批量获取学生对应的课程编码，然后遍历课程信息
//        2.1  遍历stu_class表 获取学生对应的课程  然后 遍历xclass表获取对应课程的全部信息  然后按照一定的格式进行打印
    public static void inquireResult(User user) throws Exception {
//        用一个集合接一下传递回来的课程编码
        XClassList xclassl = new XClassList();
        SelectCourseResultList scrl = new SelectCourseResultList();
        List<String> courseIds = scrl.scrSelectBySId(user.getStuId());
        if(courseIds.isEmpty()){
            System.out.println("没有任何选课记录");
        }else {
            System.out.println("您好，同学，---------------你已选课程数量:**"+courseIds.size()+"门**------------------");
            for (int i = 0; i < courseIds.size(); i++) {
                int count = i + 1;
                XClass xc = xclassl.classSelect(courseIds.get(i));
                if (xc != null) {
                    System.out.println("课程" + count + "：");
                    System.out.println("(" + xc.getId() + ")  " + xc.getName() + " -- " + xc.getCgrade() + " 分");
                    System.out.println("---------------------------------------------------------------------------------");
                    System.out.println("带教老师\t\t上课时间\t\t\t\t\t\t\t上课地点\t\t\t课程类型");
                    System.out.println(xc.getTeacher() + "\t\t" + xc.getCtime() + "\t\t" + xc.getCplace() + "\t\t" + xc.getCtype());
                    System.out.println("---------------------------------------------------------------------------------");
                    System.out.println("课程总人数---{ " + xc.getCount() + " }人");
                }
            }
        }

    }
    //    3.    退选课程
//    删除这个学生id对应的课程
    public static void delectCourse(User user) throws Exception {
        Scanner sc = new Scanner(System.in);
        XClass xclass;
        XClassList xclassl = new XClassList();
        SelectCourseResultList scrl = new SelectCourseResultList();
        List<String> courses = scrl.scrSelectBySId(user.getStuId());
//        这里可以写一个功能查询选课的编码
        System.out.println("请输入你需要退选的课程id: ");
//        需要查找---判断用户有没有选择这个课程
        String cid = sc.next();
//        如果数据表中，学生没选这个课--报错
        if(courses.contains(cid)){
//            课程存在进行删除
            scrl.scrDelect(user.getStuId(), cid);
//                选课成功后，对应课程的count进行+1操作
//            修改对应课程--应该获取
            xclass = xclassl.classSelect(cid);
            if(xclass != null){
                Integer count = xclass.getCount();
                if(count != null){
                    xclass.setCount(count - 1);
                }
//                对应数据库进行修改
                xclassl.classUpdate(xclass.getId(), xclass.getName(), xclass.getTeacher(), xclass.getCtime(), xclass.getCplace(), xclass.getCtype(), xclass.getCgrade(), xclass.getCount());
            }
            System.out.println("退课成功！");
        }else {
            System.out.println("你没有选择这个课程! ");
        }
    }

    //        4.查询所有课程----用xclasslist直接
    public static void inquireAllCourse() throws Exception {
        XClassList xclassl = new XClassList();
        List<XClass> courses = xclassl.xClassSelectAll();
        for (int i = 0; i < courses.size(); i++) {
            XClass xcl = courses.get(i);
//            if(xcl.getCount() == null || xcl.getCount().equals("")) xcl.getCount() = 0;
//            int overplus = xcl.getCountMax() - xcl.getCount();
//            +"\t"+"课程剩余选课名额:"+overplus
            System.out.println(xcl.getId() +"\t"+xcl.getName()+"\t"+xcl.getTeacher()+"\t"+xcl.getCtime()+"\t"+xcl.getCplace()+"\t"+xcl.getCtype()+"\t"+xcl.getCount());
        }
    }
//        主要是用于查找课程
}
