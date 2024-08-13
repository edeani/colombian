/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.DetallePagosProveedorDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class DetallePagosProveedorDtoRowMapper implements RowMapper<DetallePagosProveedorDto>{

    @Override
    public DetallePagosProveedorDto mapRow(ResultSet rs, int i) throws SQLException {
        DetallePagosProveedorDto detallePagosProveedorMapped = new DetallePagosProveedorDto();
        detallePagosProveedorMapped.setConceptoCuenta(rs.getString("conceptoCuenta"));
        Formatos formatosDetallePagosProv= new Formatos();
        final String consecutivoName = "consecutivo";
        if(formatosDetallePagosProv.hasColumn(rs, consecutivoName)){
            detallePagosProveedorMapped.setConsecutivo(rs.getLong(consecutivoName));
        }
        detallePagosProveedorMapped.setDetalle(rs.getString("detalle"));
        final String estadoName = "estado";
        if(formatosDetallePagosProv.hasColumn(rs, estadoName)){
            detallePagosProveedorMapped.setEstado(rs.getString(estadoName));
        }
        detallePagosProveedorMapped.setFecha(formatosDetallePagosProv.dateTostring(
                formatosDetallePagosProv.extractDateResultSet(rs, "fecha")));
        final String fechaVencimientoName = "fechaVencimiento";
        if(formatosDetallePagosProv.hasColumn(rs, fechaVencimientoName)){
            detallePagosProveedorMapped.setFechaVencimiento(formatosDetallePagosProv.dateTostring(
                formatosDetallePagosProv.extractDateResultSet(rs, fechaVencimientoName)));
        }
        
        detallePagosProveedorMapped.setIdCuenta(rs.getString("idcuenta"));
        final String idSedeName = "idSede";
        if(formatosDetallePagosProv.hasColumn(rs, idSedeName)){
            detallePagosProveedorMapped.setIdSede(rs.getLong(idSedeName));
        }
        detallePagosProveedorMapped.setIdpagoproveedor(rs.getLong("idpagoproveedor"));
        detallePagosProveedorMapped.setNombreSede(rs.getString("nombreSede"));
        detallePagosProveedorMapped.setNumero(rs.getLong("numero"));
        detallePagosProveedorMapped.setNumeroCompra(rs.getLong("numeroCompra"));
        final String saldoName = "saldo";
        if(formatosDetallePagosProv.hasColumn(rs, saldoName)){
            detallePagosProveedorMapped.setSaldo(rs.getDouble(saldoName));
        }
        detallePagosProveedorMapped.setTotal(rs.getDouble("total"));
        return detallePagosProveedorMapped;
    }
    
}
