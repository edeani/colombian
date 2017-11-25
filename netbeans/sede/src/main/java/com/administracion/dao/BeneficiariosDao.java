/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;

import com.administracion.dto.BeneficiarioAutocompletarDto;
import com.administracion.dto.ItemsDTO;
import com.administracion.entidad.Beneficiarios;

import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface BeneficiariosDao extends GenericDao<Beneficiarios>{
    
    public List<BeneficiarioAutocompletarDto> buscarBeneficiarioLikeNombre(DataSource nameDataSource,String nombre);
    public List<ItemsDTO> beneficiariosSelect(DataSource nameDataSource);
}
