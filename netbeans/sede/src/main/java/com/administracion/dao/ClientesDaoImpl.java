/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.dao;

import com.administracion.entidad.Clientes;
import java.util.List;
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
public class ClientesDaoImpl extends GenericDaoImpl<Clientes> implements ClientesDao{
    
    private JdbcTemplate jdbcTemplate;
    private static final String TABLE_CLIENTES = "clientes";
    @Override
    public Clientes findClientesByTel(String tel,DataSource nameDataSource) {
         this.jdbcTemplate = new JdbcTemplate(nameDataSource);
         Clientes clientes = null;
         try {
            clientes = this.jdbcTemplate.queryForObject(selectJdbTemplate(null, TABLE_CLIENTES
                    , "numero_telefono="+tel), new BeanPropertyRowMapper<>(Clientes.class));
        } catch (DataAccessException e) {
             System.out.println("Error listClientes::"+e.getMessage());
        }
         return  clientes;
    }
    
}
