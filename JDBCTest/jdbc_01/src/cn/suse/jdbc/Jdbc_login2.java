package cn.suse.jdbc;

import cn.suse.jdbc.Utils.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

public class Jdbc_login2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String name = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();

        boolean flag = new Jdbc_login2().login(name,password);
        if(flag){
            System.out.println("登录成功！");
        }else {
            System.out.println("登录失败！");
        }

    }

    public boolean login(String name, String password) {

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if(name == null ||password ==null){
            return false;
        }
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from user where name = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,password);

            //select * from user where name = '1' or '1' ='1' and password = '"+password+"' "
            rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, pstmt, conn);
        }

        return false;
    }
}
