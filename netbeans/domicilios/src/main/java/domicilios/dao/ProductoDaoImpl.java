/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import domicilios.dto.ProductoDto;
import domicilios.entidad.Producto;
import domicilios.mapper.ProductoDtoMapper;
import domicilios.util.LeerXml;
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
public class ProductoDaoImpl extends GenericDaoImpl<Producto> implements ProductoDao {

    private static final String AND =" and ";
    private static final String EQUAL =" = ";
    @Autowired
    private LeerXml leerXml;
    
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

     @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public List<ProductoDto> findAllPageSql(Integer first, Integer cantidad, HashMap<String, Object> parametros) {
        final MapSqlParameterSource namedParameterSource = new MapSqlParameterSource();
        if(parametros!=null){
            parametros.entrySet().stream().forEach(param->{
                namedParameterSource.addValue(param.getKey(),param.getValue());
            });
        }
        
        namedParameterSource.addValue("minimo", first);
        namedParameterSource.addValue("cantidad", cantidad);
        
        return namedParameterJdbcTemplate.query(leerXml.getQuery("ProductoSql.listProducto"),namedParameterSource, new ProductoDtoMapper());
        
    }

    @Override
    public List<ProductoDto> searchAllPageSql(Integer first, Integer cantidad, HashMap<String, Object> parametros) {
        StringBuilder conditions =  new StringBuilder("");
        final MapSqlParameterSource namedParameterSource = new MapSqlParameterSource();
        if(parametros!=null){
            parametros.entrySet().stream().forEach(param->{
                conditions.append(AND).append(param.getKey()).append(EQUAL).append(" '").append(String.valueOf(param.getValue())).append("' ");
                namedParameterSource.addValue(param.getKey(),param.getValue());
            });
        }
        String finalQuery = String.format(leerXml.getQuery("ProductoSql.searchProducto"),conditions.toString());
        namedParameterSource.addValue("minimo", first);
        namedParameterSource.addValue("cantidad", cantidad);
        return namedParameterJdbcTemplate.query(finalQuery,namedParameterSource, new ProductoDtoMapper());
    }

    @Override
    public Integer countProducts(HashMap<String, Object> parametros) {
        StringBuilder conditions =  new StringBuilder("");
        final MapSqlParameterSource namedParameterSource = new MapSqlParameterSource();
        if(parametros!=null){
            parametros.entrySet().stream().forEach(param->{
                conditions.append(AND).append(param.getKey()).append(EQUAL).append(" '").append(String.valueOf(param.getValue())).append("' ");
                namedParameterSource.addValue(param.getKey(),param.getValue());
            });
        }
        String finalQuery = String.format(leerXml.getQuery("ProductoSql.countSearchProducto"),conditions.toString());
        return namedParameterJdbcTemplate.queryForObject(finalQuery,namedParameterSource, Integer.class);
    }

}


