/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.NotasDto;
import com.administracion.entidad.ControlNotasCredito;
import com.administracion.entidad.ControlNotasDebito;
import com.administracion.entidad.Users;
import javax.sql.DataSource;



/**
 *
 * @author EderArmando
 */
public interface ControlNotasDebitoDao extends GenericDao<ControlNotasDebito>{
    public Integer guardarNotaDebitoControl(DataSource dataSource,NotasDto notasCredito, Users user);
    public ControlNotasDebito getNotaDebitoControl(DataSource dataSource, int idComprobante);
}
