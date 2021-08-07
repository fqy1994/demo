package com.fqy.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fqy.demo.entiy.User;
import com.fqy.demo.mapper.UserMapper;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //查询所有记录
    @Test
    void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println("users = " + users);
    }

    //添加一条记录
    @Test
    void addUser() {
        User user = new User();
        user.setName("蔡依林");
        user.setAge(40);
        user.setEmail("ZhouJieLun@qq.com");

        int insert = userMapper.insert(user);
        System.out.println("insert = " + insert);

    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1423242826793824257L);
        user.setAge(120);

        int row = userMapper.updateById(user);
        System.out.println(row);

    }

    @Test
    void testOptimstickLocker() {
        User user = userMapper.selectById(1423252638944399361L);
        user.setAge(240);
        userMapper.updateById(user);
    }

    //多个id批量查询测试
    @Test
    void testSelectDemo1() {
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L, 2L, 3L));
        System.out.println(users);
    }

    //分页查询
    @Test
    void testPage() {
        //创建page对象
        //传入两个参数：当前页和每页显示记录数
        Page<User> page = new Page<>(2, 3);
        //调用mp分页查询的方法
        userMapper.selectPage(page, null);

        System.out.println("当前页=" + page.getCurrent());
        System.out.println("每页数据list集合=" + page.getRecords());
        System.out.println("每页显示记录数=" + page.getSize());
        System.out.println("总记录数=" + page.getTotal());
        System.out.println("总记页数=" + page.getPages());
        System.out.println("是否有下一页=" + page.hasNext());
        System.out.println("是否有上一页=" + page.hasPrevious());

    }

    //逻辑删除
    @Test
    void testDeleteById() {
        int result = userMapper.deleteById(1423847503319195650L);
        System.out.println("逻辑删除了几条记录:" + result);

    }

    //条件查询
    @Test
    void testSelectQuery() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //wrapper.ge("age",30);
        //wrapper.eq("name","WangYiBo");
        // wrapper.ne("name","WangYiBo");
        //wrapper.between("age",20,30);
        // wrapper.like("name","Wang");
        //wrapper.orderByDesc("age");
        //wrapper.last("limit 1");
        wrapper.select("id", "name");
        List<User> users = userMapper.selectList(wrapper);


        System.out.println("user=" + users);
    }

}
