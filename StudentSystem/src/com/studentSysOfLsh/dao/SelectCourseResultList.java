package com.studentSysOfLsh.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SelectCourseResultList {
    //  1.添加信息---选课
    public static Boolean scrAdd(String classId,String stuId) throws Exception {
        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = "insert into stu_class(class_id, stu_id) values(?,?);";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, classId);
        pstmt.setString(2, stuId);

        //5. 执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //7. 释放资源
        pstmt.close();
        conn.close();

        return count > 0;

    }
    //2.查询信息---查询选课结果---通过学生id获取到课程id  然后遍历获取对应的课程信息--通过xclass表的功能
    public static List<String> scrSelectBySId(String sid) throws Exception {
//        mysql> select class_id from stu_class where stu_id = "xxxx";
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        //2. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //3. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //4. 定义sql
        String sql = "select class_id from stu_class where stu_id = ?";

        //5. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        pstmt.setString(1,sid);
        ResultSet rs = null;

        //6. 执行sql
        rs = pstmt.executeQuery();

        //7. 处理结果 List<User> 封装User对象，装载List集合
//        User user = null;
//        List<User> users = new ArrayList<>();
        List<String> classIds = new ArrayList<>();
        while (rs.next()) {
            //获取数据
            String classId = rs.getString("class_id");
            //封装Brand对象

            classIds.add(classId);
            //装载集合
//            users.add(user);
        }


//        System.out.println(users);

        //7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();

        return classIds;
    }
    //2.1.查询信息---查询选课结果---通过课程id获取到学生id  然后遍历获取对应的学生信息--通过stu表的功能
    public static List<String> scrSelectByCid(String cid) throws Exception {
//        mysql> select class_id from stu_class where stu_id = "xxxx";
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        //2. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //3. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //4. 定义sql
        String sql = "select stu_id from stu_class where class_id = ?";

        //5. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        pstmt.setString(1,cid);
        ResultSet rs = null;

        //6. 执行sql
        rs = pstmt.executeQuery();

        //7. 处理结果 List<User> 封装User对象，装载List集合
//        User user = null;
//        List<User> users = new ArrayList<>();
        List<String> stuIds = new ArrayList<>();
        while (rs.next()) {
            //获取数据
            String stuId = rs.getString("stu_id");
            //封装Brand对象

            stuIds.add(stuId);
            //装载集合
//            users.add(user);
        }


//        System.out.println(users);

        //7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();

        return stuIds;
    }

    //3.删除信息---取消选课
//    需要信息---1.学生的id  和  2.课程的编码  id
    public static Boolean scrDelect(String sid,String cid) throws Exception {
//1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();
//        delete from stu_class where class_id = "体育" and stu_id = "刘书";
        //2. 定义SQL
        String sql = "delete from stu_class where class_id = ? and stu_id = ?";
        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, cid);
        pstmt.setString(2, sid);
        //5. 执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//    System.out.println(count > 0);


        //7. 释放资源
        pstmt.close();
        conn.close();

        return count > 0;

    }
}
