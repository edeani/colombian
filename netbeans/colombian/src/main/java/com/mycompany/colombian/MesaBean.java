/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.reportes.MesasyllevarService;
import com.mycompany.reportes.MesasyllevarServiceImpl;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Mesasyllevar;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author joseefren
 */
@ManagedBean(name="mesaBean")
@RequestScoped
public class MesaBean {

    /**
     * Creates a new instance of DomiciliosDia
     */
    private MesasyllevarService mesasyLLevarService;
    private Date fi;
    private Date ff;
    private List<Mesasyllevar> mesayllevar;
    private String totalvalor;
    
    
    public MesaBean() {
        mesasyLLevarService = new MesasyllevarServiceImpl();
        fi = new Date();
        ff = new Date();
        totalvalor = "0.0";
    }

    
   public void listadoMesasLlevar()
   {
       mesayllevar = mesasyLLevarService.mesas(fi, ff);
       
       Formatos formato = new Formatos();
        //orden = (formato.numeroToStringFormato(mesasyLLevarService.getOrden()));
        //valor = (formato.numeroToStringFormato(mesasyLLevarService.getValor()));
        totalvalor = (formato.numeroToStringFormato(mesasyLLevarService.getTotalvalor()));
       
   }
    
   public void navegarReporteMesaLLevar() throws IOException {
       UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/mesasllevar");
   }

    /**
     * @return the mesasyLLevarService
     */
    public MesasyllevarService getMesasyLLevarService() {
        return mesasyLLevarService;
    }

    /**
     * @param mesasyLLevarService the mesasyLLevarService to set
     */
    public void setMesasyLLevarService(MesasyllevarService mesasyLLevarService) {
        this.mesasyLLevarService = mesasyLLevarService;
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
     * @return the mesayllevar
     */
    public List<Mesasyllevar> getMesayllevar() {
        return mesayllevar;
    }

    /**
     * @param mesayllevar the mesayllevar to set
     */
    public void setMesayllevar(List<Mesasyllevar> mesayllevar) {
        this.mesayllevar = mesayllevar;
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

   

    
}

