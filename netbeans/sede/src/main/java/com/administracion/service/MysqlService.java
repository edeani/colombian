/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.service;

/**
 *
 * @author Jose Efren
 */
public interface MysqlService {
    
    public Long secuenciaTabla(String nameDataSource,String  tabla); 
    void updateSecuencialTabla(String nameDataSource, String tabla,Long scuencia);
}
