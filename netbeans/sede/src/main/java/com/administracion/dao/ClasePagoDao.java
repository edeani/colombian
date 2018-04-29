/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.ClasePago;
import javax.sql.DataSource;



/**
 *
 * @author EderArmando
 */

public interface ClasePagoDao {
    public ClasePago findClasePagoById(Integer idClasePago,DataSource dataSource);
}
