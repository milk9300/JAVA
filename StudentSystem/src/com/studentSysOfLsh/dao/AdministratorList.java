package com.studentSysOfLsh.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.studentSysOfLsh.pojo.Administrator;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class AdministratorList {
    //    管理员需要的操作
//    1.登录时进行查找
    public static Administrator adminSelect(String name) throws Exception {
//        1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

//        2.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

//        3.获取数据库的连接
        Connection conn = dataSource.getConnection();

//        4.定义sql
        String sql = "select * from intendant where user_name = ?";

//        5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

//        设置？值
        pstmt.setString(1, name);

        ResultSet rs = null;
//        6.执行sql语句
        rs = pstmt.executeQuery();

//        7.处理结果
        Administrator admin = null;
        while (rs.next()) {
//            获取结果数据
            String userName = rs.getString("user_name");
            String pwd = rs.getString("password");
            String rank = rs.getString("rank");

//            封装对象
            admin = new Administrator();
            admin.setName(userName);
            admin.setPassword(pwd);
            admin.setRank(rank);

        }

//       8. 进行资源的释放
        rs.close();
        pstmt.close();
        conn.close();

//        9.返回值---这里查询后返回一个对象
        return admin;
    }

    //    2.添加数据
    public static Boolean adminAdd(String userName, String password) throws Exception {
        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = "insert into intendant(user_name, password,rank) values(?,?,?);";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, userName);
        pstmt.setString(2, password);
        pstmt.setString(3, "administrator");

        //5. 执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//        System.out.println(count > 0);

        //7. 释放资源
        pstmt.close();
        conn.close();

        return count > 0;
    }

}
