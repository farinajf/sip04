/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jffa.tsc.sip04.service;

import es.jffa.tsc.sip04.domain.Account;
import org.springframework.validation.Errors;

/**
 *
 * @author fran
 */
public interface AccountService {

    /*------------------------------------------------------------------------*/
    /*                          Metodos Publicos                              */
    /*------------------------------------------------------------------------*/
    boolean registerAccount(final Account account, final String password, final Errors errors);
}
