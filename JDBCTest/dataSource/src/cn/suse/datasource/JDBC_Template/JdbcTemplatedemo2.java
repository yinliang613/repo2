package cn.suse.datasource.JDBC_Template;

import cn.suse.datasource.untils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;


public class JdbcTemplatedemo2 {
    //1. 获取JDBCTemplate对象
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 1. 修改1号数据的 salary 为 10000
     */
    @Test
    public void test1() {
        String sql = "UPDATE emp SET salary = 10000 WHERE id = 1001";
        int update = template.update(sql);
        System.out.println(update);
    }

    /**
     * 2. 添加一条记录
     */
    @Test
    public void test2() {
        String sql = "INSERT INTO emp(id,ename,dept_id) VALUES(?,?,?)";
        int update = template.update(sql, 1005, "郭靖", 10);
        System.out.println(update);

    }

    /**
     * 3.删除刚才添加的记录
     */
    @Test
    public void test3() {
        String sql = "DELETE FROM emp WHERE id = 1015";
        int update = template.update(sql);
        System.out.println(update);
    }

    /**
     * 4.查询id为1001的记录，将其封装为Map集合
     * 注意：这个方法查询的结果集长度只能是1
     */
    @Test
    public void test4() {
        String sql = "SELECT * FROM emp WHERE id = 1001";
        Map<String, Object> map = template.queryForMap(sql);
        System.out.println(map);

       /* for (String s : map.keySet()) {
            System.out.println(s+":"+map.get(s));
        }*/
    }

    /**
     * 5. 查询所有记录，将其封装为List
     */
    @Test
    public void test5() {
        String sql = "SELECT * FROM emp";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map map : list) {
            System.out.println(map);
        }
    }

    /**
     * 6. 查询所有记录，将其封装为Emp对象的List集合
     */
    @Test
    public void test6() {
        String sql = "select * from emp";
        List<Emp> query = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (Emp emp : query) {
            System.out.println(emp);
        }

    }

    /**
     * 7. 查询总记录数
     */
    @Test
    public void test7() {
        String sql = "select count(id) from emp";
        Long aLong = template.queryForObject(sql, Long.class);
        System.out.println(aLong);
    }


}
