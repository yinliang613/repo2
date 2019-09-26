package cn.suse.jdbc;

import cn.suse.jdbc.Utils.JDBCUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Jdbc_demo06 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///db4", "root", "root");
            conn.setAutoCommit(false);
            String sql1 = "update account set balance = balance - ? where id = ?";
            String sql2 = "update account set balance = balance + ? where id = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setDouble(1,500);
            pstmt1.setInt(2,1);
            pstmt1.executeUpdate();
            //制造异常
            int i = 1/0;
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setDouble(1,500);
            pstmt2.setInt(2,2);
            pstmt2.executeUpdate();
            //无异常，提交
            conn.commit();

        } catch (Exception e) {
            try {
                //出现异常，回滚
                if(conn != null)
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            JDBCUtils.close(pstmt1,conn);
            JDBCUtils.close(pstmt2,null);
        }
    }
}
