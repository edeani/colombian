/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.BeneficiariosDao;
import com.colombian.cali.colombiancaliycali.dto.BeneficiarioAutocompletarDto;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
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
    
    @Override
    @Transactional(readOnly = true)
    public List<BeneficiarioAutocompletarDto> buscarBeneficiarioLikeNombre(String nameDataSource, String nombre) {
        return beneficiarioDao.buscarBeneficiarioLikeNombre(nameDataSource, nombre);
    }

    @Override
    public List<ItemsDTO> buscarBeneficiariosSelect(String nameDataSource) {
        return beneficiarioDao.beneficiariosSelect(nameDataSource);
    }
    
}
