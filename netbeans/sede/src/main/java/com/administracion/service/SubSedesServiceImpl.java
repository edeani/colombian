/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.SubSedesDao;
import com.administracion.dto.ItemsDTO;
import com.administracion.dto.SubSedesDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EderArmando
 */
@Service
public class SubSedesServiceImpl implements SubSedesService{

    @Autowired
    private SubSedesDao subSedesDao;
    
    @Override
    @Transactional(readOnly = true)
    public List<SubSedesDto> subSedesXIdSede(Integer idSede) {
        return subSedesDao.subsedesXIdSede(idSede);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ItemsDTO> subSedesLabelXIdSede(Integer idSede) {
        return subSedesDao.subsedesLabelXIdSede(idSede);
    }
    
}
