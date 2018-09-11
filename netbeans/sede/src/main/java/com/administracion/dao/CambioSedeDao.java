/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;

import com.administracion.entidad.CambioSede;
import javax.sql.DataSource;



/**
 *
 * @author Jose Efren
 */
public interface CambioSedeDao {
    public void guardarCambioSede(DataSource nameDataSource,CambioSede cambioSede);
}
