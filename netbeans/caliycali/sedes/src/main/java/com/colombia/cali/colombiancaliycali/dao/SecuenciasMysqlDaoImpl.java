/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import java.sql.SQLException;
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
    
    @Autowired
    private ProjectsDao projectsDao;
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Long secuenceTable(String nameDataSource, String table) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
       
        Long secuencia = 0L;
        try {
             String sql = "SELECT Auto_increment FROM information_schema.tables WHERE table_name='"+table+"' "
                    + "and table_schema = '"+projectsDao.getDatasource(nameDataSource).getConnection().getCatalog()+"'";
            secuencia = this.jdbcTemplate.queryForLong(sql);
        } catch (SQLException e) {
            System.out.println("Error secuenceTable SQLException::"+e.getMessage());
        } catch (DataAccessException e) {
            System.out.println("Error secuenceTable DataAccessException::"+e.getMessage());
        }
        
        return secuencia;
    }
    
}
