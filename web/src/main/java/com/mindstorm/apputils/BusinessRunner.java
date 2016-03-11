package com.mindstorm.apputils;

import com.studio.service.data.User;
import com.studio.service.database.DBHelper;
import com.studio.service.templates.BaseTemplate;

public class BusinessRunner {

    public String queryUserInfo(int uid) {
        new DBHelper().hqlInsertUser(""+uid);
        return new BaseTemplate<>("insertOk").toString();
    }

    public boolean userLogin(int uid, String password) {
        User user = new DBHelper().queryUser(""+uid);
        if (password.equals(user.password)) {
            return true;
        } else {
            return false;
        }
    }

}