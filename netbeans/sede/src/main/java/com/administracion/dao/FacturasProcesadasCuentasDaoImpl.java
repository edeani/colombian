/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;



import com.administracion.entidad.FacturasProcesadasCuentas;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class FacturasProcesadasCuentasDaoImpl extends GenericDaoImpl<FacturasProcesadasCuentas> implements FacturasProcesadasCuentasDao{

    
    private JdbcTemplate jdbctemplate;
    
    @Override
    public void guardarFacturaProcesada(DataSource nameDataSource, FacturasProcesadasCuentas facturasProcesadasCuentas) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbctemplate.execute(insertJdbTemplate("idfactura,idfacturacompra,tipo", "facturas_procesadas_cuentas", 
                    facturasProcesadasCuentas.getIdfactura()+","+facturasProcesadasCuentas.getIdfacturacompra()+
                    ",'"+facturasProcesadasCuentas.getTipo()+"'"));
        } catch (DataAccessException e) {
            System.out.println("Error guardarFacturaProcesada::"+e.getMessage());
        }
    }

    @Override
    public List<FacturasProcesadasCuentas> buscarFacturaProcesada(DataSource nameDataSource, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        List<FacturasProcesadasCuentas> facturasProcesadasCuentas = null;
        try {
            facturasProcesadasCuentas = this.jdbctemplate.query(selectJdbTemplate("*", "facturas_procesadas_cuentas", "idfactura="+idFactura+" order by consecutivo")
                    , new BeanPropertyRowMapper<>(FacturasProcesadasCuentas.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarFacturaProcesada::"+e.getMessage());
        }
        
        return facturasProcesadasCuentas;
    }
    
    @Override
    public FacturasProcesadasCuentas buscarFacturaProcesadaSedes(DataSource nameDataSource, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        FacturasProcesadasCuentas facturasProcesadasCuentas = null;
        try {
            facturasProcesadasCuentas = this.jdbctemplate.queryForObject(selectJdbTemplate("*", "facturas_procesadas_cuentas", "idfactura ="+idFactura+" and tipo='S'")
                    , new BeanPropertyRowMapper<>(FacturasProcesadasCuentas.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarFacturaProcesadaSedes::"+e.getMessage());
        }
        
        return facturasProcesadasCuentas;
    }
    
}
