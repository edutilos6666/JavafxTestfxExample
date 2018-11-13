package com.edutilos.dao;

import com.edutilos.model.Worker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class WorkerDAO implements DAO<Worker,Long> {
    private SessionFactory factory;

    public WorkerDAO() {
        initFactory();
    }

    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }
  
    @Override
    public void create(Worker worker) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            session.save(worker);
            session.getTransaction().commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Long aLong, Worker worker) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            session.merge(worker);
            session.getTransaction().commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            Worker worker = session.find(Worker.class, id);
            session.delete(worker);
            session.getTransaction().commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Worker find(Long id) {
        Worker ret = null;
        Session session = factory.openSession();
        ret = session.find(Worker.class, id);
        session.close();
        return ret;
    }

    @Override
    public List<Worker> findAll() {
        List<Worker> all = null;
        Session session = factory.openSession();
        all = session.createQuery("from Worker", Worker.class).getResultList();
        session.close();
        return all;
    }
}
