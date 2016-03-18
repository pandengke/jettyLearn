package com.studio.service.action;

/**
 * Created by 1234 on 2016/3/9.
 */
public interface UserDao {
    boolean register(String name, String password);

    String login(String uname, String password);

    boolean refreshToken(String uid, String token);
}
