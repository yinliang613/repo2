package cn.suse.jdbc;

import cn.suse.jdbc.Utils.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * 模拟注册初始版
 */
public class Jdbc_register {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name = sc.nextLine();
        System.out.println("请输入密码：");
        String password1 = sc.nextLine();
        System.out.println("请再次输入密码：");
        String password2 = sc.nextLine();
        if (!password1.equals(password2)) {
            System.out.println("两次密码不一致");
        } else {
            boolean flag = new Jdbc_register().register(name, password1);
            if (flag) {
                System.out.println("注册成功！");
            } else {
                System.out.println("注册失败！");
            }


        }
    }

    public boolean register(String name, String password) {
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        if (name == null || password == null) {
            System.out.println("密码和名字不能为空");
            return false;
        }


        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from user where name = ?";
            pstmt1 = conn.prepareStatement(sql);
            pstmt1.setString(1,name);
            rs = pstmt1.executeQuery();
            if (rs.next()) {
                System.out.println("用户名已存在！");
                return false;
            }
            String sql2 = "insert into user values (null,?,?)";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1,name);
            pstmt2.setString(2,password);

            int i = pstmt2.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt1, conn);
            JDBCUtils.close(null,pstmt2,null);
        }

        return false;
    }


}
