/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompani.bean.util;

import com.mycompany.entidades.Sedes;
import com.mycompany.servicios.util.SedesService;
import com.mycompany.servicios.util.SedesServiceImpl;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author lacastrillov
 */
@ManagedBean(name="userSessionBean")
@SessionScoped
public class UserSessionBean implements Serializable{
    
    private String username;
    private String password;
    private String role;
    private String homePage;
    private String mensaje;
    private List<Sedes> sedes;
    private Long s;
    private List<SelectItem> sed;
    //Base de datos que contiene usuarios y base de datos principal
    private String nombrePersistencia = "com.mycompany_colombian_war_1.0-SNAPSHOTPU";
    private SedesService sedesService;
    //Base de datos
    private Sedes sede;
    private String anulaciones;
    public static UserSessionBean getInstance(){
        if(BeanNavigator.getSessionAttribute("userSessionBean")!=null){
            return (UserSessionBean) BeanNavigator.getSessionAttribute("userSessionBean");
        }else{
            return null;
        }
    }
    /**
     * Creates a new instance of UserSession
     */
    public UserSessionBean() {
        sed = new LinkedList<SelectItem>();
        mensaje = "";
        
        
    }
    
    //Listar sedes
    public void reporteSedes() throws IOException
    {
        sedesService = new SedesServiceImpl();
        sedes=sedesService.listaSedes();
        if(sedes!=null){
        llenarSedes();
        s=sedes.get(0).getSed_cod();
        System.out.println(s);
     
        cargarSedeSeleccionada();
        }
        
    }
    
    public void llenarSedes()
    {
        for (Iterator<Sedes> it = sedes.iterator(); it.hasNext();) {
            Sedes sedes1 = it.next();
            getSed().add(new SelectItem(sedes1.getSed_cod(),sedes1.getSed_nombre()));
        } 
        
    }
    //cambiar sedes
    public void cambioSede() throws IOException
    {
        sede = sedesService.cambiarSede(getS(),sedes); 
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.refresh();
    }
    
    public void cargarSedeSeleccionada()
    {
        sede = sedesService.cambiarSede(getS(),sedes);
    }
    
    public void loadData(){
        if (username.equals("lacastrillov")) {
            role= "admin";
            mensaje = "Usuario Luis Castrillo Logueado";
            homePage= "/inicio/" + username;
        } else if (username.equals("albcas")) {
            role= "client";
            mensaje = "Usuario Albcas Logueado";
            homePage= "/megamenu/" + username + "?roleinfo=" + role;
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the homePage
     */
    public String getHomePage() {
        return homePage;
    }

    /**
     * @param homePage the homePage to set
     */
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    /**
     * @return the sedes
     */
    public List<Sedes> getSedes() {
        return sedes;
    }

    /**
     * @param sedes the sedes to set
     */
    public void setSedes(List<Sedes> sedes) {
        this.sedes = sedes;
    }
    
    public Sedes  getFirstSede()
    {
        return sedes.get(0);
    }

    /**
     * @return the sede
     */
    public Sedes getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(Sedes sede) {
        this.sede = sede;
    }
    /**
     * @return the nombrePersistencia
     */
    public String getNombrePersistencia() {
        return nombrePersistencia;
    }


    /**
     * @return the sed
     */
    public List<SelectItem> getSed() {
        return sed;
    }

    /**
     * @param sed the sed to set
     */
    public void setSed(List<SelectItem> sed) {
        this.sed = sed;
    }

    /**
     * @return the s
     */
    public Long getS() {
        return s;
    }

    /**
     * @param s the s to set
     */
    public void setS(Long s) {
        this.s = s;
    }

    public String getAnulaciones() {
        return anulaciones;
    }

    public void setAnulaciones(String anulaciones) {
        this.anulaciones = anulaciones;
    }
}
