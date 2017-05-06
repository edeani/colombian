/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.mapper.OrdenesDomiciliosMapper;
import com.mycompany.reportes.OrdenesDomiciliosServiceImpl;
import com.mycompany.reportes.OrdenesDomiciliosService;
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
@ManagedBean(name="ordenesdomiciliosbean")
@RequestScoped
public class OrdenesDomicliosBean {

    /**
     * Creates a new instance of OrdenesDomicliosBean
     */
    
    private OrdenesDomiciliosService OrdenesDomiciliosService;
    private Date fi;
    private Date ff;
    private List<OrdenesDomiciliosMapper> ordendesdomicilios;
    private String totalvalor;
    
    
    
    
    
    public OrdenesDomicliosBean() {
        OrdenesDomiciliosService = new OrdenesDomiciliosServiceImpl();
        fi = new Date();
        ff = new Date();
        totalvalor = "0.0";
    }
    
     public void listadoOrdenesDomi()
             
      {
        ordendesdomicilios = OrdenesDomiciliosService.domiciliosordenes(fi, ff);  
        Formatos formato = new Formatos();
        //orden = (formato.numeroToStringFormato(mesasyLLevarService.getOrden()));
        //valor = (formato.numeroToStringFormato(mesasyLLevarService.getValor()));
        totalvalor = (formato.numeroToStringFormato(OrdenesDomiciliosService.getTotalvalor()));
       
   }
    
   public void navegarReporteOrdenesDomicilios() throws IOException {
       UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/ordenesdomicilios");
   }

    /**
     * @return the OrdenesDomiciliosService
     */
    public OrdenesDomiciliosService getOrdenesDomiciliosService() {
        return OrdenesDomiciliosService;
    }

    /**
     * @param OrdenesDomiciliosService the OrdenesDomiciliosService to set
     */
    public void setOrdenesDomiciliosService(OrdenesDomiciliosService OrdenesDomiciliosService) {
        this.OrdenesDomiciliosService = OrdenesDomiciliosService;
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
     * @return the totalvalor
     */
    public String getTotalvalor() {
        return totalvalor;
    }

    /**
     * @param totalvalor the totalvalor to set
     */
    public void setTotalvalor(String totalvalor) {
        this.totalvalor = totalvalor;
    }

    /**
     * @return the ordendesdomicilios
     */
    public List<OrdenesDomiciliosMapper> getOrdendesdomicilios() {
        return ordendesdomicilios;
    }

    /**
     * @param ordendesdomicilios the ordendesdomicilios to set
     */
    public void setOrdendesdomicilios(List<OrdenesDomiciliosMapper> ordendesdomicilios) {
        this.setOrdendesdomicilios(ordendesdomicilios);
    }

    
    
    
    
}



