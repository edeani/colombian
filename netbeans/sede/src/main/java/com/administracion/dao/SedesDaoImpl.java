/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.ItemsDTO;
import com.administracion.dto.SedesDto;
import com.administracion.entidad.Sedes;
import com.administracion.util.LeerXml;
import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class SedesDaoImpl extends GenericDaoImpl<Sedes> implements SedesDao{

    private JdbcTemplate jdbcTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(SedesDaoImpl.class);
    @Autowired
    private LeerXml leerXml;
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    private void init(DataSource dataSource){
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Override
    public Sedes findXName(String sede) {
        HashMap<String,Object> parametros = new HashMap<>();
        parametros.put("sede", sede);
        return queryOpjectJpa(leerXml.getQuery("SedesJpa.findXname"), parametros);
    }
    
    @Override
    public SedesDto findXNameDto(String sede) {
        MapSqlParameterSource params = new MapSqlParameterSource("sede", sede);
        return this.namedParameterJdbcTemplate.queryForObject(leerXml.getQuery("SedesSql.findXname"), params, new BeanPropertyRowMapper<>(SedesDto.class));
    }
    @Override
    public List<ItemsDTO> listaSedesOptions(DataSource nameDatasource,Integer idSede) {
        this.jdbcTemplate = new JdbcTemplate(nameDatasource);
        return jdbcTemplate.query("select ss.id,ss.sede as label from subsedes ss where ss.idsede ="+idSede, new BeanPropertyRowMapper(ItemsDTO.class));
    }

    @Override
    public List<Sedes> traerSedes(DataSource nameDatasource) {
        this.jdbcTemplate = new JdbcTemplate(nameDatasource);
        return jdbcTemplate.query("select * from sedes", new BeanPropertyRowMapper(Sedes.class));
    }

    @Override
    public Sedes buscarSede(DataSource nameDatasource, Long idSede) {
        this.jdbcTemplate = new JdbcTemplate(nameDatasource);
        Sedes sede = null;
        try {
            sede = (Sedes) this.jdbcTemplate.queryForObject("select * from sedes where idsedes = ?", new Object[]{idSede}, new BeanPropertyRowMapper(Sedes.class));
        } catch (DataAccessException e) {
            LOGGER.error("Se encontraron 0 registros. ERROR: "+e.getMessage());
        }
        return sede;
    }

    @Override
    public Sedes findSede(Long idSede) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sedes> listSedes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
