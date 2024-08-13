/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.FacturaAutocompletarDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class FacturaAutocompletarDtoRowMapper implements RowMapper<FacturaAutocompletarDto>{

    @Override
    public FacturaAutocompletarDto mapRow(ResultSet rs, int i) throws SQLException {
        FacturaAutocompletarDto facturaAutocompletarDtoMapped = new FacturaAutocompletarDto();
        
        facturaAutocompletarDtoMapped.setEstadoFactura(rs.getString("estadoFactura"));
        Formatos formatosFactAutoCompletar = new Formatos();
        facturaAutocompletarDtoMapped.setFechaFactura(formatosFactAutoCompletar.dateTostring(
                formatosFactAutoCompletar.extractDateResultSet(rs, "fechaFactura")));
        
        final String idProveedorName = "idProveedor";
        if(formatosFactAutoCompletar.hasColumn(rs, idProveedorName)){
            facturaAutocompletarDtoMapped.setIdProveedor(rs.getLong(idProveedorName));
        }
        facturaAutocompletarDtoMapped.setValorTotal(rs.getDouble("valorTotal"));
        facturaAutocompletarDtoMapped.setValue(rs.getLong("value"));
        
        return facturaAutocompletarDtoMapped;
    }
    
}
