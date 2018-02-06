/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.ItemsDTO;
import com.administracion.dto.SedesDto;
import com.administracion.entidad.Sedes;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public interface SedesService {
    public Sedes findSedeXId(Long idSede);
    public Sedes findSedeXName(String sede);
    public SedesDto findSedeXNameDto(String sede);
    public List<ItemsDTO> listaSedesOptions(Integer idSede);
    public List<Sedes> traerSedes(String nameDatasource);
    public List<SedesDto> traerSedesDtos();
    public Sedes buscarSede(Long idSede);
}
