package com.mindstorm.apputils;

import com.studio.service.database.DBHelper;
import com.studio.service.templates.BaseTemplate;

public class BusinessRunner {

    public String queryUserInfo(int uid) {
        new DBHelper().hqlInsertUser("" + uid);
        return new BaseTemplate<>("insertOk").toString();
    }

}