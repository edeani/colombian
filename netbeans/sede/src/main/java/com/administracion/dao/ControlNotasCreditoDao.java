/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.NotasDto;
import com.administracion.entidad.ControlNotasCredito;
import com.administracion.entidad.Users;
import javax.sql.DataSource;



/**
 *
 * @author EderArmando
 */
public interface ControlNotasCreditoDao extends GenericDao<ControlNotasCredito>{
    public Integer guardarNotaCreditoControl(DataSource dataSource,NotasDto notasCredito, Users user);
    public ControlNotasCredito getNotaCreditoControl(DataSource dataSource, int idComprobante);
}
