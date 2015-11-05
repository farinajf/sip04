/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jffa.tsc.sip04.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author fran
 */
@NamedQuery(name = "findAccountByUsername", query = "from Account where username = :username")
@Entity
@Table(name = "Account")
public class Account implements java.io.Serializable {
    private static final long serialVersionUID = -1L;
    private static final String _NAME = Account.class.getSimpleName();

    private Long           id;
    private String         username;
    private String         firstName;
    private String         lastName;
    private String         email;
    private boolean        marketingOk = true;
    private boolean        acceptTerms = false;
    private boolean        enabled     = true;
    private java.util.Date dateCreated;

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
    public Account() {}

    /*------------------------------------------------------------------------*/
    /*                          Metodos Publicos                              */
    /*------------------------------------------------------------------------*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public Long getId() {return id;}

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    public String getUsername() {return username;}

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    public String getFirstName() {return firstName;}

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    public String getLastName() {return lastName;}

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    public String getEmail() {return email;}

    @NotNull
    @Column(name = "marketing_ok")
    public boolean isMarketingOk() {return marketingOk;}

    @NotNull
    @Column(name = "accept_terms")
    public boolean isAcceptTerms() {return acceptTerms;}

    @NotNull
    @Column(name = "enabled")
    public boolean isEnabled() {return enabled;}

    @Column(name = "date_created")
    public Date getDateCreated() {return dateCreated;}

    @Transient
    public String getFullName() {return firstName + " " + lastName;}

    public void setId         (final Long    x) {this.id          = x;}
    public void setUsername   (final String  x) {this.username    = x;}
    public void setFirstName  (final String  x) {this.firstName   = x;}
    public void setLastName   (final String  x) {this.lastName    = x;}
    public void setEmail      (final String  x) {this.email       = x;}
    public void setMarketingOk(final boolean x) {this.marketingOk = x;}
    public void setAcceptTerms(final boolean x) {this.acceptTerms = x;}
    public void setEnabled    (final boolean x) {this.enabled     = x;}
    public void setDateCreated(final Date    x) {this.dateCreated = x;}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
