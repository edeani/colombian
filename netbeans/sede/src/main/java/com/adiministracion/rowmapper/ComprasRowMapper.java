/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.entidad.Compras;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class ComprasRowMapper implements  RowMapper<Compras>{

    @Override
    public Compras mapRow(ResultSet rs, int i) throws SQLException {
        
        Compras compraMapped = new Compras();
        compraMapped.setCodigoProveedor(rs.getLong("codigo_proveedor"));
        compraMapped.setConsecutivo(rs.getLong("consecutivo"));
        compraMapped.setEstadoCompra(rs.getString("estado_compra").charAt(0));
        compraMapped.setEstadoCompraProveedor(rs.getString("estado_compra_proveedor"));
        Formatos formatosCompra = new Formatos();
        compraMapped.setFechaCompra(formatosCompra.extractDateResultSet(rs, "fecha_compra"));
        compraMapped.setFechaVencimiento(formatosCompra.extractDateResultSet(rs, "fecha_vencimiento"));
        compraMapped.setIdCompra(rs.getLong("id_compra"));
        compraMapped.setIdFacturaCompra(rs.getLong("idfacturacompra"));
        compraMapped.setIdsede(rs.getLong("idsede"));
        compraMapped.setSaldo(rs.getDouble("saldo"));
        compraMapped.setValorTotal(rs.getDouble("valor_total"));
        
        return compraMapped;
    }
    
}
