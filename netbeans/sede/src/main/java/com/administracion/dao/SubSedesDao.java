/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.ItemsDTO;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.SubSedes;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public interface SubSedesDao extends GenericDao<SubSedes>{
     public List<SubSedesDto> subsedesXIdSede(Integer idSede);
     public List<ItemsDTO> subsedesLabelXIdSede(Integer idSede);
}
