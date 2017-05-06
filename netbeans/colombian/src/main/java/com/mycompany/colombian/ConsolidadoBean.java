/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.mapper.ConsolidadoMapper;
import com.mycompany.reportes.ConsolidadoSedesService;
import com.mycompany.reportes.ConsolidadoSedesServiceImpl;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author joseefren
 */
@ManagedBean
@RequestScoped
public class ConsolidadoBean {

    /**
     * Creates a new instance of ConsolidadoBean
     */
    private List<ConsolidadoMapper> consolidado;
    private ConsolidadoSedesService consolidadoService;
    private Date fi;
    private Date ff;
    private String totalVentas;
    private String totalUnidades;
    private String totalConsignacion;
    public ConsolidadoBean() {
        fi = new Date();
        ff = new Date();
        consolidadoService = new ConsolidadoSedesServiceImpl();
    }
    
    public void navegarReporteConsolidado() throws IOException {
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/consolidado");
    }

    public void reporteConsolidados(){
        consolidado = consolidadoService.consolidadoSede(fi, ff);
        totalUnidades=consolidadoService.getTotalUnidades();
        totalVentas=consolidadoService.getTotalVentas();
        setTotalConsignacion(consolidadoService.getTotalConsignacion());
    }
    /**
     * @return the consolidado
     */
    public List<ConsolidadoMapper> getConsolidado() {
        return consolidado;
    }

    /**
     * @param consolidado the consolidado to set
     */
    public void setConsolidado(List<ConsolidadoMapper> consolidado) {
        this.consolidado = consolidado;
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
     * @return the totalVentas
     */
    public String getTotalVentas() {
        return totalVentas;
    }

    /**
     * @param totalVentas the totalVentas to set
     */
    public void setTotalVentas(String totalVentas) {
        this.totalVentas = totalVentas;
    }

    /**
     * @return the totalUnidades
     */
    public String getTotalUnidades() {
        return totalUnidades;
    }

    /**
     * @param totalUnidades the totalUnidades to set
     */
    public void setTotalUnidades(String totalUnidades) {
        this.totalUnidades = totalUnidades;
    }

    /**
     * @return the totalConsignacion
     */
    public String getTotalConsignacion() {
        return totalConsignacion;
    }

    /**
     * @param totalConsignacion the totalConsignacion to set
     */
    public void setTotalConsignacion(String totalConsignacion) {
        this.totalConsignacion = totalConsignacion;
    }
}
