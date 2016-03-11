package com.studio.service.database;

import com.studio.service.data.ITemplateMsg;
import com.studio.service.data.User;
import com.studio.service.templates.BaseTemplate;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Administrator on 2016/3/10.
 */
public class DBHelperTest {

    @org.junit.Test
    public void testQueryUser() throws Exception {
//        User user = new DBHelper().queryUser("123");
//        BaseTemplate<ITemplateMsg, User> temp = new BaseTemplate<>(true, null, user, "000");
//        Assert.assertEquals(temp.toString(), temp);
    }

    @org.junit.Test
    public void testCreateDB() throws Exception {

    }

    @org.junit.Test
    public void testInsertUser() throws Exception {

    }

    @Test
    public void testHqlUser() throws Exception {
//        User user = new DBHelper().hqlUser("123");
//        Assert.assertEquals("123", user.uid);
    }

    @Test
    public void testHqlInsertUser() throws Exception {
        Assert.assertEquals(true,new DBHelper().hqlInsertUser("12345"));
    }
}