package com.studentSysOfLsh.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.studentSysOfLsh.pojo.User;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserList {
    //    1.查询所有信息
    public static List userSelectAll() throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        //2. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //3. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //4. 定义sql
        String sql = "select * from user";

        //5. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        ResultSet rs = null;

        //6. 执行sql
        rs = pstmt.executeQuery();

        //7. 处理结果 List<User> 封装User对象，装载List集合
        User user = null;
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            //获取数据
            String userName = rs.getString("user_name");
            String Pwd = rs.getString("password");
            String idCard = rs.getString("id_card");
            String phone = rs.getString("phone");
            //封装Brand对象
            user = new User();
            user.setName(userName);
            user.setPassword(Pwd);
            user.setIdCard(idCard);
            user.setUphone(phone);
            //装载集合
            users.add(user);
        }

//        System.out.println(users);

        //7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();

        return users;
    }
    //    1.1查找某个用户是否存在
    public static User userSelect(String username) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        //2. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //3. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //4. 定义sql
        String sql = "select * from user where user_name = ?";

        //5. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        pstmt.setString(1,username);
        ResultSet rs = null;

        //6. 执行sql
        rs = pstmt.executeQuery();

        //7. 处理结果 List<User> 封装User对象，装载List集合
        User user = null;
//        List<User> users = new ArrayList<>();
        while (rs.next()) {
            //获取数据
            String userName = rs.getString("user_name");
            String pwd = rs.getString("password");
            String idCard = rs.getString("id_card");
            String phone = rs.getString("phone");
            String sid = rs.getString("stu_id");
            //封装Brand对象
            user = new User();
            user.setName(userName);
            user.setPassword(pwd);
            user.setIdCard(idCard);
            user.setUphone(phone);
            user.setStuId(sid);
            //装载集合
//            users.add(user);
        }

//        System.out.println(users);

        //7. 释放资源
        rs.close();
        pstmt.close();
        conn.close();

        return user;
    }
    //    2.增加信息
    public static Boolean userAdd(String userName,String password,String idCard,String phone) throws Exception {
        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = "insert into user(user_name, password, id_card, phone) values(?,?,?,?);";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, userName);
        pstmt.setString(2, password);
        pstmt.setString(3, idCard);
        pstmt.setString(4, phone);

        //5. 执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//        System.out.println(count > 0);

        //7. 释放资源
        pstmt.close();
        conn.close();

        return count > 0;
    }

    //    3.修改信息
    public static Boolean userUpdate(String userName,String password,String idCard,String phone) throws Exception {
        // 接收页面提交的参数

        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = " update user \n" +
                "         set password  = ?,\n" +
                "         id_card = ?,\n" +
                "         phone     = ?\n" +
                "     where user_name = ?";

//        mysql> update user set password = "123123" where user_name = "malaipi";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, password);
        pstmt.setString(2, idCard);
        pstmt.setString(3, phone);
        pstmt.setString(4, userName);

        //5. 执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//        System.out.println(count > 0);


        //7. 释放资源
        pstmt.close();
        conn.close();

        return count > 0;
    }

//    4.删除信息
    public static Boolean userDelect(String userName) throws Exception {
    // 接收页面提交的参数
//    String userName = "malaipi";

    //1. 获取Connection
    //3. 加载配置文件
    Properties prop = new Properties();
    prop.load(new FileInputStream("./src/druid.properties"));
    //4. 获取连接池对象
    DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

    //5. 获取数据库连接 Connection
    Connection conn = dataSource.getConnection();

    //2. 定义SQL
    String sql = " delete from user where user_name = ?";

//        mysql> update user set password = "123123" where user_name = "malaipi";

    //3. 获取pstmt对象
    PreparedStatement pstmt = conn.prepareStatement(sql);

    //4. 设置参数
    pstmt.setString(1, userName);
    //5. 执行SQL
    int count = pstmt.executeUpdate(); // 影响的行数
    //6. 处理结果
//    System.out.println(count > 0);


    //7. 释放资源
    pstmt.close();
    conn.close();

    return count > 0;


}

    //5.进行学生信息注册
//    主要是修改功能---将学生的id添加到user表中；
//    mysql> update user set stu_id = "5017220410" where user_name = "stulsh";
    public static Boolean userToRegister(String stuId,String username) throws Exception {
//        添加语句
        //1. 获取Connection
        //3. 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));
        //4. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //5. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //2. 定义SQL
        String sql = "update user set stu_id = ? where user_name = ?";

        //3. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //4. 设置参数
        pstmt.setString(1, stuId);
        pstmt.setString(2, username);

        //5. 执行SQL
        int count = pstmt.executeUpdate(); // 影响的行数
        //6. 处理结果
//        System.out.println(count > 0);

        //7. 释放资源
        pstmt.close();
        conn.close();

        return count > 0;
    }

//    学号唯一性判断
    public static Boolean userConfirmSid(String sid) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("./src/druid.properties"));

        //2. 获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //3. 获取数据库连接 Connection
        Connection conn = dataSource.getConnection();

        //4. 定义sql
        String sql = "select * from user where stu_id = ?";

        //5. 获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // 设置？的值
        pstmt.setString(1,sid);

        //6. 处理结果
        ResultSet rs = pstmt.executeQuery();
        User user = null;
//        Boolean flag = true;
        while (rs.next()){
//            获取数据
            String id = rs.getString("stu_id");
            user = new User();
            user.setStuId(id);
        }

        //7. 释放资源
        pstmt.close();
        conn.close();
        if(user!=null){
            if(user.getStuId().equals(sid)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }

    }
}
