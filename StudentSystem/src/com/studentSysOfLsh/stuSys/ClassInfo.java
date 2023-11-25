package com.studentSysOfLsh.stuSys;

import com.studentSysOfLsh.dao.SelectCourseResultList;
import com.studentSysOfLsh.dao.StudentList;
import com.studentSysOfLsh.dao.XClassList;
import com.studentSysOfLsh.pojo.Student;
import com.studentSysOfLsh.pojo.XClass;

import java.util.List;
import java.util.Scanner;

public class ClassInfo {
    Scanner sc = new Scanner(System.in);
    XClassList xclassl = new XClassList();
    SelectCourseResultList scrl = new SelectCourseResultList();
    public void classFunction() throws Exception {
        while (true) {
            System.out.println("-------课程信息功能-------");
            System.out.println("1.添加课程 ");
            System.out.println("2.查询课程信息 ");
            System.out.println("3.删除课程 ");
            System.out.println("4.修改课程信息 ");
            System.out.println("5.查询课程学员信息 ");
            System.out.println("0.退出 ");

            String choice = sc.next();

            switch (choice) {
//                对应5个函数
                case "1" -> addClass(xclassl);
                case "2" -> selectClass(xclassl);
                case "3" -> delectClass(xclassl);
                case "4" -> updateClass(xclassl);
                case "5" -> selectByClassOfStuInfo(scrl,xclassl);
                case "0" -> {
                    return;
                }
                default -> System.out.println("输入有误");
            }
        }
    }

    //        1.添加课程
    public static void addClass(XClassList xclassl) throws Exception {
        Scanner sc = new Scanner(System.in);
        XClass xc = new XClass();
        System.out.println("请输入添加课程的编号");
        String xcid = sc.next();
        XClass xcy = xclassl.classSelect(xcid);
        if(xcy != null){
            System.out.println("该课程编号已经存在！");
            return;
        }
        xc.setId(xcid);
        System.out.println("请输入添加课程的名称");
        String xcname = sc.next();
        xc.setName(xcname);
        System.out.println("请输入添加课程的代课老师");
        String xcteacher = sc.next();
        xc.setTeacher(xcteacher);
        System.out.println("请输入添加课程的上课时间");
        String xctime = sc.next();
        xc.setCtime(xctime);
        System.out.println("请输入添加课程的上课地点");
        String xcplace = sc.next();
        xc.setCplace(xcplace);
        System.out.println("请输入添加课程的类别");
        String xctype = sc.next();
        xc.setCtype(xctype);
        System.out.println("请输入添加课程的学分");
        int xcgrade = sc.nextInt();
        xc.setCgrade(xcgrade);
        System.out.println("请输入添加课程的总人数");
        int xccount = sc.nextInt();
        xc.setCount(xccount);

        Boolean b = xclassl.classAdd(xc.getId(), xc.getName(), xc.getTeacher(), xc.getCtime(), xc.getCplace(), xc.getCtype(), xc.getCgrade(), xc.getCount());
        if(b){
            System.out.println("课程id:"+xcid+"已经被添加");
        }
    }

    //      2.查询课程信息
    public static void selectClass(XClassList xclassl) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要查找的课程编码");
        String cid= sc.next();
        XClass xc = xclassl.classSelect(cid);
        if(xc != null){
            System.out.println("----------课程id:"+cid+"的信息----------");
            System.out.println(xc.getId()+"\t"+xc.getName()+"\t"+xc.getTeacher()+"\t"+xc.getCtime()+"\t"+xc.getCplace()+"\t"+xc.getCtype()+"\t"+xc.getCgrade()+"\t"+xc.getCount());
        }else {
            System.out.println("查找不到该课程信息！");
        }
    }

    //      3.删除课程
    public static void delectClass(XClassList xclassl) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要删除的课程编码");
        String cid= sc.next();
        Boolean b = xclassl.classDelect(cid);
        if(b){
            System.out.println("课程编码:"+cid+"的信息已被删除");
        }else {
            System.out.println("查找不到该课程信息！");
        }
    }

    //      4.修改课程信息
    public static void updateClass(XClassList xclassl) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要修改信息的课程编号: ");
        String cid = sc.next();
        XClass mxc = xclassl.classSelect(cid);

        if(mxc != null){
            updateLoop:while (true){
                System.out.println("-----请输入修改的方式:------");
                System.out.println("1.修改课程名称 - 2.修改代课教师 - 3.修改上课时间 - 4.修改上课地点 - 5.修改课程类别 - 6.修改课程学分 - 7.修改课程总人数");
                System.out.println("0.退出操作 ");
                System.out.println("-----------------------------------------");
                String choice = sc.next();
                switch (choice){
                    case "1" ->{
                        System.out.println("请输入课程名称");
                        String mname = sc.next();
                        mxc.setId(mname);
                    }
                    case "2" ->{
                        System.out.println("请输入代课教师");
                        String mteacher = sc.next();
                        mxc.setTeacher(mteacher);
                    }
                    case "3" ->{
                        System.out.println("请输入上课时间");
                        String mtime = sc.next();
                        mxc.setCtime(mtime);
                    }
                    case "4" ->{
                        System.out.println("请输入上课地点");
                        String mplace = sc.next();
                        mxc.setCplace(mplace);
                    }
                    case "5" ->{
                        System.out.println("请输入课程类别");
                        String mtype = sc.next();
                        mxc.setCtype(mtype);
                    }
                    case "6" ->{
                        System.out.println("请输入课程学分");
                        int mgrade = sc.nextInt();
                        mxc.setCgrade(mgrade);
                    }
                    case "7" ->{
                        System.out.println("请输入课程总人数");
                        int mcount = sc.nextInt();
                        mxc.setCount(mcount);
                    }
                    case "0" ->{
                        break updateLoop;
                    }
                    default -> System.out.println("输入有误！");
                }
            }
            Boolean b = xclassl.classUpdate(mxc.getId(), mxc.getName(), mxc.getTeacher(), mxc.getCtime(), mxc.getCplace(), mxc.getCtype(), mxc.getCgrade(), mxc.getCount());
            if(b){
                System.out.println("课程编码为"+cid+"的课程信息已经成功修改");
            }else {
                System.out.println("操作异常，修改失败");
            }
        }else {
            System.out.println("查询不到课程编码为"+cid+"的课程信息");
        }
    }
//        5.查询课程学员信息
//            等写完学生功能的选课再回来补，需要一个新表，学生信息和课程的关系
//    从stu_class表中获取stu的id集合，然后从stu表中查询
    public static void selectByClassOfStuInfo(SelectCourseResultList scrl,XClassList xclassl) throws Exception{
        Scanner sc = new Scanner(System.in);
        StudentList stul = new StudentList();
        System.out.println("请输入查询课程编码: ");
        String cid = sc.next();
        XClass xClass = xclassl.classSelect(cid);
        if(xClass != null){
            List<String> stuIds = scrl.scrSelectByCid(cid);
            System.out.println("------课程编码---"+cid+"---的课程信息:------");
            for (int i = 0; i < stuIds.size(); i++) {
                String stuId = stuIds.get(i);
                Student stu = stul.stuSelect(stuId);
                if(stu != null){
                    System.out.println(stu.getSid()+"\t"+stu.getName()+"\t"+stu.getAge()+"\t"+stu.getGender()+"\t"+stu.getCollege()+"\t"+stu.getProfession()+"\t"+stu.getPhone());
                }
            }
        }else {
            System.out.println("该课程不存在！");
        }

    }

}
