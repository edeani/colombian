/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dataSource;

import javax.sql.DataSource;

/**
 *
 * @author Eslayfer
 */
public interface ProjectsDao {
    
    public DataSource getDatasource(String dataSource);
    
    public String baseDatos();
}
