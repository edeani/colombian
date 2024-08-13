/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.CuentasPagarProveedoresDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class CuentasPagarProveedoresRowMapper implements RowMapper<CuentasPagarProveedoresDto>{

    @Override
    public CuentasPagarProveedoresDto mapRow(ResultSet rs, int i) throws SQLException {
        CuentasPagarProveedoresDto cuentasPagarProveedoresMapped = new CuentasPagarProveedoresDto();
        
        cuentasPagarProveedoresMapped.setDiasVencido(rs.getLong("dias_vencido"));
        
        final Formatos formatosCtaPagoProveedor = new Formatos();
        cuentasPagarProveedoresMapped.setFecha(formatosCtaPagoProveedor.dateTostring(formatosCtaPagoProveedor.extractDateResultSet(rs, "fecha")));
        cuentasPagarProveedoresMapped.setFechaVencimiento(formatosCtaPagoProveedor.dateTostring(formatosCtaPagoProveedor.extractDateResultSet(rs, "fecha_vencimiento")));
        
        cuentasPagarProveedoresMapped.setIdCompra(rs.getLong("id_compra"));
        final String proveedorCol = "proveedor";
        if(formatosCtaPagoProveedor.hasColumn(rs, proveedorCol)){
            cuentasPagarProveedoresMapped.setProveedor(rs.getString(proveedorCol));
        }
         
        cuentasPagarProveedoresMapped.setSaldo(rs.getDouble("saldo"));
        cuentasPagarProveedoresMapped.setValorTotal(rs.getDouble("valor_total"));
        
        return cuentasPagarProveedoresMapped;
    }
    
}
