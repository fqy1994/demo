package com.fqy.demo;

import com.fqy.demo.entiy.User;
import com.fqy.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
    }
    @Test
    void addUser() {
        User user = new User();
        user.setName("lucy");
        user.setAge(30);
        user.setEmail("lucy@qq.com");

        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);

    }

}
