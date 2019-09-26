package cn.suse.jdbc;

import cn.suse.jdbc.Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 模拟登录
 */

public class Jdbc_login {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();

        boolean flag = new Jdbc_login().login(name,password);
        if(flag){
            System.out.println("登录成功！");
        }else {
            System.out.println("登录失败！");
        }

    }

    public boolean login(String name, String password) {

        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        if(name == null ||password ==null){
            return false;
        }
        try {
            conn = JDBCUtils.getConnection();
            stat = conn.createStatement();
            String sql = "select * from user where name = '"+name+"' and password = '"+password+"' ";
            //select * from user where name = '1' or 1=1 or 1 = '1' and password = '"+password+"' "
            rs = stat.executeQuery(sql);
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stat, conn);
        }

        return false;
    }
}
