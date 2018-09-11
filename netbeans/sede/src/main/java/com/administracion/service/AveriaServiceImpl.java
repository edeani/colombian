/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dao.AveriasDao;
import com.administracion.service.autorizacion.ConnectsAuth;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edeani
 */
@Service
public class AveriaServiceImpl implements AveriasService{


    @Autowired
    private AveriasDao averiasDao;
    
    @Autowired
    private ConnectsAuth connectsAuth;
    
       
    @Override
    @Transactional
    public void guardarAveria(String nameDataSource, String detalleAveria, String valorTotal,String usuario) {
        DataSource dataSource = connectsAuth.getDataSourceSede(nameDataSource);
        averiasDao.guardarAveria(dataSource, valorTotal, usuario);
        Long numeroAveria = averiasDao.secuenciaAveria(dataSource);
        averiasDao.guardarDetalleAveria(dataSource, detalleAveria, valorTotal, usuario, numeroAveria);
    }
    
}
