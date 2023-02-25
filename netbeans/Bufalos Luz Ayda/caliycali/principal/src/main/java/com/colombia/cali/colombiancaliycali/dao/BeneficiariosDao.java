/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.BeneficiarioAutocompletarDto;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface BeneficiariosDao {
    
    public List<BeneficiarioAutocompletarDto> buscarBeneficiarioLikeNombre(String nameDataSource,String nombre);
    public List<ItemsDTO> beneficiariosSelect(String nameDataSource);
}
