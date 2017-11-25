/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;

import com.administracion.entidad.Averias;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface AveriasDao extends GenericDao<Averias>{
    
    public void guardarAveria(DataSource nameDataSource, String valorTotal,String usuario);
    public void guardarDetalleAveria(DataSource nameDataSource, String detalleAveria, String valorTotal,String usuario,Long numeroAveria);
    public Long secuenciaAveria(DataSource nameDataSource);
}
