package com.studio.service.action.impl;

import com.studio.service.action.QueryDocDao;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public class QueryDocDaoImpl implements QueryDocDao {
    @Override
    public List<String> queryUseDocrCatogory(String token) {
        Session session = HibernateInit.instance.getFactory().openSession();
        session.beginTransaction();
        List list = session.createQuery("from UserDoc.directory").list();
        session.getTransaction().commit();
        return list;
    }
}
