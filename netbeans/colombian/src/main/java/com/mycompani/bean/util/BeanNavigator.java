/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompani.bean.util;

import com.ocpsoft.pretty.PrettyContext;
import java.io.IOException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lacastrillov
 */
public class BeanNavigator {

    public static void dispatch(String url) throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String path = facesContext.getExternalContext().getRequestContextPath() + url;
        facesContext.getExternalContext().redirect(path);
    }

    public static void doForward(String redirect) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler myNav = facesContext.getApplication().getNavigationHandler();
        myNav.handleNavigation(facesContext, null, redirect);
    }

    public static Object getSessionAttribute(String nombre) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if(facesContext.getExternalContext().getSession(false)!=null){
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
            Object nB = session.getAttribute(nombre);
            return nB;
        }else{
            return null;
        }
    }
    
    public static void setSessionAttribute(String nombre, Object nB) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.setAttribute(nombre, nB);
    }
    
    public static void refresh()throws IOException{
         
        FacesContext facesContext = FacesContext.getCurrentInstance();
        PrettyContext prettyContext = PrettyContext.getCurrentInstance(facesContext);
        String path = prettyContext.getContextPath() +prettyContext.getRequestURL() + "";
        facesContext.getExternalContext().redirect(path);
    }
    
}
