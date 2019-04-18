package com.songxl.testmock2.service.impl;

import com.songxl.testmock2.dao.UserDAO;
import com.songxl.testmock2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //使用spy多层调用测试
    @Override
    public String queryUserById(String id) {
        System.out.println("1.进入service层， 入参："+id);
        String userById = userDAO.getUserById(id);
        System.out.println("4.调用dao返回值："+userById);
        return userById;
    }

    @Override
    public String queryUserByName(String name) {
        String daoUserByName = null;
        try {
            System.out.println("1.service (by name)"+name);
            daoUserByName = userDAO.getUserByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daoUserByName;
    }

    private String queryUserByOther(String test) {
        System.out.println("1.进入service层， 入参："+test);
        String testId = userDAO.getUserById(test);
        System.out.println("3.调用dao返回值："+testId);
        return testId;
    }
}
