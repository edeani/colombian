/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.mapper.VentasMapper;
import com.mycompany.reportes.VentasService;
import com.mycompany.reportes.VentasServiceImpl;
import com.mycompany.util.Formatos;
import java.io.IOException;
import java.util.ArrayList;
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
public class VentasBean {

    /**
     * Creates a new instance of VentasBean
     */
    private VentasService ventaService;
    private List<VentasMapper> ventasMesa;
    private List<VentasMapper> ventasDomicilio ;
    private List<VentasMapper> ventasMostrador;
    private List<VentasMapper> ventas ;
    
    //totales
    private String totalVenta;
    private String totalDomicilios;
    private String totalMesa;
    private String totalMostrador;
    
    //Fechas
    private Date fi;
    private Date ff;
    
    public VentasBean() {
    ventasMesa = new ArrayList<VentasMapper>();
    ventasDomicilio = new ArrayList<VentasMapper>();
    ventasMostrador = new ArrayList<VentasMapper>();
    ventas = new ArrayList<VentasMapper>();
    totalVenta="0.0";
    totalDomicilios="0.0";
    totalMesa="0.0";
    totalMostrador="0.0";
    ventaService = new VentasServiceImpl();
    fi = new Date();
    ff = new Date();      
            
    }
    
    public void reporteVentas(){
        
        Formatos formato = new Formatos();
        
        ventaService.ventas(getFi(), getFf());
        ventasMesa=ventaService.getVentasMesa();
        ventasDomicilio=ventaService.getVentasDomicilio();
        ventasMostrador=ventaService.getVentasMostrador();
        ventas=ventaService.getVentas();
        
        Double totalDomiciliosNumber = ventaService.getTotalDomicilios();
        Double totalMesaNumber = ventaService.getTotalMesa();
        Double totalMostradorNumber = ventaService.getTotalMostrador();
        totalDomicilios=formato.numeroToStringFormato(ventaService.getTotalDomicilios());
        totalMesa=formato.numeroToStringFormato(ventaService.getTotalMesa());
        totalMostrador=formato.numeroToStringFormato(ventaService.getTotalMostrador());
        totalVenta=formato.numeroToStringFormato(totalDomiciliosNumber+totalMesaNumber+totalMostradorNumber);
        
    }
    
     public void navegarReporteVentas() throws IOException {
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/ventas");
    }
    

    /**
     * @return the ventasMesa
     */
    public List<VentasMapper> getVentasMesa() {
        return ventasMesa;
    }

    /**
     * @param ventasMesa the ventasMesa to set
     */
    public void setVentasMesa(List<VentasMapper> ventasMesa) {
        this.ventasMesa = ventasMesa;
    }

    /**
     * @return the ventasDomicilio
     */
    public List<VentasMapper> getVentasDomicilio() {
        return ventasDomicilio;
    }

    /**
     * @param ventasDomicilio the ventasDomicilio to set
     */
    public void setVentasDomicilio(List<VentasMapper> ventasDomicilio) {
        this.ventasDomicilio = ventasDomicilio;
    }

    /**
     * @return the ventasMostrador
     */
    public List<VentasMapper> getVentasMostrador() {
        return ventasMostrador;
    }

    /**
     * @param ventasMostrador the ventasMostrador to set
     */
    public void setVentasMostrador(List<VentasMapper> ventasMostrador) {
        this.ventasMostrador = ventasMostrador;
    }

    /**
     * @return the ventas
     */
    public List<VentasMapper> getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(List<VentasMapper> ventas) {
        this.ventas = ventas;
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
     * @return the totalMesa
     */
    public String getTotalMesa() {
        return totalMesa;
    }

    /**
     * @param totalMesa the totalMesa to set
     */
    public void setTotalMesa(String totalMesa) {
        this.totalMesa = totalMesa;
    }

    /**
     * @return the totalMostrador
     */
    public String getTotalMostrador() {
        return totalMostrador;
    }

    /**
     * @param totalMostrador the totalMostrador to set
     */
    public void setTotalMostrador(String totalMostrador) {
        this.totalMostrador = totalMostrador;
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
}
