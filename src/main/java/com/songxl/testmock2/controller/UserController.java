package com.songxl.testmock2.controller;

import com.songxl.testmock2.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserController {
//    @Autowired
//    private UserService service;

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    public String getUserById(String id) {
        service.queryUserById(id);
        return id;
    }

    public String returnName() {
        return getStaticName("sxl");
    }

    public static String getStaticName(String name) {
        return "A_" + name;
    }

    public String getPrivateName(String name) {

        if (publicCheck()) {
            return "public 被mock 了";
        }
        if (check(name)) {
            return "private 被mock 了";
        }
        return "A_" + name;
    }

    public boolean publicCheck() {
        return false;
    }

    private boolean check(String name) {
        System.out.println("access private method");
        return false;
    }

    private String say(String content) {
        return "sxl say: " + content;
    }

}
