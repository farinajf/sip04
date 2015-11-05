/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jffa.tsc.sip04.dao.hbn;

import es.jffa.tsc.sip04.dao.Dao;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author fran
 * @param <T>
 */
public abstract class AbstractHbnDao<T extends Object> implements Dao<T> {
    private static final long serialVersionUID = -1L;
    private static final String _NAME = AbstractHbnDao.class.getSimpleName();

    @Autowired
    private SessionFactory sessionFactory;
    private Class<T>       domainClass;

    /*------------------------------------------------------------------------*/
    /*                          Metodos Privados                              */
    /*------------------------------------------------------------------------*/
    /**
     *
     * @return
     */
    private Class<T> _getDomainClass() {
        if (domainClass == null)
        {
            ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();

            this.domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
        }

        return domainClass;
    }

    /**
     *
     * @return
     */
    private String _getDomainClassName() {return _getDomainClass().getName();}

    /*------------------------------------------------------------------------*/
    /*                          Default Access                                */
    /*------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------*/
    /*                          Metodos Protegidos                            */
    /*------------------------------------------------------------------------*/
    protected Session _getSession() {return sessionFactory.getCurrentSession();}

    /*------------------------------------------------------------------------*/
    /*                            Constructores                               */
    /*------------------------------------------------------------------------*/
    /**
     * Constructor por defecto.
     */
    public AbstractHbnDao() {}

    /*------------------------------------------------------------------------*/
    /*                          Metodos Publicos                              */
    /*------------------------------------------------------------------------*/
    /**
     *
     * @param t
     */
    @Override
    public void create(final T t) {
        Method method = ReflectionUtils.findMethod(_getDomainClass(), "setDateCreated", new Class[] {java.util.Date.class});

        if (method != null)
        {
            try
            {
                method.invoke(t, new Date());
            }
            catch (Exception e) {}
        }

        _getSession().save(t);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public T get(final Serializable id) {
        return (T) _getSession().get(_getDomainClass(), id);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public T load(final Serializable id) {
        return (T) _getSession().load(_getDomainClass(), id);
    }

    /**
     *
     * @return
     */
    @Override
    public List<T> getAll() {
        return _getSession().createQuery("from " + _getDomainClassName()).list();
    }

    /**
     *
     * @param t
     */
    @Override
    public void update(final T t) {_getSession().update(t);}

    /**
     *
     * @param t
     */
    @Override
    public void delete(final T t) {_getSession().delete(t);}

    /**
     *
     * @param id
     */
    @Override
    public void deleteById(final Serializable id) {delete(load(id));}

    /**
     *
     */
    @Override
    public void deleteAll() {
        _getSession().createQuery("delete " + _getDomainClassName()).executeUpdate();
    }

    /**
     *
     * @return
     */
    @Override
    public long count() {
        return (Long) _getSession().createQuery("select count(*) from " + _getDomainClassName()).uniqueResult();
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean exists(final Serializable id) {return (get(id) != null);}
}
