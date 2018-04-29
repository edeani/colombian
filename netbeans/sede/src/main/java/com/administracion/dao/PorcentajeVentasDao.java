/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.PorcentajeVentas;
import javax.sql.DataSource;



/**
 *
 * @author EderArmando
 */
public interface PorcentajeVentasDao {
     public Double PorcentajeVentasDaoImpl(DataSource nameDataSource,int mes);
     public void insertarPorcetajeVentas(DataSource nameDataSource,PorcentajeVentas porcentajeVentas);
     public void borrarPorcentajeVentas(DataSource nameDataSource,Integer mes);
}
