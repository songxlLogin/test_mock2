package com.songxl.testmock2;

import com.songxl.testmock2.dao.impl.UserDAOImpl;
import com.songxl.testmock2.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

/**
 * doThrow的使用
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMock2ApplicationTests7 {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl();

    @Mock
    private UserDAOImpl userDAO = new UserDAOImpl();





    /**
     *  测试用例：
     *  thenThrow的使用
     *  参数：必须指定一个异常。
     */
    @Test
    public void test1() {
        RuntimeException exception = new RuntimeException("test exception");
        when(userDAO.getUserByName(Mockito.anyString())).thenThrow(exception);
        userService.queryUserByName("");
    }




}
