/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.reportes.ComprasService;
import com.mycompany.reportes.ComprasServiceImpl;
import com.mycompany.mapper.Compras;
import com.mycompany.util.Formatos;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author joseefren
 */
@ManagedBean(name="comprasBean")
@RequestScoped
public class ComprasBean {

    /**
     * Creates a new instance of ComprasBean
     */
    private Date Finicial;
    private Date Ffinal;
    private List<Compras> compras;
    private String totalCompras;
    private ComprasService comprasService;
    
    public ComprasBean() {
        Finicial = new Date();
        Ffinal = new Date();
        comprasService = new ComprasServiceImpl();
        totalCompras = "0.0";
    }

    public void listadoCompras(){
        compras = comprasService.listadoCompras(Finicial, Ffinal);
        Formatos formato = new Formatos();
        totalCompras = formato.numeroToStringFormato(comprasService.getTotalCompras());
        formato = null;
    }
    
    public void navegarReporteCompras() throws IOException {
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/compras");
    }
    
    /**
     * @return the Finicial
     */
    public Date getFinicial() {
        return Finicial;
    }

    /**
     * @param Finicial the Finicial to set
     */
    public void setFinicial(Date Finicial) {
        this.Finicial = Finicial;
    }

    /**
     * @return the Ffinal
     */
    public Date getFfinal() {
        return Ffinal;
    }

    /**
     * @param Ffinal the Ffinal to set
     */
    public void setFfinal(Date Ffinal) {
        this.Ffinal = Ffinal;
    }

    /**
     * @return the compras
     */
    public List<Compras> getCompras() {
        return compras;
    }

    /**
     * @param compras the compras to set
     */
    public void setCompras(List<Compras> compras) {
        this.compras = compras;
    }

    /**
     * @return the totalCompras
     */
    public String getTotalCompras() {
        return totalCompras;
    }

    /**
     * @param totalCompras the totalCompras to set
     */
    public void setTotalCompras(String totalCompras) {
        this.totalCompras = totalCompras;
    }
    
    
}
