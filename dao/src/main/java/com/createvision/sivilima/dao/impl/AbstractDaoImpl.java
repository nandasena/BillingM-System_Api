package com.createvision.sivilima.dao.impl;

import com.createvision.sivilima.dao.AbstractDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository("abstractDao")
public abstract class AbstractDaoImpl<T extends Serializable, PK extends Serializable> implements AbstractDao<T, PK> {

    private Class<T> model;
    @Autowired
    private SessionFactory sessionFactory;

    public AbstractDaoImpl() {
        this.model = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session   getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Long save(T object) {
        return (Long) getSession().save(object);
    }

    @Override
    public T get(PK id) {
        return (T) getSession().get(this.model, id);
    }

    @Override
    public void update(T object) {
        getSession().update(object);
    }

    @Override
    public void delete(T object){
        getSession().delete(object);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        final Session session = getSession();
        final Criteria criteria = session.createCriteria(this.model);
        return criteria.list();
    }

}
