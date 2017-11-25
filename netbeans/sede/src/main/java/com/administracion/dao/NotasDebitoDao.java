/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.NotasDto;
import com.administracion.entidad.NotasDebito;
import javax.sql.DataSource;



/**
 *
 * @author EderArmando
 */
public interface NotasDebitoDao extends GenericDao<NotasDebito>{
    public void guardarNotaDebito(DataSource dataSource,NotasDto notasDebito);
    public void guardarNotaCredito(DataSource dataSource,NotasDto notasDebito);
}
