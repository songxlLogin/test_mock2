package com.songxl.testmock2;

import com.songxl.testmock2.dao.impl.UserDAOImpl;
import com.songxl.testmock2.database.UserDataBase;
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

/**
 * spy的使用
 * 本测试用例中的service、dao 就是springboot中很常见的service调dao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMock2ApplicationTests4 {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Spy
    private UserDAOImpl userDAO = new UserDAOImpl();

    @Mock
    private UserDataBase userDataBase;

    /**
     * 此方法中没有加 when().thenReturn();
     * 结果：会正常调用dao层，返回实际执行的数据
     */
    @Test
    public void testSpy() {
        String id = userService.queryUserById("hi");
        Assert.assertEquals("hi", id);
    }

    /**
     *  此方法中加 when().thenReturn();
     *  结果：会正常调用dao层，但是按照thenReturn()的值返回
     */
    @Test
    public void test1() {
        when(userDAO.getUserById("hi Micheal")).thenReturn("hello");
        String id = userService.queryUserById("hi");
        Assert.assertEquals("hello", id);
    }


    /**
     * 使用spy
     * result：
     * 2,dao (by id)songxl
     * songxl
     */
    @Test
    public void test3() {
        when(userDAO.getUserById("hi")).thenReturn("hello");
        String id = userDAO.getUserById("hi");
        Assert.assertEquals("hello", id);
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

    @Test
    public void test5(){
        userDAO.setUserDataBase(userDataBase);
        when(userDataBase.getUserById(Mockito.anyString())).thenReturn("songxl");
        String id = userService.queryUserById("songxl");
        System.out.println(id);
    }
}
