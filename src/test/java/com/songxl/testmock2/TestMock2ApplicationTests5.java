package com.songxl.testmock2;

import com.songxl.testmock2.dao.UserDAO;
import com.songxl.testmock2.dao.impl.UserDAOImpl;
import com.songxl.testmock2.service.UserService;
import com.songxl.testmock2.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.mockito.Mockito.when;

/**
 * 本测试用例中的service、dao 就是springboot中很常见的service调dao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMock2ApplicationTests5 {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Mock
    private UserDAO userDAO = new UserDAOImpl();

    @Test
    public void testPrivate(){
        try {
            when(userDAO.getUserById("hi Micheal")).thenReturn("hello");
            Class<? extends UserServiceImpl> aClass = userServiceImpl.getClass();
            Method method = aClass.getDeclaredMethod("queryUserByOther", String.class);
            method.setAccessible(true);
//            UserServiceImpl userService = aClass.newInstance();
            String test = (String) method.invoke(userServiceImpl, "hi Micheal");
            Assert.assertEquals("测试结果：","hello",test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSpy() {
        when(userDAO.getUserById("hi Micheal")).thenReturn("hello");
        String id = userServiceImpl.queryUserById("hi Micheal");
        Assert.assertEquals("hi", id);
    }






}
