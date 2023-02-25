/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.AveriasDao;
import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edeani
 */
@Service
public class AveriaServiceImpl implements AveriasService{

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;
    @Autowired
    private AveriasDao averiasDao;
    
    @Override
    @Transactional
    public void guardarAveria(String nameDataSource, String detalleAveria, String valorTotal,String usuario) {
        
        averiasDao.guardarAveria(nameDataSource, valorTotal, usuario);
        Long numeroAveria = averiasDao.secuenciaAveria(nameDataSource);
        averiasDao.guardarDetalleAveria(nameDataSource, detalleAveria, valorTotal, usuario, numeroAveria);
    }
    
}
