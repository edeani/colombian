/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;


import com.administracion.entidad.ClasePago;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class ClasePagoDaoImpl implements ClasePagoDao{


    private JdbcTemplate jdbcTemplate;
    
    @Override
    public ClasePago findClasePagoById(Integer idClasePago,DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        ClasePago clasesDePago = null;
        try {
            clasesDePago = this.jdbcTemplate.queryForObject("select * from clase_pago where id="+idClasePago, 
                    new BeanPropertyRowMapper<>(ClasePago.class));
        } catch (DataAccessException e) {
            System.out.println("ERROR listAllClasesPago::"+e.getMessage());
        }
        
        return clasesDePago;
    }
    
}
