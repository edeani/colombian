/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.ItemsDTO;
import com.administracion.dto.SubSedesDto;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public interface SubSedesService {
    public List<SubSedesDto> subSedesXIdSede(Integer idSede);
    public List<ItemsDTO> subSedesLabelXIdSede(Integer idSede);
}
