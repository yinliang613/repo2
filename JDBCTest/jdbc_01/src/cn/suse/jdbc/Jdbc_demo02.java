package cn.suse.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc_demo02 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //创建sql语句
            String sql = "insert into account values (null,'王五',3000)";
            //连接数据库,创建数据库连接connection对象
             conn = DriverManager.getConnection("jdbc:mysql:///db3", "root", "root");
            //创建执行sql的Statement对象
             statement = conn.createStatement();
            //执行SQL语句
            int count = statement.executeUpdate(sql);
            //处理结果
            if(count >0){
                System.out.println("执行成功！");
            }else{
                System.out.println("执行失败！");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //释放资源
            if(statement!= null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
