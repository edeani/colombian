/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dao.NotasDebitoDao;
import com.administracion.dto.NotasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.administracion.service.autorizacion.AccesosSubsedes;

/**
 *
 * @author EderArmando
 */
@Service
public class NotasDebitoServiceImpl implements NotasDebitoService{

    @Autowired
    private NotasDebitoDao notasDebitoDao;
    @Autowired
    private AccesosSubsedes accesosSubsedes;
    
    @Transactional
    @Override
    public void guardarNotaDebito(String dataSource, NotasDto notasDebito) {
        notasDebitoDao.guardarNotaDebito(accesosSubsedes.getDataSourceSubSede(dataSource), notasDebito);
    }

    @Transactional
    @Override
    public void guardarNotaCredito(String dataSource, NotasDto notasDebito) {
         notasDebitoDao.guardarNotaCredito(accesosSubsedes.getDataSourceSubSede(dataSource), notasDebito);
    }
    
}
