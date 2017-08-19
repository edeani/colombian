/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.NotasDebitoDao;
import com.colombian.cali.colombiancaliycali.dto.NotasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EderArmando
 */
@Service
public class NotasDebitoServiceImpl implements NotasDebitoService{

    @Autowired
    private NotasDebitoDao notasDebitoDao;
    
    @Transactional
    @Override
    public void guardarNotaDebito(String dataSource, NotasDto notasDebito) {
        notasDebitoDao.guardarNotaDebito(dataSource, notasDebito);
    }
    
}
