/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.reportes.CuadreService;
import com.mycompany.reportes.CuadreServiceImpl;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Cuadre;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author joseefren
 */
@ManagedBean(name="cuadrebean")
@RequestScoped
public class CuadreBean {

    /**
     * Creates a new instance of DomiciliosDia
     */
    private CuadreService cuadreDiarioService;
    private Date fi;
    private Date ff;
    private List<Cuadre> cuadre;
    private String totalVenta;
    private String totalGastos;
    private String totalConsignaciones;
    private String valorCajaReal;
    
    public CuadreBean() {
        cuadreDiarioService = new CuadreServiceImpl();
        fi = new Date();
        ff = new Date();
        totalVenta = "0.0";
        totalGastos = "0.0";
        totalConsignaciones = "0.0";
        valorCajaReal = "0.0";
    }

    
   public void listadoCuadres()
   {
       cuadre = cuadreDiarioService.cuadreDia(fi, ff);
       Formatos formato = new Formatos();
       totalConsignaciones = formato.numeroToStringFormato(cuadreDiarioService.getValorConsignaciones());
       totalVenta = formato.numeroToStringFormato(cuadreDiarioService.getValorVentas());
       totalGastos =formato.numeroToStringFormato(cuadreDiarioService.getValorGastos());
   }
    
   public void navegarReporteCuadre() throws IOException {
       UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/cuadre");
   }

    /**
     * @return the cuadreDiarioService
     */
    public CuadreService getCuadreDiarioService() {
        return cuadreDiarioService;
    }

    /**
     * @param cuadreDiarioService the cuadreDiarioService to set
     */
    public void setCuadreDiarioService(CuadreService cuadreDiarioService) {
        this.cuadreDiarioService = cuadreDiarioService;
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
     * @return the cuadre
     */
    public List<Cuadre> getCuadre() {
        return cuadre;
    }

    /**
     * @param cuadre the cuadre to set
     */
    public void setCuadre(List<Cuadre> cuadre) {
        this.cuadre = cuadre;
    }

    /**
     * @return the totalVenta
     */
    public String getTotalVenta() {
        return totalVenta;
    }

    /**
     * @param totalVenta the totalVenta to set
     */
    public void setTotalVenta(String totalVenta) {
        this.totalVenta = totalVenta;
    }

    /**
     * @return the totalGastos
     */
    public String getTotalGastos() {
        return totalGastos;
    }

    /**
     * @param totalGastos the totalGastos to set
     */
    public void setTotalGastos(String totalGastos) {
        this.totalGastos = totalGastos;
    }

    /**
     * @return the totalConsignaciones
     */
    public String getTotalConsignaciones() {
        return totalConsignaciones;
    }

    /**
     * @param totalConsignaciones the totalConsignaciones to set
     */
    public void setTotalConsignaciones(String totalConsignaciones) {
        this.totalConsignaciones = totalConsignaciones;
    }

    /**
     * @return the valorCajaReal
     */
    public String getValorCajaReal() {
        return valorCajaReal;
    }

    /**
     * @param valorCajaReal the valorCajaReal to set
     */
    public void setValorCajaReal(String valorCajaReal) {
        this.valorCajaReal = valorCajaReal;
    }
    
}

