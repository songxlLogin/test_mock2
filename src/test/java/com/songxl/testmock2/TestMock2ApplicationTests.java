package com.songxl.testmock2;

import com.songxl.testmock2.controller.UserController;
import com.songxl.testmock2.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMock2ApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

    @Test
    public void contextLoads() {
        String id = userService.queryUserById("1234");
        System.out.println(id);
    }

    @Test
    public void tetController(){
        String id = userController.getUserById("sxl");
        System.out.println(id);
    }
}
