package com.songxl.testmock2.dao.impl;

import com.songxl.testmock2.dao.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl implements UserDAO {

    @Override
    public String getUserById(String id) {
        System.out.println("2,dao 层执行到了，入参为："+id);
        return id;
    }

    @Override
    public String getUserByName(String name) {
        System.out.println("2,dao 层执行到了，入参为："+name);
        return name;
    }
}
