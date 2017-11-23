/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.UserXSubSedeDao;
import com.administracion.dto.SubSedesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EderArmando
 */
@Service
public class UserSubSedeServiceImpl implements UserSubSedeService{

    @Autowired
    private UserXSubSedeDao userXSubSedeDao;
    
    @Transactional(readOnly = true)
    @Override
    public SubSedesDto findSedeDtoXUser(Long cedula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
