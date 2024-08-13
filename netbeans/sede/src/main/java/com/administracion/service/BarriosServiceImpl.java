/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.service;

import com.administracion.dao.BarriosDao;
import com.administracion.entidad.Barrios;
import com.administracion.service.autorizacion.ConnectsAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anlod
 */
@Service
public class BarriosServiceImpl implements BarriosService{
    
    @Autowired
    private ConnectsAuth connectsAuth;
    @Autowired
    private BarriosDao barriosDao;
    
    @Override
    public Barrios listClientes(Integer codigoBarrio, String nameDataSource) {
        return barriosDao.findBarrioById(codigoBarrio, connectsAuth.getDataSourceSubSede(nameDataSource));
    }
    
}
