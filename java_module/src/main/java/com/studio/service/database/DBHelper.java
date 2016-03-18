package com.studio.service.database;

import com.studio.service.data.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.List;

/**
 * Created by 1234 on 2016/3/9.
 */
public class DBHelper {
    public static final String URL = "jdbc:mysql://127.0.0.1/MySQL";
    public static final String USER = "root";
    public static final String PASSWORD = "1234";

    public User queryUser(String uid) {
        User user = null;
//        try {
//            com.mysql.jdbc.Driver d = null;
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//            String sql = "select * from user_info where uid = " + uid;
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery(sql);
//            if (rs.next()) {
//                String uname = rs.getString("uname");
//                String password = rs.getString("password");
//                user = new User(uid, uname, password);
//            }
//            rs.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        return user;
    }

    public boolean createDB() {
        CallableStatement statement = null;
        try {
            Driver.class.getName();
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (connection != null) {
                statement = connection.prepareCall("create table user_info(" +
                        "uid integer PRIMARY KEY" +
                        ", " +
                        "uname VARCHAR (8)" +
                        "," +
                        "password VARCHAR (3)" +
                        ")");
                statement.execute();
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean insertUser(User user) {
        try {
            Driver.class.getName();
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String sql = "insert into user_info (uid,uname,password) values (" + user.uid +
                    ",\"" + user.uname + "\"" +
                    ",\"" + user.password + "\"" +
                    ")";
            CallableStatement statement = connection.prepareCall(sql);
            statement.execute();
            statement.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public User hqlUser(String uid) {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            Session session = new MetadataSources(registry).buildMetadata().buildSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            List list = session.createQuery("from User").list();
            System.out.println("list:          " + list);
            tx.commit();
            return (User) list.get(0);
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return null;
    }

    public boolean hqlInsertUser(String uid) {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            Session session = new MetadataSources(registry).buildMetadata().buildSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            User user = new User();
            user.setUname("zb");
            user.setPassword("zbpass");
            session.save(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return false;
    }

    public boolean hqlQuery(String uid) {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            Session session = new MetadataSources(registry).buildMetadata().buildSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            List list =  session.createQuery("from User").list();
            System.out.println("*******");
            System.out.println(list);
            System.out.println("******");
            tx.commit();
            return true;
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return false;
    }

}
