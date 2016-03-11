package com.studio.service.action;

import com.studio.service.data.User;

/**
 * Created by 1234 on 2016/3/9.
 */
public interface UserDao {
    boolean login(User user);
    boolean logout(User user);
}
