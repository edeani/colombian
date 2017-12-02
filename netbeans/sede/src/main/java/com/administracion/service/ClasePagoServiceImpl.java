/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dao.ClasePagoDao;
import com.administracion.entidad.ClasePago;
import com.administracion.entidad.Users;
import com.administracion.service.autorizacion.AccesosSubsedes;
import com.administracion.service.autorizacion.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EderArmando
 */
@Service
public class ClasePagoServiceImpl implements ClasePagoService{

    @Autowired
    private ClasePagoDao clasePagoDao;
    @Autowired
    private SecurityService securityService;
    
    private AccesosSubsedes accesosSubsedes;
    
    @Autowired
    private void init(AccesosSubsedes accesosSubsedes){
        this.accesosSubsedes = accesosSubsedes;
    }
    
    @Transactional(readOnly = true)
    @Override
    public ClasePago findClasePagoById(Integer idClasePago) {
        Users user = securityService.getCurrentUser();
        return clasePagoDao.findClasePagoById(idClasePago,accesosSubsedes.getDataSourceSubSede(user.getIdsedes().getSede()));
    }
    
}
