/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.reportes.InventarioService;
import com.mycompany.reportes.InventarioServiceImpl;
import com.mycompany.mapper.Inventario;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author joseefren
 */
@ManagedBean
@RequestScoped
public class InventarioBean implements Serializable{

    /**
     * Creates a new instance of InventarioBean
     */
    private Date finicial;
    private Date ffinal;
    private List<Inventario> inventario;
    private InventarioService inventarioService;
    public InventarioBean() {
        inventarioService = new InventarioServiceImpl();
        finicial = new Date();
        ffinal = new Date();
    }

    public void reporteInventario()
    {
        
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        inventario = inventarioService.traerInventario(ffinal, finicial);
    }
    
    public void navegarReporteInventario() throws IOException {
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/inventario");
    }
    
    /**
     * @return the finicial
     */
    public Date getFinicial() {
        return finicial;
    }

    /**
     * @param finicial the finicial to set
     */
    public void setFinicial(Date finicial) {
        this.finicial = finicial;
    }

    /**
     * @return the ffinal
     */
    public Date getFfinal() {
        return ffinal;
    }

    /**
     * @param ffinal the ffinal to set
     */
    public void setFfinal(Date ffinal) {
        this.ffinal = ffinal;
    }

    /**
     * @return the inventario
     */
    public List<Inventario> getInventario() {
        return inventario;
    }

    /**
     * @param inventario the inventario to set
     */
    public void setInventario(List<Inventario> inventario) {
        this.inventario = inventario;
    }
}
