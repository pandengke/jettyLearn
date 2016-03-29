package com.studio.service.data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/3/18.
 */
@Entity
@Table(name = "d_user_auth")
public class UserAuth {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "org.hibernate.id.IncrementGenerator")
    private int id;
    @Column
    private int type;//登录方式

    @ManyToOne(targetEntity = User.class, optional = false)
    @JoinColumn(name = "uid", nullable = false, updatable = false)
    private User user;//用户id
    @Column
    private String token;//使用的token

    //constructor without arguments for Hibernate;
    public UserAuth() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User uid) {
        this.user = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
