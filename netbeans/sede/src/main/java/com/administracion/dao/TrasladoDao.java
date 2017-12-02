/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;

import com.administracion.dto.TrasladosDto;
import com.administracion.entidad.Traslado;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface TrasladoDao {
    public void insertarTraslado(DataSource nameDataSource, Traslado traslado);
    public List<TrasladosDto> reporteTraslado(DataSource nameDataSource, Date fechaInicio,Date fechaFin);
}
