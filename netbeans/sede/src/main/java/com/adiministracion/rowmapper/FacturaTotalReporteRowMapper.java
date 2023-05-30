/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.FacturaTotalReporteDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class FacturaTotalReporteRowMapper implements RowMapper<FacturaTotalReporteDto>{

    @Override
    public FacturaTotalReporteDto mapRow(ResultSet rs, int i) throws SQLException {
        FacturaTotalReporteDto facturaTotalReporteMapped = new FacturaTotalReporteDto();
        facturaTotalReporteMapped.setFecha_factura((new Formatos()).extractDateResultSet(rs, "fecha_factura"));
        facturaTotalReporteMapped.setNumero_factura(rs.getLong("numero_factura"));
        facturaTotalReporteMapped.setSede(rs.getString("sede"));
        return facturaTotalReporteMapped;
    }
    
}
