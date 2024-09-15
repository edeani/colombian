/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.ItemsDTO;
import com.administracion.dto.SedesDto;
import com.administracion.entidad.Sedes;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author EderArmando
 */
public interface SedesDao extends GenericDao<Sedes>{
    Sedes findXName(String sede);
    SedesDto findXNameDto(String sede);
    List<ItemsDTO> listaSubSedesOptions(Integer idSede);
    List<Sedes> traerSedes();
    Sedes buscarSede( Long idSede);

    public Sedes findSede(Long idSede);

    public List<Sedes> listSedes();
    List<SedesDto> listSedesDto();
    List<ItemsDTO> listaSedesOptionsByUsername(String username);
    /**
     * Busca los datos en el punto de la sede y no en la bd credentials
     * @param ds
     * @return 
     */
    List<ItemsDTO> listaSedesOptionsPoint(DataSource ds);
}
