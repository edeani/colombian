/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.TrasladosDto;
import com.colombian.cali.colombiancaliycali.entidades.Traslado;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface TrasladoDao {
    public void insertarTraslado(String nameDataSource, Traslado traslado);
    public List<TrasladosDto> reporteTraslado(String nameDataSource, Date fechaInicio,Date fechaFin);
}
