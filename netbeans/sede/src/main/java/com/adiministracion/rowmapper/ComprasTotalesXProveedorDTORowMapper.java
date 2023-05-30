/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.ReporteComprasTotalesXProveedorDTO;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class ComprasTotalesXProveedorDTORowMapper implements RowMapper<ReporteComprasTotalesXProveedorDTO>{

    @Override
    public ReporteComprasTotalesXProveedorDTO mapRow(ResultSet rs, int i) throws SQLException {
        ReporteComprasTotalesXProveedorDTO comprasTotXProvMapped = new ReporteComprasTotalesXProveedorDTO();
        
        comprasTotXProvMapped.setFecha_compra((new Formatos()).extractDateResultSet(rs, "fecha_compra"));
        comprasTotXProvMapped.setNumero_compra(rs.getLong("numero_compra"));
        comprasTotXProvMapped.setValor_total(rs.getDouble("valor_total"));
        
        return comprasTotXProvMapped; 
    }
    
}
