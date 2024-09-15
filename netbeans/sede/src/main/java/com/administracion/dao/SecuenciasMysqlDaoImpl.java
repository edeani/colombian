/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import java.sql.SQLException;
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
public class SecuenciasMysqlDaoImpl implements SecuenciasMysqlDao{
    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Long secuenceTable(DataSource nameDataSource, String table) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
       
        Long secuencia = 0L;
        try {
             String sql = "SELECT Auto_increment FROM information_schema.tables WHERE table_name='"+table+"' "
                    + "and table_schema = '"+nameDataSource.getConnection().getCatalog()+"'";
            secuencia = this.jdbcTemplate.queryForObject(sql,Long.class);
        } catch (SQLException e) {
            System.out.println("Error secuenceTable SQLException::"+e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error secuenceTable DataAccessException::"+e.getMessage());
        }
        
        return secuencia;
    }

    @Override
    public void updateSecuencialTabla(DataSource nameDataSource, String tabla, Long secuencia)  {
         this.jdbcTemplate = new JdbcTemplate(nameDataSource);
          try {
             String sql = "ALTER TABLE "+nameDataSource.getConnection().getCatalog()+"."+tabla+" AUTO_INCREMENT = "+secuencia;
            this.jdbcTemplate.execute(sql);
        } catch (DataAccessException | SQLException  e) {
            System.out.println("Error secuenceTable DataAccessException::"+e.getMessage());
        }
         
    }
    
}
