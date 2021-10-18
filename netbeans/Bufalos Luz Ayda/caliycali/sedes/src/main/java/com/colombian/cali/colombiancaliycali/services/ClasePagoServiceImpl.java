/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.ClasePagoDao;
import com.colombian.cali.colombiancaliycali.entidades.ClasePago;
import com.colombian.cali.colombiancaliycali.entidades.Users;
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
    
    @Transactional(readOnly = true)
    @Override
    public ClasePago findClasePagoById(Integer idClasePago) {
        Users user = securityService.getCurrentUser();
        return clasePagoDao.findClasePagoById(idClasePago, user.getSede().getSede());
    }
    
}
