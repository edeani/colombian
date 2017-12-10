/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.SedesDao;
import com.administracion.dto.ItemsDTO;
import com.administracion.entidad.Sedes;
import com.administracion.service.autorizacion.AccesosSubsedes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EderArmando
 */
@Service
public class SedesServiceImpl extends GenericService implements SedesService{

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

    @Override
    @Transactional
    public List<ItemsDTO> listaSedesOptions(Integer idSede) {
        return sedesDao.listaSedesOptions(accesosSubsedes_.getPrincipalDataSource(),idSede);
    }

    @Override
    @Transactional
    public List<Sedes> traerSedes(String nameDatasource) {
        return sedesDao.traerSedes(accesosSubsedes_.getDataSourceSubSede(nameDatasource));
    }

    @Override
    @Transactional(readOnly = true)
    public Sedes buscarSede(String nameDatasource, Long idSede) {
       return sedesDao.buscarSede(accesosSubsedes_.getDataSourceSubSede(nameDatasource), idSede);
    }
    
}
