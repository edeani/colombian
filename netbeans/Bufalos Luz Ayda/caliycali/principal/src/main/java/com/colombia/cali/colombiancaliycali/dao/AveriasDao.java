/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

/**
 *
 * @author Jose Efren
 */
public interface AveriasDao {
    
    public void guardarAveria(String nameDataSource, String valorTotal,String usuario);
    public void guardarDetalleAveria(String nameDataSource, String detalleAveria, String valorTotal,String usuario,Long numeroAveria);
    public Long secuenciaAveria(String nameDataSource);
}
