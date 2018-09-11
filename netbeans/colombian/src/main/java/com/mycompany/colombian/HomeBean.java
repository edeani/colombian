/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.entidades.Usuario;
import com.mycompany.security.AuthenticationService;
import com.mycompany.security.UsuarioService;
import com.mycompany.security.UsuarioServiceImpl;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eder
 */
@ManagedBean(name="homeBean")
@RequestScoped
public class HomeBean implements Serializable {

    //Maneja la sesion del usuario
    private UserSessionBean user;
    //Maneja los procesos de sesion
    private AuthenticationService authServ;
    //Sedes de la base de datos
    
    /**
     * Creates a new instance of HomeBean
     */
    public HomeBean() {  
    }

    //Inicio de sesion del usuario
    public void loguearUsuario() throws IOException{
        authServ = AuthenticationService.getInstance();
        user = UserSessionBean.getInstance();
        UsuarioService usuarioService = new UsuarioServiceImpl();
        Usuario usuario = usuarioService.encontraUsuario(user.getUsername(), user.getPassword());
        
        if(usuario != null)
        {
            user.setAnulaciones(usuario.getAnulaciones());
           authServ.login(user); 
           BeanNavigator.dispatch("/home/usuario/"+user.getUsername()); 
        }else
        {
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Validación: ", "Acceso Denegado"));     
        }
        user.reporteSedes();
    }

    //Finalizar Sesión del usuario
    public void cerrarSesion() throws IOException 
    {
        authServ = AuthenticationService.getInstance();
        authServ.logout();
        BeanNavigator.dispatch("/index");
    }
        
}
