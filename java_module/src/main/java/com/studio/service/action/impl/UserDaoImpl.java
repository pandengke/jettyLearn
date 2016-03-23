package com.studio.service.action.impl;

import com.studio.service.action.UserDao;
import com.studio.service.data.User;
import com.studio.service.data.UserAuth;
import com.studio.service.jwt.Utils;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by 1234 on 2016/3/9.
 */
public class UserDaoImpl implements UserDao {

    private Session openSession() {
        return HibernateInit.instance.getFactory().openSession();
    }

    @Override
    public boolean register(String name, String password) {
        Session session = openSession();
        session.beginTransaction();
        session.save(new User(name, password));
        session.getTransaction().commit();
        return true;
    }

    @Override
    public String login(String uname, String password) {
        System.out.println("begin init:" + System.currentTimeMillis() % 10000);
        Session session = openSession();
        session.beginTransaction();
        List list = session.createQuery("from User u where u.uname = '" + uname + "'"
                + " and " + "u.password = '" + password + "'"
        ).list();
        session.getTransaction().commit();
        System.out.println("after login:" + System.currentTimeMillis() % 10000);
        if (list.size() > 0) {
            User user = (User) list.get(0);
            System.out.println("query user:" + user);
            String token = Utils.generateWebToken(uname);
            if (refreshToken(user.uid, token)) {
                return token;
            }
        }
        return null;
    }

    @Override
    public boolean refreshToken(String uid, String token) {
        Session session = openSession();
        session.beginTransaction();
        List<UserAuth> list = session.createQuery("select ua from UserAuth ua where ua.uid = '" + uid + "'").list();
        for (UserAuth au : list) {
            session.delete(au);
        }
        UserAuth userAuth = new UserAuth();
        userAuth.setUid(uid);
        userAuth.setToken(token);
        session.save(userAuth);
        session.getTransaction().commit();
        System.out.println("after save token:" + System.currentTimeMillis() % 10000);
        return true;
    }

    @Override
    public boolean isAuth(String token) {
        Session session = openSession();
        session.beginTransaction();
        List list = session.createQuery("select ua from UserAuth  ua where ua.token like '" + token + "'").list();
        session.getTransaction().commit();
        return list.size() > 0;
    }
}
