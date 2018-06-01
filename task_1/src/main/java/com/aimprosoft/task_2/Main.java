package com.aimprosoft.task_2;

import com.aimprosoft.task_2.bean.Employee;
import com.aimprosoft.task_2.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;

public class Main {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setDate(new Date(12334990));
        employee.setEmail("email");
        employee.setSalary(12000);
        employee.setIdDepartment(1);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        sessionFactory.close();

    }
}
