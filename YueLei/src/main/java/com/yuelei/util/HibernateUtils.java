package com.yuelei.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static Configuration cfg=new Configuration().configure();
    private static SessionFactory sf=cfg.buildSessionFactory();
    public static Session openSession(){
        return sf.openSession();
    }
}
