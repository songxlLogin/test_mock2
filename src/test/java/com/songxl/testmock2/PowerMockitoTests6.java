package com.songxl.testmock2;

import com.songxl.testmock2.controller.UserController;
import com.songxl.testmock2.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Method;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserController.class)
public class PowerMockitoTests6 {

    @Mock
    private UserServiceImpl serviceImpl;

    @InjectMocks
    private UserController controller;

    /**
     * mock静态方法
     */
    @Test
    public void testStatic(){
        PowerMockito.mockStatic(UserController.class);
        when(UserController.getStaticName(Mockito.anyString())).thenReturn("sxl");
        String name = UserController.getStaticName("sxl");
        Assert.assertEquals("sxl","sxl");
    }

    /**
     * mock私有方法(只是在方法执行时有调用私有方法)
     * @throws Exception
     */
    @Test
    public void testPrivate() throws Exception {
        UserController controller = PowerMockito.spy(this.controller);
        PowerMockito.when(controller,"check",any()).thenReturn(true);
        String name = controller.getPrivateName("sxl");
        Assert.assertEquals("private 被mock 了", name);
    }

    /**
     * mock私有方法（直接mock私有方法）
     * @throws Exception
     */
    @Test
    public void testPrivate2() throws Exception {
        Method method = PowerMockito.method(UserController.class, "say", String.class);
        Object o = method.invoke(controller, "hello Micheal");
        Assert.assertEquals("sxl say: hello Micheal",o);
    }


    /**
     * mock final
     */
    @Test
    public void testFinal(){
        Mockito.when(serviceImpl.queryUserById(any())).thenReturn("sxl");
        String id = controller.getUserById("sxl");
        Assert.assertEquals("sxl",id);
    }
}
