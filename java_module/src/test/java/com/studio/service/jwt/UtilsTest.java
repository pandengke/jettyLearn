package com.studio.service.jwt;

import io.jsonwebtoken.impl.crypto.MacProvider;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/3/14.
 */
public class UtilsTest {

    @Test
    public void testGenerateWebToken() throws Exception {
//        Assert.assertEquals("",Utils.generateWebToken("pdk"));
    }

    @Test
    public void testParseBody() throws Exception {
        String token = "eyJhbGciOiJIUzUxMiJ9." +
                "eyJhdWQiOiJtamoiLCJpYXQiOjE0NTgwMDc2NzMsImp0aSI6IjEyMzQiLCJzdWIiOiJzdWJqZWN0In0." +
                "3NUDLGOjfa4khpPRe-1KqvsXk8YMSj2mBQyQkTd0ndOQFQtVWR_s2soPxHppFVXVPHIllnm45RQjsBD7IVtXBQ";
        Assert.assertEquals("pdk",Utils.parseBody(token));
    }
}