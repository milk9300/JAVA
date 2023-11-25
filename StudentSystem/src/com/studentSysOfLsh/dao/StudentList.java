package com.studentSysOfLsh.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.studentSysOfLsh.pojo.Student;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class StudentList {
    //    1.查找用户是否存在
//    1.通过学号
    public static Student stuSelect(String sid) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        //2. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //3. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //4. 定义sql
        String sql = "select * from stu where sid = ?";

        //5. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        pstmt.setString(1, sid);
        ResultSet rs = null;

        //6. 执行sql
        rs = pstmt.executeQuery();

        //7. 处理结果 List<User> 封装User对象，装载List集合
        Student stu = null;
//        List<User> users = new ArrayList<>();
        while (rs.next()) {
            //获取数据
            String stuId = rs.getString("sid");
            String name = rs.getString("name");
            Integer age = rs.getInt("age");
            String gender = rs.getString("gender");
            String college = rs.getString("college");
            String profession = rs.getString("profession");
            String phone = rs.getString("phone");

            //封装Brand对象
            stu = new Student();
            stu.setSid(stuId);
            stu.setName(name);
            stu.setAge(age);
            stu.setGender(gender);
            stu.setCollege(college);
            stu.setProfession(profession);
            stu.setPhone(phone);

            //装载集合
//            users.add(user);
        }

//        System.out.println(users);

        //7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();

        return stu;
    }

    //    2.添加学生
    public static Boolean stuAdd(String stuId, String name, Integer age, String gender, String college, String profession, String phone) throws Exception {
        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = "insert into stu(sid, name, age, gender,college,profession,phone) values(?,?,?,?,?,?,?);";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, stuId);
        pstmt.setString(2, name);
        pstmt.setInt(3, age);
        pstmt.setString(4, gender);
        pstmt.setString(5, college);
        pstmt.setString(6, profession);
        pstmt.setString(7, phone);

        //5. 执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//        System.out.println(count > 0);

        //7. 释放资源
        pstmt.close();
        conn.close();

        return count > 0;

    }

    //    3.修改学生信息
    public static Boolean stuUpdate(String stuId, String name, Integer age, String gender, String college, String profession, String phone) throws Exception {
//         接收页面提交的参数

        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = " update stu \n" +
                "         set name  = ?,\n" +
                "         age = ?,\n" +
                "         gender = ?,\n" +
                "         college = ?,\n" +
                "         profession = ?,\n" +
                "         phone     = ?\n" +
                "     where sid = ?";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.setString(3, gender);
        pstmt.setString(4, college);
        pstmt.setString(5, profession);
        pstmt.setString(6, phone);
        pstmt.setString(7, stuId);

        //5. 执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//        System.out.println(count > 0);


        //7. 释放资源
        pstmt.close();
        conn.close();

        return count > 0;
    }
//    4.删除课程
    public static Boolean stuDelect(String sid) throws Exception{
        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = " delete from stu where sid = ?";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, sid);
        //5. 执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//        System.out.println(count > 0);

        //7. 释放资源
        pstmt.close();
        conn.close();

        return count >0;
    }
}
