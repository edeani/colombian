/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.NotasDto;



/**
 *
 * @author EderArmando
 */
public interface NotasDebitoService {
    public void guardarNotaDebito(String dataSource,NotasDto notasDebito);
    public void guardarNotaCredito(String dataSource,NotasDto notasDebito);
}
