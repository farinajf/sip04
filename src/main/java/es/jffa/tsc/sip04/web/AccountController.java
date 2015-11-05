/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.jffa.tsc.sip04.web;

import es.jffa.tsc.sip04.domain.Account;
import es.jffa.tsc.sip04.service.AccountService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author fran
 */
@Controller
@RequestMapping("/users")
public class AccountController implements java.io.Serializable {
    private static final long serialVersionUID = -1L;
    private static final String _NAME = AccountController.class.getSimpleName();
    private static final Logger _log  = LoggerFactory.getLogger(AccountController.class);

    private static final String _VN_REG_FORM = "users/registrationForm";
    private static final String _VN_REG_OK   = "redirect:registration_ok";

    @Autowired
    private AccountService accountService;

    /*------------------------------------------------------------------------*/
    /*                          Metodos Privados                              */
    /*------------------------------------------------------------------------*/
    /**
     *
     * @param result
     */
    private static void _convertPasswordError(final BindingResult result) {
        for (ObjectError error : result.getGlobalErrors())
        {
            String msg = error.getDefaultMessage();

            if ("account.password.mismatch.message".equals(msg) == true)
            {
                if (result.hasFieldErrors("password") == false) result.rejectValue("password", "error.mismatch");
            }
        }
    }

    /**
     *
     * @param form
     * @return
     */
    private static Account _toAccount(final AccountForm form) {
        final Account result = new Account();

        result.setAcceptTerms(form.isAcceptTerms());
        result.setEmail      (form.getEmail());
        result.setEnabled    (true);
        result.setFirstName  (form.getFirstName());
        result.setLastName   (form.getLastName());
        result.setMarketingOk(form.isMarketingOk());
        result.setUsername   (form.getUsername());

        return result;
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
    public AccountController() {}

    /*------------------------------------------------------------------------*/
    /*                          Metodos Publicos                              */
    /*------------------------------------------------------------------------*/
    public void setAccountService(final AccountService x) {accountService = x;}

    /**
     * The @InitBinder annptation tells Spring Web MVC to call this method
     * when initializing the WebDataBinder responsible for binding HTTP
     * parameters to form beans.
     * @param binder
     */
    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.setAllowedFields(new String[]{
            "username",
            "password",
            "confirmPassword",
            "firstName",
            "lastName",
            "email",
            "marketingOk",
            "acceptTerms"});
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String getRegistrationForm(final Model model) {
        model.addAttribute("account", new AccountForm());
        return _VN_REG_FORM;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postRegistrationForm(@ModelAttribute("account") @Valid final AccountForm form, final BindingResult result) {
        _log.info("Created registration: {}", form);

        _convertPasswordError(result);

        accountService.registerAccount(_toAccount(form), form.getPassword(), result);

        return (result.hasErrors() ? _VN_REG_FORM : _VN_REG_OK);
    }
}
