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
    List<ItemsDTO> listaSedesOptions(DataSource nameDatasource,Integer idSede);
    List<Sedes> traerSedes(DataSource nameDatasource);
    Sedes buscarSede(DataSource nameDatasource, Long idSede);

    public Sedes findSede(Long idSede);

    public List<Sedes> listSedes();
}
