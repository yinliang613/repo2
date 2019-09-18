package cn.suse.mapper;

import cn.suse.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
}
