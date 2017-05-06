/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.security;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompany.colombian.HomeBean;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lacastrillov
 */
@ManagedBean(name="authenticationService")
@SessionScoped
public class AuthenticationService {

    public final String AUTH_KEY = "app.session.user";
    public final String LOGIN_PATH = "";
    private String loginMensaje="";
    
    public static AuthenticationService getInstance(){
        if(BeanNavigator.getSessionAttribute("authenticationService")==null){
            BeanNavigator.setSessionAttribute("authenticationService", new AuthenticationService());
        }
        return (AuthenticationService) BeanNavigator.getSessionAttribute("authenticationService");
    }

    public void validateAuthentication() throws IOException{
        if (!isLoggedIn()) {
            setLoginMensaje("Acceso denegado");
            BeanNavigator.dispatch(LOGIN_PATH);
        }
    }
    
    public boolean isLoggedIn() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().getSessionMap().get(AUTH_KEY) != null;
    }

    public void login(Object user) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getSessionMap().put(AUTH_KEY, user);
    }

    public void logout() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().getSessionMap().remove(AUTH_KEY);
        setLoginMensaje("La sesion se ha cerrado");
        //BeanNavigator.dispatch(LOGIN_PATH);
    }
    
    public void incorrectAccess() throws IOException {
        setLoginMensaje("El usuario o la contraseña son incorrectos");
        BeanNavigator.dispatch(LOGIN_PATH);
    }

    /**
     * @return the loginMensaje
     */
    public String getLoginMensaje() {
        return loginMensaje;
    }

    /**
     * @param loginMensaje the loginMensaje to set
     */
    public void setLoginMensaje(String loginMensaje) {
        this.loginMensaje = loginMensaje;
    }
}
