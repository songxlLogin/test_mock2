package com.songxl.testmock2;

import com.songxl.testmock2.dao.UserDAO;
import com.songxl.testmock2.service.UserService;
import com.songxl.testmock2.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMock2ApplicationTests3 {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Spy
    private UserDAO userDAO2;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Mock
    private UserDAO userDAO;


    @Test
    public void test1() {
        when(userDAO.getUserById(Mockito.anyString())).thenReturn("hi");
        String id = userService.queryUserById("123");
        Assert.assertEquals("hi", id);
    }


    @Test
    public void test2() {
        when(userDAO2.getUserById("hi")).thenReturn("hello");
        String id = userService.queryUserById("hi");
        System.out.println(id);
        Assert.assertEquals("hi", id);
    }


    /**
     * 使用spy
     * result：
     * 2,dao (by id)songxl
     * songxl
     */
    @Test
    public void test3() {
        when(userDAO2.getUserById("songxl")).thenReturn("songxl");
        String id = userDAO2.getUserById("songxl");
        System.out.println(id);
    }

    /**
     * 使用mock
     * result：
     * songxl
     */
    @Test
    public void test4() {
        when(userDAO.getUserById("songxl")).thenReturn("songxl");
        String id = userDAO.getUserById("songxl");
        System.out.println(id);
    }
}
