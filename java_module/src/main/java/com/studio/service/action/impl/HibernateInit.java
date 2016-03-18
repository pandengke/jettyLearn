package com.studio.service.action.impl;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Created by Administrator on 2016/3/18.
 */
public class HibernateInit {
    public static final HibernateInit instance = new HibernateInit();
    SessionFactory factory;

    public HibernateInit() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public SessionFactory getFactory() {
        return factory;
    }
}
