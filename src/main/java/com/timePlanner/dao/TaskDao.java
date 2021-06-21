package com.timePlanner.dao;
import com.timePlanner.models.Tasks;
import com.timePlanner.utils.HibernateUtill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskDao {
    public Tasks findById(int id) {
        return HibernateUtill.getSessionFactory().openSession().get(Tasks.class, id);
    }

    public void save(Tasks task) {
        Session session = HibernateUtill.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(task);
        tx1.commit();
        session.close();
    }

    public void update(Tasks task) {
        Session session = HibernateUtill.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(task);
        tx1.commit();
        session.close();
    }

    public void delete(Tasks task) {
        Session session = HibernateUtill.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(task);
        tx1.commit();
        session.close();
    }

    public List<Tasks> findAll() {
        List<Tasks> task = (List<Tasks>)  HibernateUtill.getSessionFactory().openSession().createQuery("From tasks", Tasks.class).list();
        return task;
    }
}
