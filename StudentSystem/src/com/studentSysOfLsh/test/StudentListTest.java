package com.studentSysOfLsh.test;

import com.studentSysOfLsh.dao.StudentList;
import com.studentSysOfLsh.pojo.Student;

public class StudentListTest {
    public static void main(String[] args) throws Exception {
        StudentList stul = new StudentList();
        Student student = stul.stuSelect("123123");
        System.out.println(student.getName()+", "+student.getProfession());
        stul.stuDelect("123123");
    }


}
