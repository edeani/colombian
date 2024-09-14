/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.Sloganxsede;
import com.administracion.entidad.TextosSloganSedeDto;
import com.administracion.util.LeerXml;
import javax.sql.DataSource;
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
 * @author EderArmando
 */
@Repository
public class SloganSedeDaoImpl extends GenericDaoImpl<Sloganxsede> implements SloganSedeDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(SloganSedeDaoImpl.class);
    @Autowired
    private LeerXml leerXml;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * Se ejecuta la conexión de la base de datos de credenciales
     *
     * @param dataSource Parámetro con la conexión principal
     */
    @Autowired
    private void init(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public TextosSloganSedeDto finTextosSloganXIdSede(Integer idsede) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource("idsede", idsede);
            return this.namedParameterJdbcTemplate.queryForObject(leerXml.getQuery("SedesSql.findSloganSede"), params, new BeanPropertyRowMapper<>(TextosSloganSedeDto.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error TextosSloganSedeDto::"+e.getMessage());
        }
        return null;
    }
}
