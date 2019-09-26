package cn.suse.datasource.JDBC_Template;

import cn.suse.datasource.untils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class Jdbctemplatedemo1 {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "UPDATE account set balance = 2000 WHERE id =?";

        int update = template.update(sql, 2);
        System.out.println(update);
    }

}
