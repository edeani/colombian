/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.entidades.Inventario;
import java.util.List;

/**
 *
 * @author user
 */
public interface InventarioDao {
    
    public void actualizarPromedio(String nameDataSource,Long idproducto,Double promedio);
}
