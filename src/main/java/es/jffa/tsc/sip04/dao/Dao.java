/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jffa.tsc.sip04.dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author fran
 * @param <T>
 */
public interface Dao<T extends Object> {

    /*------------------------------------------------------------------------*/
    /*                          Metodos Publicos                              */
    /*------------------------------------------------------------------------*/
    void    create    (final T            t);
    T       get       (final Serializable id);
    T       load      (final Serializable id);
    List<T> getAll    ();
    void    update    (final T            t);
    void    delete    (final T            t);
    void    deleteById(final Serializable id);
    void    deleteAll ();
    long    count     ();
    boolean exists    (final Serializable id);
}
