/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.adiministracion.rowmapper.CuadreRowMapper;
import com.administracion.entidad.Users;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Cuadre;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joseefren
 */
@Service
public class CuadreColombianServiceImpl implements CuadreColombianService {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ConnectsAuth connectsAuth;
    
    private JdbcTemplate jdbctemplate;
    
   
    private Double valorVentas;
    private Double valorGastos;
    private Double valorConsignaciones;

    @Override
    @Transactional(readOnly = true)
    public List<Cuadre> cuadreDia(Date fi, Date ff,String subsede) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        List<Cuadre> cuadres = new ArrayList<>();
        try {
        Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            String query = "SELECT cierre_diario.fecha AS FECHA, "
                    + "cierre_diario.valor_ventas AS valorVentas, "
                    + "cierre_diario.valor_gastos AS valorGastos, "
                    + "cierre_diario.consignaciones AS valorConsignaciones, "
                    + "cierre_diario.caja_real AS ValorCajaReal, "
                    + "cierre_diario.descuento_ventas AS valorDescuentos, "
                    + "cierre_diario.pago_tarjetas AS valorPagosTarjeta "
                    + "FROM cierre_diario "
                    + "WHERE cierre_diario.fecha between '" + formato.dateTostring(dfDefault.format(fi)) + "'  and '" + formato.dateTostring(dfDefault.format(ff)) + "' " 
                    + "ORDER BY FECHA";
            cuadres = this.jdbctemplate.query(query, new CuadreRowMapper());
            calcularResumenCuadre(cuadres);
        } catch (DataAccessException e) {
            System.out.println("Error cuadreDia::" + e.getMessage());
        }
        return cuadres;
       
    }

    /**
     * Calculo el resume de los cuadres
     * @param cuadres 
     */
    public void calcularResumenCuadre(List<Cuadre> cuadres){
        valorVentas=0D;
        valorGastos=0D;
        valorConsignaciones=0D;
        if(cuadres !=null){
            cuadres.stream().map((cuadre) -> {
                valorVentas += Double.valueOf(cuadre.getValorVentas());
                return cuadre;
            }).map((cuadre) -> {
                valorGastos += Double.valueOf(cuadre.getValorGastos());
                return cuadre;
            }).forEachOrdered((cuadre) -> {
                valorConsignaciones += Double.valueOf(cuadre.getValorConsignaciones());
            });
        } 
    }
    /**
     * @return the valorVentas
     */
    @Override
    public Double getValorVentas() {
        return valorVentas;
    }

    /**
     * @param valorVentas the valorVentas to set
     */
    public void setValorVentas(Double valorVentas) {
        this.valorVentas = valorVentas;
    }

    /**
     * @return the valorGastos
     */
    @Override
    public Double getValorGastos() {
        return valorGastos;
    }

    /**
     * @param valorGastos the valorGastos to set
     */
    public void setValorGastos(Double valorGastos) {
        this.valorGastos = valorGastos;
    }

    /**
     * @return the valorConsignaciones
     */
    @Override
    public Double getValorConsignaciones() {
        return valorConsignaciones;
    }

    /**
     * @param valorConsignaciones the valorConsignaciones to set
     */
    public void setValorConsignaciones(Double valorConsignaciones) {
        this.valorConsignaciones = valorConsignaciones;
    }
}
