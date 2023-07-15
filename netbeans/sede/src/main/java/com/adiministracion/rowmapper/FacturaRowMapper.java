/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.entidad.Factura;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class FacturaRowMapper implements RowMapper<Factura>{

    @Override
    public Factura mapRow(ResultSet rs, int i) throws SQLException {
        Factura facturaMapped = new Factura();
        facturaMapped.setEstadoFactura(rs.getString("estado_factura"));
        
        Formatos formatosFactura = new Formatos();
        facturaMapped.setFechaFactura(formatosFactura.extractDateResultSet(rs, "fecha_factura"));
        facturaMapped.setNumeroFactura(rs.getLong("numero_factura"));
        facturaMapped.setValorTotal(rs.getDouble("valor_total"));
        
        return facturaMapped;
    }
    
}
