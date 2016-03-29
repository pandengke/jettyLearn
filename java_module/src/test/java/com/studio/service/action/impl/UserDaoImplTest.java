package com.studio.service.action.impl;

import com.studio.service.action.UserDao;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016/3/18.
 */
public class UserDaoImplTest {

    @Test
    public void testLogin() throws Exception {
        UserDao userDao = new UserDaoImpl();
        String token = userDao.login("zb", "zbpass");
        Assert.assertNotNull(token);
    }

//    @Test
//    public void testRegister() throws Exception {
//        UserDao userDao = new UserDaoImpl();
//        Assert.assertEquals(true, userDao.register("pdk", "pdkpass"));
//    }
//
//    @Test
//    public void testRefreshToken() throws Exception {
//
//    }
}