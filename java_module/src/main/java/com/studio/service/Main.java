package com.studio.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2016/3/21.
 */
public class Main {
    public static void main(String[] args) {
        Subject currentUser = SecurityUtils.getSubject();

        Session session = currentUser.getSession();
        session.setAttribute( "someKey", "aValue" );
    }
}
