/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.InventarioDTO;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class InventarioDTORowMapper implements RowMapper<InventarioDTO>{

    @Override
    public InventarioDTO mapRow(ResultSet rs, int i) throws SQLException {
        InventarioDTO inventarioDTOMapped = new InventarioDTO();
        inventarioDTOMapped.setCodigoProductoInventario(rs.getString("codigo_producto_inventario"));
        inventarioDTOMapped.setDescripcionProducto(rs.getString("descripcion_producto"));
        
        Formatos formatosInvetarioDTO = new Formatos();
        
        inventarioDTOMapped.setFechaFinal(formatosInvetarioDTO.dateTostring(
                                formatosInvetarioDTO.extractDateResultSet(rs, "fecha_final")
        ));
        inventarioDTOMapped.setFechaInicial(formatosInvetarioDTO.dateTostring(
                                formatosInvetarioDTO.extractDateResultSet(rs, "fecha_inicial")
        ));
        
        final String promedioName = "promedio";
        if(formatosInvetarioDTO.hasColumn(rs, promedioName)){
            inventarioDTOMapped.setPromedio(rs.getString(promedioName));
        }
        inventarioDTOMapped.setStockHoy(rs.getString("stock_hoy"));
        inventarioDTOMapped.setStockMinimo(rs.getString("stock_minimo"));
        inventarioDTOMapped.setStockReal(rs.getString("stock_real"));
        
        return inventarioDTOMapped;
        
    }
    
}
