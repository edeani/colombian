/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

/**
 *
 * @author EderArmando
 */
public interface PorcentajeVentasService {
    public void generarDetallePorcentajeVentas(String nameDataSource,Integer mes);
    public void generarPorcentajeVentas(String nameDataSource,Integer mes);
}
