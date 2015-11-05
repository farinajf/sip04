/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jffa.tsc.sip04.web;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ScriptAssert;

/**
 *
 * @author fran
 */
@ScriptAssert(lang = "javascript",
        script = "_this.confirmPassword.equals(_this.password)",
        message = "account.password.mismatch.message")
public class AccountForm implements java.io.Serializable {
    private static final long serialVersionUID = -1L;
    private static final String _NAME = AccountForm.class.getSimpleName();

    private String  _username;
    private String  _password;
    private String  _confirmPassword;
    private String  _firstName;
    private String  _lastName;
    private String  _email;
    private boolean _marketingOk = true;
    private boolean _acceptTerms = false;

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
    public AccountForm() {}

    /*------------------------------------------------------------------------*/
    /*                          Metodos Publicos                              */
    /*------------------------------------------------------------------------*/
    @NotNull
    @Size(min = 1, max = 50)
    public String getUsername()        {return _username;}

    @NotNull
    @Size(min = 6, max = 50)
    public String getPassword()        {return _password;}

    @NotNull
    @Size(min = 6, max = 50)
    public String getConfirmPassword() {return _confirmPassword;}

    public String getFirstName()       {return _firstName;}

    public String getLastName()        {return _lastName;}

    @NotNull
    @Size(min = 6, max = 50)
    @Email
    public String getEmail()           {return _email;}

    public boolean isMarketingOk() {return _marketingOk;}
    @AssertTrue(message = "{account.acceptTerms.assertTrue.message}")

    public boolean isAcceptTerms() {return _acceptTerms;}

    public void setUsername       (final String  x) {this._username        = x;}
    public void setPassword       (final String  x) {this._password        = x;}
    public void setConfirmPassword(final String  x) {this._confirmPassword = x;}
    public void setFirstName      (final String  x) {this._firstName       = x;}
    public void setLastName       (final String  x) {this._lastName        = x;}
    public void setEmail          (final String  x) {this._email           = x;}
    public void setMarketingOk    (final boolean x) {this._marketingOk     = x;}
    public void setAcceptTerms    (final boolean x) {this._acceptTerms     = x;}

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("username",    _username)
                .append("firstName",   _firstName)
                .append("lastName",    _lastName)
                .append("email",       _email)
                .append("marketingOk", _marketingOk)
                .append("acceptTerms", _acceptTerms)
                .toString();
    }
}
