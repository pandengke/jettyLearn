package com.studio.service.data;

import com.google.gson.Gson;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by 1234 on 2016/3/9.
 */
@javax.persistence.Table(name = "orcl_user")
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(strategy = "org.hibernate.id.IncrementGenerator", name = "generator")
    private long id;

    @Column(name = "orcl_uid")
    public String uid;

    @Column(name = "orc_uname")
    public String uname;

    @Column(name = "orc_password")
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
