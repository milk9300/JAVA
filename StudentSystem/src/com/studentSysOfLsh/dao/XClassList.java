package com.studentSysOfLsh.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.studentSysOfLsh.pojo.XClass;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class XClassList {
    //    1.查询所有课程
    public static List xClassSelectAll() throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        //2. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //3. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //4. 定义sql
        String sql = "select * from xclass";

        //5. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        ResultSet rs = null;

        //6. 执行sql
        rs = pstmt.executeQuery();

        //7. 处理结果 List<User> 封装User对象，装载List集合
        XClass xclass = null;
        List<XClass> xclasses = new ArrayList<>();
        while (rs.next()) {
            //获取数据
            String id = rs.getString("id");
            String name = rs.getString("name");
            String tName = rs.getString("t_name");
            String cTime = rs.getString("c_time");
            String cPlace = rs.getString("c_place");
            String cType = rs.getString("c_type");
            Integer cGrade = rs.getInt("c_grade");
            Integer count = rs.getInt("count");

            //封装Brand对象
            xclass = new XClass();
            xclass.setId(id);
            xclass.setName(name);
            xclass.setTeacher(tName);
            xclass.setCtime(cTime);
            xclass.setCplace(cPlace);
            xclass.setCtype(cType);
            xclass.setCgrade(cGrade);
            xclass.setCount(count);
            //装载集合
            xclasses.add(xclass);
        }

//        System.out.println(users);

        //7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();

        return xclasses;
    }

    //      2.查询某个课程
    public static XClass classSelect(String id) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        //2. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //3. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //4. 定义sql
        String sql = "select * from xclass where id = ?";

        //5. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        pstmt.setString(1, id);
        ResultSet rs = null;

        //6. 执行sql
        rs = pstmt.executeQuery();

        //7. 处理结果 List<User> 封装User对象，装载List集合
        XClass xclass = null;
        while (rs.next()) {
            //获取数据
            String xcid = rs.getString("id");
            String cName = rs.getString("name");
            String tName = rs.getString("t_name");
            String cTime = rs.getString("c_time");
            String cPlace = rs.getString("c_place");
            String cType = rs.getString("c_type");
            Integer cGrade = rs.getInt("c_grade");
            Integer count = rs.getInt("count");

            //封装XClass对象
            xclass = new XClass();
            xclass.setId(xcid);
            xclass.setName(cName);
            xclass.setTeacher(tName);
            xclass.setCtime(cTime);
            xclass.setCplace(cPlace);
            xclass.setCtype(cType);
            xclass.setCgrade(cGrade);
            xclass.setCount(count);
        }

        //7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();

        return xclass;
    }

    //    3.添加课程
    public static Boolean classAdd(String id, String name, String tName, String cTime, String cPlace, String cType, Integer cGrade, Integer count) throws Exception {
        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = "insert into stu(id, name, t_name, c_time,c_place,c_type,c_grade,count) values(?,?,?,?,?,?,?,?);";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, id);
        pstmt.setString(2, name);
        pstmt.setString(3, tName);
        pstmt.setString(4, cTime);
        pstmt.setString(5, cPlace);
        pstmt.setString(6, cType);
        pstmt.setInt(7, cGrade);
        pstmt.setInt(8, count);

        //5. 执行SQL
        int num = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//        System.out.println(count > 0);

        //7. 释放资源
        pstmt.close();
        conn.close();

        return num > 0;
    }

    //    4.修改课程
    public static Boolean classUpdate(String id, String name, String tName, String cTime, String cPlace, String cType, Integer cGrade, Integer count) throws Exception {
        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();
//    String sql = "insert into stu(id, name, t_name,
//    c_time,c_place,c_type,c_grade,count) values(?,?,?,?,?,?,?,?);";

        //2. 定义SQL
        String sql = " update xclass \n" +
                "         set name  = ?,\n" +
                "         t_name = ?,\n" +
                "         c_time = ?,\n" +
                "         c_place = ?,\n" +
                "         c_type = ?,\n" +
                "         c_grade = ?,\n" +
                "         count     = ?\n" +
                "     where id = ?";
        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, name);
        pstmt.setString(2, tName);
        pstmt.setString(3, cTime);
        pstmt.setString(4, cPlace);
        pstmt.setString(5, cType);
        pstmt.setInt(6, cGrade);
        pstmt.setInt(7, count);
        pstmt.setString(8, id);

        //5. 执行SQL
        int num = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//        System.out.println(count > 0);

        //7. 释放资源
        pstmt.close();
        conn.close();

        return num > 0;
    }

//    5.删除课程
    public static Boolean classDelect(String id) throws Exception{
        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = " delete from xclass where id = ?";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, id);
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
