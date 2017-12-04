package com.sanmi.citasClientes.hibernate.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sanmi.citasClientes.HibernateUtil;

public abstract class AbstractDao {
    private Session session;
    private Transaction tx;

    public AbstractDao() {
    }

    protected void saveOrUpdate(Object obj) {
        try {
            startOperation();
            session.saveOrUpdate(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
    }

    protected void delete(Class clazz, int id) {
        try {
            startOperation();
            Object obj = session.load(clazz, id);
            session.delete(obj);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
    }

    protected Object find(Class clazz, Long id) {
        Object obj = null;
        try {
            startOperation();
            obj = session.load(clazz, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            // HibernateFactory.close(session);
        }
        return obj;
    }

    protected List findAll(Class clazz) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
        return objects;
    }
    
    protected List findCitesOrderedByData(Class clazz, int id) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = session.createCriteria(clazz.getName()).add(Restrictions.eq("cliente_id", id))
                    .addOrder(Order.desc("data")).list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }        
        return objects;
    }

    protected List findAllCitesOrderedByData(Class clazz, String[] ids) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            String queryStr = "CLIENTE_ID in (";
            for (String s : ids) {
                queryStr += s + ",";
            }
            queryStr = queryStr.substring(0, queryStr.length() - 1) + ")";
            objects = session.createCriteria(clazz.getName()).add(Restrictions.sqlRestriction(queryStr))
                    .addOrder(Order.desc("data")).list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }        
        return objects;
    }

    protected List findAllClientsByName(Class clazz, String nombre, String primer_apellido, String segundo_apellido) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = session.createCriteria(clazz.getName()).add(Restrictions.like("nombre", "%" + nombre + "%"))
                    .add(Restrictions.like("primer_apellido", "%" + primer_apellido + "%"))
                    .add(Restrictions.like("segundo_apellido", "%" + segundo_apellido + "%")).addOrder(Order.asc("nombre")).addOrder(Order.asc("primer_apellido")).addOrder(Order.asc("segundo_apellido")).list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
        return objects;
    }

    protected List findUserByNamePw(Class clazz, String nombre, String password) {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from " + clazz.getName());
            objects = session.createCriteria(clazz.getName()).add(Restrictions.eq("nombre", nombre))
                    .add(Restrictions.eq("password", password)).list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            session.close();
        }
        return objects;
    }

    protected void handleException(HibernateException e) throws DataAccessLayerException {
        tx.rollback();
        throw new DataAccessLayerException(e);
    }

    protected void startOperation() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }
}