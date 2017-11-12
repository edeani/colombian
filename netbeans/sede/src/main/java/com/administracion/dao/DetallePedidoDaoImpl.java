/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.Detallepedido;
import com.administracion.util.LeerXml;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class DetallePedidoDaoImpl extends GenericDaoImpl<Detallepedido> implements DetallePedidoDao{

    @Autowired
    private LeerXml leerXml;
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

     @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    @Override
    public void deleteDetallePedidoSql(Long idpedido) {
        final MapSqlParameterSource namedParameterSource = new MapSqlParameterSource();
        namedParameterSource.addValue("idpedido", idpedido);
        namedParameterJdbcTemplate.update(leerXml.getQuery("PedidoSql.deleteDetallePedido"),namedParameterSource);
    }
    
}
