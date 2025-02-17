/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.NotasDetalleDto;
import com.administracion.dto.NotasDto;
import com.administracion.entidad.ControlNotasCredito;
import com.administracion.entidad.ControlNotasDebito;
import com.administracion.entidad.Users;
import java.util.List;



/**
 *
 * @author EderArmando
 */
public interface NotasService {
    public int guardarNotaDebito(String dataSource,NotasDto notasDebito,Users userNB);
    public int guardarNotaCredito(String dataSource,NotasDto notasDebito, Users userNC);
    public List<NotasDetalleDto> consultarNotaCredito(String dataSource,int idComprobante);
    public ControlNotasCredito consultarControlNotaCredito(String dataSource,int idComprobante);
    public List<NotasDetalleDto> consultarNotaDebito(String dataSource,int idComprobante);
    public ControlNotasDebito consultarControlNotaDebito(String dataSource,int idComprobante);
}
