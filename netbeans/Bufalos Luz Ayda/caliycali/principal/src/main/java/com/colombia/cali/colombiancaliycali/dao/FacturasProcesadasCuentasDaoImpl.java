/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.entidades.FacturasProcesadasCuentas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class FacturasProcesadasCuentasDaoImpl implements FacturasProcesadasCuentasDao{

    @Autowired
    private CaliycaliDao caliyCaliDao;
    @Autowired
    private ProjectsDao projectsDao;
    private JdbcTemplate jdbctemplate;
    
    @Override
    public void guardarFacturaProcesada(String nameDataSource, FacturasProcesadasCuentas facturasProcesadasCuentas) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbctemplate.execute(caliyCaliDao.insertJdbTemplate("idfactura,idfacturacompra,tipo", "facturas_procesadas_cuentas", 
                    facturasProcesadasCuentas.getIdfactura()+","+facturasProcesadasCuentas.getIdfacturacompra()+
                    ",'"+facturasProcesadasCuentas.getTipo()+"'"));
        } catch (DataAccessException e) {
            System.out.println("Error guardarFacturaProcesada::"+e.getMessage());
        }
    }

    @Override
    public List<FacturasProcesadasCuentas> buscarFacturaProcesada(String nameDataSource, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<FacturasProcesadasCuentas> facturasProcesadasCuentas = null;
        try {
            facturasProcesadasCuentas = this.jdbctemplate.query(caliyCaliDao.selectJdbTemplate("*", "facturas_procesadas_cuentas", "idfactura="+idFactura+" order by consecutivo")
                    , new BeanPropertyRowMapper<FacturasProcesadasCuentas>(FacturasProcesadasCuentas.class));
        } catch (Exception e) {
            System.out.println("Error buscarFacturaProcesada::"+e.getMessage());
        }
        
        return facturasProcesadasCuentas;
    }
    
    @Override
    public FacturasProcesadasCuentas buscarFacturaProcesadaSedes(String nameDataSource, Long idFactura) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        FacturasProcesadasCuentas facturasProcesadasCuentas = null;
        try {
            facturasProcesadasCuentas = this.jdbctemplate.queryForObject(caliyCaliDao.selectJdbTemplate("*", "facturas_procesadas_cuentas", "idfactura ="+idFactura+" and tipo='S'")
                    , new BeanPropertyRowMapper<FacturasProcesadasCuentas>(FacturasProcesadasCuentas.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarFacturaProcesadaSedes::"+e.getMessage());
        }
        
        return facturasProcesadasCuentas;
    }
    
}
