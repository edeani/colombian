/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.entidades.Subprincipal;
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
public class SubPrincipalDaoImpl implements SubPrincipalDao{

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;
    
    @Override
    public Subprincipal findSubPrincipalByIdsede(String nameDatasource,Integer idsede) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        try {
            return this.jdbctemplate.queryForObject(caliycaliDao.selectJdbTemplate("*", "subprincipal", 
                    "idsede="+idsede), new BeanPropertyRowMapper<Subprincipal>(Subprincipal.class));
        } catch (DataAccessException e) {
            System.out.println("Error findSubPrincipalByIdsede::"+e.getMessage());
        }
        return null;
    }
    
    
}
