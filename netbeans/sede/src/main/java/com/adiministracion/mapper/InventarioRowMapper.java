/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.mapper;

import com.administracion.dto.InventarioDTO;
import com.administracion.entidad.Inventario;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class InventarioRowMapper implements RowMapper<Inventario>{

    @Override
    public Inventario mapRow(ResultSet rs, int i) throws SQLException {
        Inventario inventarioMapped = new Inventario();
        inventarioMapped.setCodigoProductoInventario(rs.getLong("codigo_producto_inventario"));
        inventarioMapped.setDescripcionProducto(rs.getString("descripcion_producto"));
        
        Formatos formatosInvetarioDTO = new Formatos();
        
        inventarioMapped.setFechaFinal(formatosInvetarioDTO.extractDateResultSet(rs, "fecha_final"));
        inventarioMapped.setFechaInicial(formatosInvetarioDTO.extractDateResultSet(rs, "fecha_inicial"));
        
        inventarioMapped.setPromedio(rs.getDouble("promedio"));
        inventarioMapped.setStockHoy(rs.getDouble("stock_hoy"));
        inventarioMapped.setStockMinimo(rs.getDouble("stock_minimo"));
        inventarioMapped.setStockReal(rs.getDouble("stock_real"));
        
        return inventarioMapped;
        
    }
    
}
