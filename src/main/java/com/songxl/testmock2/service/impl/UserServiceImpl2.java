package com.songxl.testmock2.service.impl;

import com.songxl.testmock2.service.UserService;

public class UserServiceImpl2 implements UserService {
    @Override
    public String queryUserById(String id) {
        System.out.println("1.进入service层， 入参："+id);
        return id;
    }

    @Override
    public String queryUserByName(String name) {
        return null;
    }
}
