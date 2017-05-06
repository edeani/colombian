/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.colombian;

import com.mycompani.bean.util.BeanNavigator;
import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dto.ConsignacionesDtoToMapper;
import com.mycompany.entidades.Consignaciones;
import com.mycompany.mapper.ConsignacionesMapper;
import com.mycompany.reportes.CierreService;
import com.mycompany.reportes.CierreServiceImpl;
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
@ManagedBean
@RequestScoped
public class DiarioBean {
  /**
     * Creates a new instance of CierreBean
     */
    private Date fechaCierre;
    private String cajaInicial;
    private String ventas;
    private String gastos;
    private String consignaciones;
    private String caja_final;
    private List<Consignaciones> consigs;
    private List<ConsignacionesMapper> consigsMapper;
    private CierreService cierreService;
    public DiarioBean() {
        cierreService = new CierreServiceImpl();
        fechaCierre = new Date();
    }
    
    public void navegarReporteCierre() throws IOException {
        UserSessionBean user = UserSessionBean.getInstance();
        user.setMensaje("");
        BeanNavigator.dispatch("/home/usuario/" + UserSessionBean.getInstance().getUsername() + "/reportes/cierreDiario");
    }
    
    public void calcularCierreDiario()
    {
        Formatos formato = new Formatos();
        //JOptionPane.showConfirmDialog(null, fechaCierre.toString());
        setCajaInicial(formato.numeroToStringFormato(cierreService.cierreDiario(fechaCierre)));
        setVentas(formato.numeroToStringFormato(cierreService.cierreVentas(fechaCierre)));
        setGastos(formato.numeroToStringFormato(cierreService.cierreGastos(fechaCierre)));
        setConsignaciones(formato.numeroToStringFormato(cierreService.cierreConsignaciones(fechaCierre)));
        setCaja_final(formato.numeroToStringFormato(cierreService.cierrCajaFinal(formato.stringToNumeroFormato(ventas), 
                   formato.stringToNumeroFormato(gastos),
                   formato.stringToNumeroFormato(cajaInicial),
                   formato.stringToNumeroFormato(consignaciones))));
        setConsigs(cierreService.cierreListaConsignaciones(fechaCierre));
        ConsignacionesDtoToMapper consignacionDtoToMapper = new ConsignacionesDtoToMapper();
        consigsMapper = consignacionDtoToMapper.consignacionDtoToMapper(consigs);
    }
    
    /**
     * @return the fechaCierre
     */
    public Date getFechaCierre() {
        return fechaCierre;
    }

    /**
     * @param fechaCierre the fechaCierre to set
     */
    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    /**
     * @return the cajaInicial
     */
    public String getCajaInicial() {
        return cajaInicial;
    }

    /**
     * @param cajaInicial the cajaInicial to set
     */
    public void setCajaInicial(String cajaInicial) {
        this.cajaInicial = cajaInicial;
    }

    /**
     * @return the ventas
     */
    public String getVentas() {
        return ventas;
    }

    /**
     * @param ventas the ventas to set
     */
    public void setVentas(String ventas) {
        this.ventas = ventas;
    }

    /**
     * @return the gastos
     */
    public String getGastos() {
        return gastos;
    }

    /**
     * @param gastos the gastos to set
     */
    public void setGastos(String gastos) {
        this.gastos = gastos;
    }

    /**
     * @return the consignaciones
     */
    public String getConsignaciones() {
        return consignaciones;
    }

    /**
     * @param consignaciones the consignaciones to set
     */
    public void setConsignaciones(String consignaciones) {
        this.consignaciones = consignaciones;
    }

    /**
     * @return the caja_final
     */
    public String getCaja_final() {
        return caja_final;
    }

    /**
     * @param caja_final the caja_final to set
     */
    public void setCaja_final(String caja_final) {
        this.caja_final = caja_final;
    }

    /**
     * @return the consigs
     */
    public List<Consignaciones> getConsigs() {
        return consigs;
    }

    /**
     * @param consigs the consigs to set
     */
    public void setConsigs(List<Consignaciones> consigs) {
        this.consigs = consigs;
    }

    /**
     * @return the consigsMapper
     */
    public List<ConsignacionesMapper> getConsigsMapper() {
        return consigsMapper;
    }

    /**
     * @param consigsMapper the consigsMapper to set
     */
    public void setConsigsMapper(List<ConsignacionesMapper> consigsMapper) {
        this.consigsMapper = consigsMapper;
    }
}
