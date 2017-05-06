/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.reportes.DomiciliosDiaService;
import com.mycompany.reportes.DomiciliosDiaServiceImpl;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Ordenes;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author joseefren
 */
@ManagedBean(name="domiciliosDia")
@RequestScoped
public class DomiciliosDia {

    /**
     * Creates a new instance of DomiciliosDia
     */
    private DomiciliosDiaService domicilioService;
    private Date fi;
    private Date ff;
    private List<Ordenes> ordenes;
    private String totalDomicilios;
    private String totalRegistros;
    public DomiciliosDia() {
        domicilioService = new DomiciliosDiaServiceImpl();
        fi = new Date();
        ff = new Date();
        totalDomicilios = "0.0";
        totalRegistros = "0.0";
    }

    
   public void listadoOrdenes()
   {
       ordenes = domicilioService.domicilioDia(fi, ff);
       Formatos formato = new Formatos();
       totalDomicilios = formato.numeroToStringFormato(domicilioService.getTotalDomicilios());
       totalRegistros = formato.numeroToStringFormato(domicilioService.getTotalRegistros());
   }
    
   public void navegarReporteDomicilios() throws IOException {
       UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/domiciliosDia");
    }
    /**
     * @return the fi
     */
    public Date getFi() {
        return fi;
    }

    /**
     * @param fi the fi to set
     */
    public void setFi(Date fi) {
        this.fi = fi;
    }

    /**
     * @return the ff
     */
    public Date getFf() {
        return ff;
    }

    /**
     * @param ff the ff to set
     */
    public void setFf(Date ff) {
        this.ff = ff;
    }

    /**
     * @return the ordenes
     */
    public List<Ordenes> getOrdenes() {
        return ordenes;
    }

    /**
     * @param ordenes the ordenes to set
     */
    public void setOrdenes(List<Ordenes> ordenes) {
        this.ordenes = ordenes;
    }

    /**
     * @return the totalDomicilios
     */
    public String getTotalDomicilios() {
        return totalDomicilios;
    }

    /**
     * @param totalDomicilios the totalDomicilios to set
     */
    public void setTotalDomicilios(String totalDomicilios) {
        this.totalDomicilios = totalDomicilios;
    }

    /**
     * @return the totalRegistros
     */
    public String getTotalRegistros() {
        return totalRegistros;
    }

    /**
     * @param totalRegistros the totalRegistros to set
     */
    public void setTotalRegistros(String totalRegistros) {
        this.totalRegistros = totalRegistros;
    }
}
