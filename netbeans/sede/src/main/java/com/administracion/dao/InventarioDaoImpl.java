/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;


import com.administracion.entidad.Inventario;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class InventarioDaoImpl extends GenericDaoImpl<Inventario> implements InventarioDao{

    private JdbcTemplate jdbctemplate;

    
    
    
    
    @Override
    public void actualizarPromedio(DataSource nameDataSource,Long idproducto,Double promedio) {
        
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        
        try {
            this.jdbctemplate.execute(updateJdbTemplate("promedio="+promedio
                    , "inventario"
                    ,"codigo_producto_inventario = "+idproducto));
        } catch (DataAccessException e) {
            System.out.println("No se actualizó el producto");

        }
        
    }
   
    
    
}
