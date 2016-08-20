/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class InventarioDaoImpl implements InventarioDao{

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private  CaliycaliDao caliycaliDao;
    
    
    
    
    @Override
    public void actualizarPromedio(String nameDataSource,Long idproducto,Double promedio) {
        
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        
        try {
            this.jdbctemplate.execute(caliycaliDao.updateJdbTemplate("promedio="+promedio.doubleValue()
                    , "inventario"
                    ,"codigo_producto_inventario = "+idproducto.longValue()));
        } catch (Exception e) {
            System.out.println("No se actualizó el producto");

        }
        
    }
   
    
    
}
