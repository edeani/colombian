/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.entidades.DetallePorcentajeVentas;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface DetallePorcentajeVentasDao {
    public List<DetallePorcentajeVentas> generarDetallePorcentajeVentas(String nameDataSource,int mes);
    public void borrarDetallePorcentajeVentasAll(String nameDatasource);
    public void insertarDetallePorcentajeVenta(String nameDataSource,DetallePorcentajeVentas detallePorcentajeVentas);
}
