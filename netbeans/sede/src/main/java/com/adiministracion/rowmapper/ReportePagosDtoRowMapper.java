/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.ReportePagosDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class ReportePagosDtoRowMapper implements RowMapper<ReportePagosDto>{

    @Override
    public ReportePagosDto mapRow(ResultSet rs, int i) throws SQLException {
        ReportePagosDto reportePagosDtoMapped = new ReportePagosDto();
        
        Formatos formatosReportePagos = new Formatos();
        reportePagosDtoMapped.setFecha(formatosReportePagos.extractDateResultSet(rs, "fecha"));
        reportePagosDtoMapped.setIdcuenta(rs.getString("idcuenta"));
        reportePagosDtoMapped.setNombre(rs.getString("nombre"));
        reportePagosDtoMapped.setTotal(rs.getDouble("total"));
        
        return reportePagosDtoMapped;
    }
    
}
