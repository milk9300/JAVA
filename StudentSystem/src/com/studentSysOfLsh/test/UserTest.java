package com.studentSysOfLsh.test;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.studentSysOfLsh.pojo.User;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//使用数据库遍历user
public class UserTest {
    public List userSelectAll() throws Exception {
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
        while (rs.next()){
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

}
