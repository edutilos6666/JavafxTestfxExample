package com.edutilos.dao;

import com.edutilos.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PersonDAO implements DAO<Person, Long> {
    private SessionFactory factory;

    public PersonDAO() {
        initFactory();
    }

    public void initFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public void closeFactory() {
        if(factory != null && factory.isOpen()) factory.close();
    }

    @Override
    public void create(Person person) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            session.save(person);
            session.getTransaction().commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Long id, Person person) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            Person one = session.find(Person.class, id);
            one.setName(person.getName());
            one.setAge(person.getAge());
            one.setWage(person.getWage());
            one.setActive(person.isActive());
            session.merge(one);
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
            Person one = session.find(Person.class, id);
            session.delete(one);
            session.getTransaction().commit();
        } catch(Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Person find(Long id) {
        Person ret = null;
        Session session = factory.openSession();
        ret = session.find(Person.class, id);
        session.close();
        return ret;
    }

    @Override
    public List<Person> findAll() {
        List<Person> all = null;
        Session session = factory.openSession();
        all = session.createQuery("from Person", Person.class).getResultList();
        session.close();
        return all;
    }
}
