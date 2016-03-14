package com.studio.service.jwt;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/3/14.
 */
public class UtilsTest {

    @Test
    public void testGenerateWebToken() throws Exception {
        String str = Utils.generateWebToken("pdk");
        Assert.assertNotNull(str);
    }

    @Test
    public void testParseBody() throws Exception {
        String str = Utils.generateWebToken("pdk");
        Assert.assertEquals("pdk",Utils.parseBody(str));
    }
}