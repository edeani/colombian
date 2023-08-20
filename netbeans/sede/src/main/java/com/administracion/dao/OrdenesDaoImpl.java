/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.dao;

import com.administracion.dto.OrdenesClienteProdDto;
import com.administracion.util.LeerXml;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anlod
 */
@Repository
public class OrdenesDaoImpl implements OrdenesDao{
    

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    private LeerXml leerXml;
    /**
     * El parámetro de promedion proviene de la base de datos principal.Los demas datos se extraen de la subsede
     * @param nameDataSource
     * @param fechaInicial
     * @param fechaFinal
     * @param tel
     * @return Las órdenes por producto de los clientes por sub-sede
     */
    @Override
    public List<OrdenesClienteProdDto> ordenesReporteClientesSubSede(DataSource nameDataSource, String fechaInicial, String fechaFinal
            ,String tel) {
        List<OrdenesClienteProdDto> ordenesCliente = new ArrayList<>();
        
        try {
            @SuppressWarnings("UnusedAssignment")
            String sqlOrdenesClientes = leerXml.getQuery("OrdenesSql.ordenesReporteClientes");
            
            MapSqlParameterSource params = new MapSqlParameterSource("fechaInicial", fechaInicial);
            params.addValue("fechaFinal", fechaFinal);
            params.addValue("tel", tel);
            
            namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(nameDataSource);
            return namedParameterJdbcTemplate.query(sqlOrdenesClientes, params, new BeanPropertyRowMapper<>(OrdenesClienteProdDto.class));
                 
        } catch (DataAccessException e) {
            System.out.println("Error ordenesReporteClientes "+e.getMessage());
        }
        
        return ordenesCliente;
    }
    
}
