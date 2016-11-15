/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;

import com.administracion.dto.ProductoAutocompletarDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author edeani
 */
public class ProductoAutocompletarMapper implements RowMapper<ProductoAutocompletarDto>{

    @Override
    public ProductoAutocompletarDto mapRow(ResultSet rs, int i) throws SQLException {
        ProductoAutocompletarDto productoAutocompletarDto = new ProductoAutocompletarDto();
        productoAutocompletarDto.setIdproducto(rs.getLong("idproducto"));
        productoAutocompletarDto.setValue(rs.getString("nombreproducto"));
        productoAutocompletarDto.setPrecio(rs.getFloat("precioproducto"));
        productoAutocompletarDto.setIdtipo(rs.getInt("idtipo"));
        productoAutocompletarDto.setTipo(rs.getString("nombretipo"));
        productoAutocompletarDto.setDescripcion(rs.getString("descripcion"));
        productoAutocompletarDto.setImagen(rs.getString("imagen"));
        return productoAutocompletarDto;
    }
    
}
