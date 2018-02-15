/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.SloganSedeDao;
import com.administracion.entidad.TextosSloganSedeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EderArmando
 */
@Service
public class SloganSedeServiceImpl implements SloganSedeService{
    @Autowired
    private SloganSedeDao sloganSedeDao;
    
    @Override
    public TextosSloganSedeDto findSloganXIdSede(Integer idsede) {
        return sloganSedeDao.finTextosSloganXIdSede(idsede);
    }
    
}
