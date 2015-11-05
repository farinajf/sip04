/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jffa.tsc.sip04.dao;

import es.jffa.tsc.sip04.domain.Account;

/**
 *
 * @author fran
 */
public interface AccountDao extends Dao<Account> {

    /*------------------------------------------------------------------------*/
    /*                          Metodos Publicos                              */
    /*------------------------------------------------------------------------*/
    void create(final Account account, final String password);

    Account findByUsername(final String username);
}
