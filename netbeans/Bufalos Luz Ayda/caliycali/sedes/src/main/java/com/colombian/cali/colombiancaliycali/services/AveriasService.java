/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

/**
 *
 * @author edeani
 */
public interface AveriasService {
    //Guarda la averia en la base de datos
    public void guardarAveria(String nameDataSource,String detalleAveria,String valorTotal,String Usuario);
    
}
