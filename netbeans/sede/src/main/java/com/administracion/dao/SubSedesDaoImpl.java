/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.ItemsDTO;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.SubSedes;
import com.administracion.util.LeerXml;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class SubSedesDaoImpl extends GenericDaoImpl<SubSedes> implements SubSedesDao{

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    private LeerXml leerXml;
    
    @Autowired
    public void init(DataSource dataSourceSede){
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSourceSede);
    }
    
    @Override
    public List<SubSedesDto> subsedesXIdSede(Integer idSede) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(leerXml.getQuery("SubSedesJpa.findXIdSede"));
        MapSqlParameterSource params = new MapSqlParameterSource("idSede", idSede);
        return this.namedParameterJdbcTemplate.query(queryBuilder.toString(), params, new BeanPropertyRowMapper<>(SubSedesDto.class));
    }

    @Override
    public List<ItemsDTO> subsedesLabelXIdSede(Integer idSede) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(leerXml.getQuery("SubSedesJpa.findLabelXIdSede"));
        MapSqlParameterSource params = new MapSqlParameterSource("idSede", idSede);
        return this.namedParameterJdbcTemplate.query(queryBuilder.toString(), params, new BeanPropertyRowMapper<>(ItemsDTO.class));
    }

    @Override
    public SubSedesDto findSubSedeByUser(Long cedula) {
        MapSqlParameterSource params = new MapSqlParameterSource("cedula", cedula);
        return this.namedParameterJdbcTemplate.queryForObject(leerXml.getQuery("SubSedesJpa.findLabelXIdSede"), params, new BeanPropertyRowMapper<>(SubSedesDto.class));
    }
    
}
