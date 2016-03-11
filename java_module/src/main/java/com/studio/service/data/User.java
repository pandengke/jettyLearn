package com.studio.service.data;

import com.google.gson.Gson;

/**
 * Created by 1234 on 2016/3/9.
 */
public class User implements ITemplateData{
    private long id;
    public String uid;
    public String uname;
    public String password;

    public User(String uid, String uname, String password) {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
    }

    public User(){

    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
