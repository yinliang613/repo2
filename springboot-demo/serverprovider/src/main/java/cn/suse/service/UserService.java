package cn.suse.service;

import cn.suse.mapper.UserMapper;
import cn.suse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public User queryById(Long id){
       return this.userMapper.selectByPrimaryKey(id);
    }
}
