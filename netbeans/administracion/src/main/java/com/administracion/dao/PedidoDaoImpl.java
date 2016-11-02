/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.adiministracion.mapper.PedidosDtoMapper;
import com.adiministracion.mapper.ProductoDtoMapper;
import com.administracion.dto.PedidoDto;
import com.administracion.entidad.Pedido;
import com.administracion.util.LeerXml;
import java.util.HashMap;
import java.util.List;
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
public class PedidoDaoImpl extends GenericDaoImpl<Pedido> implements PedidoDao{
    
    @Autowired
    private LeerXml leerXml;
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

     @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<PedidoDto> findAllPageSql(Integer first, Integer cantidad, HashMap<String, Object> parametros) {
        final MapSqlParameterSource namedParameterSource = new MapSqlParameterSource();
        if(parametros!=null){
            parametros.entrySet().stream().forEach(param->{
                namedParameterSource.addValue(param.getKey(),param.getValue());
            });
        }
        namedParameterSource.addValue("minimo", first);
        namedParameterSource.addValue("cantidad", cantidad);
        
        return namedParameterJdbcTemplate.query(leerXml.getQuery("PedidoSql.listPedido"),namedParameterSource, new PedidosDtoMapper());
    }

    @Override
    public void updateEstado(Long idpedido, String estado) {
        final MapSqlParameterSource namedParameterSource = new MapSqlParameterSource();
        namedParameterSource.addValue("idpedido", idpedido);
        namedParameterSource.addValue("estado", estado);
        
        namedParameterJdbcTemplate.update(leerXml.getQuery("PedidoSql.updateEstado"),namedParameterSource);
    }
    
    
}
