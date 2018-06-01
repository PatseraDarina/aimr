package com.aimprosoft.task_2.utils;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);

    private static final EntityManagerFactory EMF;

    static {
        try {
            EMF = Persistence.createEntityManagerFactory(Constant.Hibernate.PERSISTENT_UNIT_NAME);
        } catch (Throwable ex) {
            LOGGER.warn(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager() {
        return EMF.createEntityManager();
  }

    public static void close() {
        EMF.close();
    }
}
