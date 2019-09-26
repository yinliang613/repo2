package cn.suse.datasource.Druid;

import cn.suse.datasource.untils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用Druid工具类插入一条数据
 */
public class DruidDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //获取连接对象
            conn = JDBCUtils.getConnection();
            //定义SQL语句
            String sql = "INSERT INTO account VALUES (null,?,?)";
            //获取SQL执行对象
            pstmt = conn.prepareStatement(sql);
            //对?进行赋值
            pstmt.setString(1, "张杰");
            pstmt.setDouble(2, 5000);
            //执行sql
            int i = pstmt.executeUpdate();
            System.out.println(i);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(pstmt,conn);

        }
    }
}
