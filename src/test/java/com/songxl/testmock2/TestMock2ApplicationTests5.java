package com.songxl.testmock2;

import com.songxl.testmock2.dao.UserDAO;
import com.songxl.testmock2.dao.impl.UserDAOImpl;
import com.songxl.testmock2.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

import static org.mockito.Mockito.when;

/**
 * 测试私有方法和doAnswer的使用
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMock2ApplicationTests5 implements Answer<String> {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Mock
    private UserDAO userDAO = new UserDAOImpl();

    @Test
    public void testDoAnswer2() {
        Mockito.doAnswer(new TestMock2ApplicationTests5()).when(userDAO).getUserById("hi");
        String id = userServiceImpl.queryUserById("hi");
        Assert.assertEquals("hi:sxl", id);
    }

    @Override
    public String answer(InvocationOnMock invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        String id = (String) arguments[0];
        return id + ":sxl";
    }

    @Test
    public void testPrivate() {
        try {
            when(userDAO.getUserById("hi Micheal")).thenReturn("hello");
            Class<? extends UserServiceImpl> aClass = userServiceImpl.getClass();
            Method method = aClass.getDeclaredMethod("queryUserByOther", String.class);
            method.setAccessible(true);
//            UserServiceImpl userService = aClass.newInstance();
            String test = (String) method.invoke(userServiceImpl, "hi Micheal");
            Assert.assertEquals("测试结果：", "hello", test);
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

    @Test
    public void testDoAnswer() {
        when(userDAO.getUserById(Mockito.anyString())).thenAnswer((Answer<String>) invocation -> {
            //所有的入参
            Object[] arguments = invocation.getArguments();
            String id = (String) arguments[0];
            System.out.println("receive:" + id);
            return "hello";
        });
        String id = userServiceImpl.queryUserById("hi");
        Assert.assertEquals("hello", id);
    }


}
