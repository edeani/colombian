/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.mapper;

import domicilios.dto.ProductoDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author user
 */
public class ProductoDtoMapper implements RowMapper{
    @Override
    public ProductoDto mapRow(ResultSet rs, int rowNum) throws SQLException {  
        ProductoDto productoDto = new ProductoDto();
        productoDto.setIdproducto(rs.getInt("idproducto"));
        productoDto.setNombreproducto(rs.getString("nombreproducto"));
        productoDto.setPrecioproducto(rs.getFloat("precioproducto"));
        productoDto.setTipo(rs.getString("tipo"));
        productoDto.setNombreTipo(rs.getString("nombretipo"));
        productoDto.setDescripcion(rs.getString("descripcion"));
        return productoDto;
    }
}
