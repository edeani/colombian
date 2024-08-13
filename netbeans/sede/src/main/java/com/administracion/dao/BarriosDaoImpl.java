/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.dao;

import com.administracion.entidad.Barrios;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anlod
 */
@Repository
public class BarriosDaoImpl extends GenericDaoImpl<Barrios> implements BarriosDao{

    private JdbcTemplate jdbcTemplate;
    
    private static final String TABLE_BARRIOS="barrios";
    @Override
    public Barrios findBarrioById(Integer codigoBarrio, DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        Barrios barrio=null;
        try {
            barrio = this.jdbcTemplate.queryForObject(selectJdbTemplate(null, TABLE_BARRIOS
                    , "codigo_barrio="+codigoBarrio), new BeanPropertyRowMapper<>(Barrios.class));
        } catch (DataAccessException e) {
            System.out.println("Error findBarrioById:: "+e.getMessage());
        }
        return barrio;
    }
    
}
