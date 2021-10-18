/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.entidades.PorcentajeVentas;

/**
 *
 * @author EderArmando
 */
public interface PorcentajeVentasDao {
     public Double PorcentajeVentasDaoImpl(String nameDataSource,int mes);
     public void insertarPorcetajeVentas(String nameDataSource,PorcentajeVentas porcentajeVentas);
     public void borrarPorcentajeVentas(String nameDataSource,Integer mes);
}
