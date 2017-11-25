/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import javax.sql.DataSource;



/**
 *
 * @author user
 */
public interface InventarioDao {
    
    public void actualizarPromedio(DataSource nameDataSource,Long idproducto,Double promedio);
}
