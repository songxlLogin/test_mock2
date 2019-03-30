package com.songxl.testmock2.database;

import org.springframework.stereotype.Service;

@Service
public class UserDataBaseImpl implements UserDataBase {
    @Override
    public String getUserById(String id) {
        System.out.println("2,database 层执进入了，入参为："+id);
        return id;
    }

    @Override
    public String getUserByName(String name) {
        System.out.println("2,database 层进入了，入参为："+name);
        return name;
    }
}
