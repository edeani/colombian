/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.service;

import com.administracion.dao.BeneficiariosDao;
import com.administracion.dto.BeneficiarioAutocompletarDto;
import com.administracion.dto.ItemsDTO;
import com.administracion.service.autorizacion.AccesosSubsedes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Efren
 */
@Service
public class BeneficiariosServiceImpl implements BeneficiariosService{

    @Autowired
    private BeneficiariosDao beneficiarioDao;
    
    @Autowired
    private AccesosSubsedes accesosSubsedes;
    
    @Override
    @Transactional(readOnly = true)
    public List<BeneficiarioAutocompletarDto> buscarBeneficiarioLikeNombre(String nameDataSource, String nombre) {
        
        return beneficiarioDao.buscarBeneficiarioLikeNombre(accesosSubsedes.getDataSourceSubSede(nameDataSource), nombre);
    }

    @Override
    public List<ItemsDTO> buscarBeneficiariosSelect(String nameDataSource) {
        return beneficiarioDao.beneficiariosSelect(accesosSubsedes.getDataSourceSubSede(nameDataSource));
    }
    
}
