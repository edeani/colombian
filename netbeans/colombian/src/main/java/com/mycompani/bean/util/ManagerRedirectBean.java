/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompani.bean.util;

import com.mycompany.security.AuthenticationService;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author lacastrillov
 */
@ManagedBean(name = "managerRedirectBean")
@RequestScoped
public class ManagerRedirectBean {

    private UserSessionBean user;
    private AuthenticationService authServ;

    /**
     * Creates a new instance of ManagerRedirectBean
     */
    public ManagerRedirectBean() {
    }

    public void validarUsuario() throws IOException {
        authServ = AuthenticationService.getInstance();
        user = UserSessionBean.getInstance();
        if ((user.getUsername().equals("lacastrillov") && user.getPassword().equals("calsat"))
                || (user.getUsername().equals("albcas") && user.getPassword().equals("albcas"))) {
            authServ.login(user);
            user.loadData();
            loguedGoHome();
        } else {
            authServ.incorrectAccess();
        }
    }

    public void loguedGoHome() throws IOException {
        authServ = AuthenticationService.getInstance();
        if (UserSessionBean.getInstance() != null) {
            user = UserSessionBean.getInstance();
            if (authServ.isLoggedIn()) {
                BeanNavigator.dispatch(user.getHomePage());
            }
        }
    }
}
