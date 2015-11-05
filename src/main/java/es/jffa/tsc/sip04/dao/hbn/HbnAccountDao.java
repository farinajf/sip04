/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jffa.tsc.sip04.dao.hbn;

import es.jffa.tsc.sip04.dao.AccountDao;
import es.jffa.tsc.sip04.domain.Account;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fran
 */
@Repository
public class HbnAccountDao extends AbstractHbnDao<Account> implements AccountDao {
    private static final long serialVersionUID = -1L;
    private static final String _NAME = HbnAccountDao.class.getSimpleName();

    private static final String _UPDATE_PASSWORD_SQL = "update Account set password = ? where username = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /*------------------------------------------------------------------------*/
    /*                          Metodos Privados                              */
    /*------------------------------------------------------------------------*/

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
    public HbnAccountDao() {
        System.out.println("HbnAccountDao()");
    }

    /*------------------------------------------------------------------------*/
    /*                          Metodos Publicos                              */
    /*------------------------------------------------------------------------*/
    /**
     *
     * @param account
     * @param password
     */
    @Override
    public void create(final Account account, final String password) {
        create(account);

        jdbcTemplate.update(_UPDATE_PASSWORD_SQL, password, account.getUsername());
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public Account findByUsername(final String username) {
        final Query q = _getSession().getNamedQuery("findAccountByUsername");

        q.setParameter("username", username);

        return (Account) q.uniqueResult();
    }
}
