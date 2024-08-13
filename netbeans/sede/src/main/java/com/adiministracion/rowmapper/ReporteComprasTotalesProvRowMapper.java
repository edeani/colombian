/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.ComprasTotalesDTO;
import com.administracion.dto.ReporteComprasTotalesProvDTO;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class ReporteComprasTotalesProvRowMapper implements RowMapper<ReporteComprasTotalesProvDTO>{

    @Override
    public ReporteComprasTotalesProvDTO mapRow(ResultSet rs, int i) throws SQLException {
        ReporteComprasTotalesProvDTO reporteComprasTotalesProvMapped = new ReporteComprasTotalesProvDTO();
        
        reporteComprasTotalesProvMapped.setFecha_compra((new Formatos()).extractDateResultSet(rs, "fecha_compra"));
        reporteComprasTotalesProvMapped.setNombre(rs.getString("nombre"));
        reporteComprasTotalesProvMapped.setNumero_compra(rs.getLong("numero_compra"));
        reporteComprasTotalesProvMapped.setValor_total(rs.getDouble("valor_total"));
       
        return reporteComprasTotalesProvMapped;
    }
    
}
