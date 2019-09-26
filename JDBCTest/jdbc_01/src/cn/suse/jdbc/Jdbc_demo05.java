package cn.suse.jdbc;

import cn.suse.jdbc.Utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc_demo05 {
    public static void main(String[] args) {
        List<Emp> list = new Jdbc_demo04().findAll();
        for (Emp e:list) {
            System.out.println(e);
        }
    }

    public List<Emp> findAll() {
        ResultSet rs = null;
        Statement stat = null;
        Connection conn = null;
        List<Emp> list = null;


        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql:///db2", "root", "root");
            conn = JDBCUtils.getConnection();
            stat = conn.createStatement();
            String sql = "select * from emp";
            rs = stat.executeQuery(sql);
            Emp emp = null;
            list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");
                emp = new Emp();
                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);
                list.add(emp);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs,stat,conn);
        }
        return list;
    }

}
