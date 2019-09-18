package cn.suse.service;

import cn.suse.mapper.UserMapper;
import cn.suse.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id){
        return  this.userMapper.selectByPrimaryKey(id);
    }
    public List<User> queryAll(){
        return this.userMapper.selectAll();
    }

    @Transactional
    public void deleteById(Long id){
        this.userMapper.deleteByPrimaryKey(id);
    }
}
