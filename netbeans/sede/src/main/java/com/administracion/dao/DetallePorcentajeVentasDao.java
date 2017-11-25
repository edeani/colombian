/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.entidad.DetallePorcentajeVentas;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface DetallePorcentajeVentasDao {
    public List<DetallePorcentajeVentas> generarDetallePorcentajeVentas(DataSource nameDataSource,int mes);
    public void borrarDetallePorcentajeVentasAll(DataSource nameDataSource);
    public void insertarDetallePorcentajeVenta(DataSource nameDataSource,DetallePorcentajeVentas detallePorcentajeVentas);
}
