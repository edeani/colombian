/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.entidades.Users;
import com.colombian.cali.colombiancaliycali.services.SecurityService;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Cuadre;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProjectsDao projectsDao;
    
    private JdbcTemplate jdbctemplate;
    
   
    private Double valorVentas;
    private Double valorGastos;
    private Double valorConsignaciones;

    @Override
    @Transactional(readOnly = true)
    public List<Cuadre> cuadreDia(Date fi, Date ff) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(user.getSede().getSede()));
        List<Cuadre> cuadres = new ArrayList<Cuadre>();
        try {
        Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            String query = "SELECT cierre_diario.fecha AS FECHA, "
                    + "cierre_diario.valor_ventas AS VENTAS, "
                    + "cierre_diario.valor_gastos AS GASTOS, "
                    + "cierre_diario.consignaciones AS CONSIGNACIONES, "
                    + "cierre_diario.caja_real AS CAJA_REAL "
                    + "FROM cierre_diario "
                    + "WHERE cierre_diario.fecha between '" + formato.dateTostring(dfDefault.format(fi)) + "'  and '" + formato.dateTostring(dfDefault.format(ff)) + "' " 
                    + "ORDER BY FECHA";
            cuadres = this.jdbctemplate.query(query, new BeanPropertyRowMapper<Cuadre>(Cuadre.class));
            calcularResumenCuadre(cuadres);
        } catch (Exception e) {
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
            for (Cuadre cuadre : cuadres) {
                valorVentas += Double.valueOf(cuadre.getValorVentas());
                valorGastos += Double.valueOf(cuadre.getValorGastos());
                valorConsignaciones += Double.valueOf(cuadre.getValorConsignaciones());
            }
        } 
    }
    /**
     * @return the valorVentas
     */
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
