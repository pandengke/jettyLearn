package com.studio.service.data;

import com.google.gson.Gson;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by 1234 on 2016/3/9.
 */
@javax.persistence.Table(name = "orcl_user")
@Entity
public class User {
    private long id;
    public String uid;
    public String uname;
    public String password;

    public User(String uid, String uname, String password) {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
    }

    public User() {

    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Column(name = "orc_uname")
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Column(name = "orc_password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @GenericGenerator(strategy = "increment", name = "generator")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "orcl_uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
