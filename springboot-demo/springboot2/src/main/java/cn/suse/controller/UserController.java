package cn.suse.controller;

import cn.suse.pojo.User;
import cn.suse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("hello")
    @ResponseBody
    public String test(){
        return "user hello";
    }
    @GetMapping("{id}")
    @ResponseBody
    public User queryUserById(@PathVariable("id")Long id){
        return this.userService.queryById(id);
    }
    @GetMapping("all")
    public String queryAll(Model model){
        List<User> users = this.userService.queryAll();
        model.addAttribute("users",users);
        return "users";
    }
}
