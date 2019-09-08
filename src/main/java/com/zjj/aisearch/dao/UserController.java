package com.zjj.aisearch.dao;

import com.zjj.aisearch.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User users) {
        User user = new User();
        user.setUsername(users.getUsername());
        user.setId(users.getId());
        user.setPassword(users.getPassword());
        user.setAge(users.getAge());
        return String.valueOf(userDao.save(user).getId());// 返回id做验证
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(Integer id) {
        userDao.deleteById(id);
        return "Success!";
    }

    @PutMapping("/updateUser")
    public String updateUser(int id, String username, String password, String age) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setAge(age);
        return String.valueOf(userDao.save(user).getId());// 返回id做验证
    }

    @GetMapping("/getUser")
    public User getUser(Integer id) {
        return userDao.findById(id).get();
    }

    @GetMapping("/getAllUsers")
    public Iterable<User> getAllUsers() {
        return userDao.findAll();
    }
}