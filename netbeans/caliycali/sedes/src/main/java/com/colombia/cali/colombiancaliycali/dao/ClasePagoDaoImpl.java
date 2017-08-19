/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.entidades.ClasePago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class ClasePagoDaoImpl implements ClasePagoDao{

    @Autowired
    private ProjectsDao projectsDao;

    private JdbcTemplate jdbcTemplate;
    
    @Override
    public ClasePago findClasePagoById(Integer idClasePago,String dataSource) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(dataSource));
        ClasePago clasesDePago = null;
        try {
            clasesDePago = this.jdbcTemplate.queryForObject("select * from clase_pago where id="+idClasePago, 
                    new BeanPropertyRowMapper<ClasePago>(ClasePago.class));
        } catch (Exception e) {
            System.out.println("ERROR listAllClasesPago::"+e.getMessage());
        }
        
        return clasesDePago;
    }
    
}
