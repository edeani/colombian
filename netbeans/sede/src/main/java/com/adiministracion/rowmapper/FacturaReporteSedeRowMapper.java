/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.FacturaReporteSedeDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class FacturaReporteSedeRowMapper implements RowMapper<FacturaReporteSedeDto>{

    @Override
    public FacturaReporteSedeDto mapRow(ResultSet rs, int i) throws SQLException {
        
        FacturaReporteSedeDto facturaReporteSedeMapped = new FacturaReporteSedeDto();
        facturaReporteSedeMapped.setFecha_factura((new Formatos()).extractDateResultSet(rs, "fecha_factura"));
        facturaReporteSedeMapped.setNumero_factura(rs.getLong("numero_factura"));
        facturaReporteSedeMapped.setValor_total(rs.getLong("valor_total"));
        
        return facturaReporteSedeMapped;
        
    }
    
}
