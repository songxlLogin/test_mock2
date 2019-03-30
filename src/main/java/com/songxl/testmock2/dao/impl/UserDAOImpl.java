package com.songxl.testmock2.dao.impl;

import com.songxl.testmock2.dao.UserDAO;
import com.songxl.testmock2.database.UserDataBase;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserDAOImpl implements UserDAO {

    @Autowired
    private UserDataBase userDataBase;

    @Override
    public String getUserById(String id) {
        System.out.println("2. 进入dao 层，入参为：" + id);
        String userById = userDataBase.getUserById(id);
        System.out.println("3.调用database返回结果：" + id);
        return userById;
    }

    @Override
    public String getUserByName(String name) {
        System.out.println("2,dao 层执进入了，入参为："+name);
        return name;
    }
}
