/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dao.AveriasDao;
import com.administracion.datasources.GenericDataSource;
import com.administracion.dto.SubSedesDto;
import com.administracion.service.autorizacion.AccesosSubsedes;
import javax.sql.DataSource;
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
    private AveriasDao averiasDao;
    
    @Autowired
    private AccesosSubsedes accesosSubsedes;
    
       
    @Override
    @Transactional
    public void guardarAveria(String nameDataSource, String detalleAveria, String valorTotal,String usuario) {
        DataSource dataSource = accesosSubsedes.getDataSourceSubSede(nameDataSource);
        averiasDao.guardarAveria(dataSource, valorTotal, usuario);
        Long numeroAveria = averiasDao.secuenciaAveria(dataSource);
        averiasDao.guardarDetalleAveria(dataSource, detalleAveria, valorTotal, usuario, numeroAveria);
    }
    
}
