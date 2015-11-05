/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jffa.tsc.sip04.service;

import es.jffa.tsc.sip04.dao.AccountDao;
import es.jffa.tsc.sip04.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

/**
 *
 * @author fran
 */
@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {
    private static final long serialVersionUID = -1L;
    private static final String _NAME = AccountServiceImpl.class.getSimpleName();

    @Autowired
    private AccountDao accountDao;

    /*------------------------------------------------------------------------*/
    /*                          Metodos Privados                              */
    /*------------------------------------------------------------------------*/
    /**
     *
     * @param username
     * @param errors
     */
    private void _validateUsername(final String username, final Errors errors) {
        if (accountDao.findByUsername(username) != null)
        {
            errors.rejectValue("username", "error.duplicate", new String[] {username}, null);
        }
    }

    /*------------------------------------------------------------------------*/
    /*                          Default Access                                */
    /*------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------*/
    /*                          Metodos Protegidos                            */
    /*------------------------------------------------------------------------*/

    /*------------------------------------------------------------------------*/
    /*                            Constructores                               */
    /*------------------------------------------------------------------------*/
    /**
     * Constructor por defecto.
     */
    public AccountServiceImpl() {
        System.out.println("AccountServiceImpl()");
    }

    /*------------------------------------------------------------------------*/
    /*                          Metodos Publicos                              */
    /*------------------------------------------------------------------------*/
    /**
     *
     * @param account
     * @param password
     * @param errors
     * @return
     */
    @Transactional(readOnly = false)
    @Override
    public boolean registerAccount(final Account account, final String password, final Errors errors) {
        _validateUsername(account.getUsername(), errors);

        boolean valid = !errors.hasErrors();

        if (valid) accountDao.create(account, password);

        return valid;
    }
}
