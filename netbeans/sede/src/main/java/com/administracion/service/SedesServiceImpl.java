/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.SedesDao;
import com.administracion.entidad.Sedes;
import javafx.beans.property.ReadOnlyLongWrapper;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EderArmando
 */
@Service
public class SedesServiceImpl implements SedesService{

    @Autowired
    private SedesDao sedesDao;
    
    @Transactional
    @Override
    public Sedes findSedeXId(Long idSede) {
        return sedesDao.findById(idSede);
    }
    @Transactional
    @Override
    public Sedes findSedeXName(String sede) {
        return sedesDao.findXName(sede);
    }
    
}
