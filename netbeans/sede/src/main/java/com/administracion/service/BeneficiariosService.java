/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.service;


import com.administracion.dto.BeneficiarioAutocompletarDto;
import com.administracion.dto.ItemsDTO;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface BeneficiariosService {
    
    public List<BeneficiarioAutocompletarDto> buscarBeneficiarioLikeNombre(String nameDataSource,String nombre);
    public List<ItemsDTO> buscarBeneficiariosSelect(String nameDataSource);
}
