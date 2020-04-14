/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import domicilios.dto.ProductoDto;

/**
 *
 * @author user
 */
public class ProductoDtoMapper implements RowMapper<ProductoDto>{
    @Override
    public ProductoDto mapRow(ResultSet rs, int rowNum) throws SQLException {  
        ProductoDto productoDto = new ProductoDto();
        productoDto.setIdproducto(rs.getInt("idproducto"));
        productoDto.setNombreproducto(rs.getString("nombreproducto"));
        productoDto.setPrecioproducto(rs.getFloat("precioproducto"));
        productoDto.setTipo(rs.getInt("tipo"));
        productoDto.setNombreTipo(rs.getString("nombretipo"));
        productoDto.setDescripcion(rs.getString("descripcion"));
        productoDto.setEstado(rs.getString("estado"));
        productoDto.setImagen(rs.getString("imagen"));
        return productoDto;
    }
}
