/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dao.PropertiesSedeDao;
import com.administracion.entidad.PropertiesSede;
import com.administracion.util.LeerXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author edeani
 */
@Repository
public class PropertiesSedeDaoImpl implements PropertiesSedeDao{

    private  static  final String PARAM_NAME="name";
    private  static  final String PARAM_ID_SEDE="idSede";
    
    private static final Logger logger = LoggerFactory.getLogger(PropertiesSedeDaoImpl.class);
    
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    
    @Autowired
    private LeerXml leerXml;
    
    @Override
    public PropertiesSede getPropertie(String name, Long idSedePrincipal) {
        MapSqlParameterSource paramsPropertiesSede = new MapSqlParameterSource(PARAM_NAME, name);
        paramsPropertiesSede.addValue(PARAM_ID_SEDE, idSedePrincipal);
        PropertiesSede propertiesSede = null;
        try {
            propertiesSede = this.namedParameterJdbcTemplate.queryForObject(leerXml.getQuery("PropertiesSede.getPropertie"),paramsPropertiesSede, 
                    new BeanPropertyRowMapper<>(PropertiesSede.class));
        } catch (DataAccessException e) {
            logger.error(String.format("Error getPropertie %s", e.getMessage()),e);
        }
        
        return propertiesSede;
    }
    
}
