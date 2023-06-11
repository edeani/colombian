/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.DetallePagosTercerosDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class DetallePagosTercerosDtoRowMapper implements RowMapper<DetallePagosTercerosDto>{

    @Override
    public DetallePagosTercerosDto mapRow(ResultSet rs, int i) throws SQLException {
        DetallePagosTercerosDto detallePagosTercerosDtoMapped = new DetallePagosTercerosDto();
        
        detallePagosTercerosDtoMapped.setConceptoCuenta(rs.getString("conceptoCuenta"));
        Formatos formatosDetPagosTer = new Formatos();
        final String consecutivoName = "consecutivo";
        if(formatosDetPagosTer.hasColumn(rs, consecutivoName)){
            detallePagosTercerosDtoMapped.setConsecutivo(rs.getLong(consecutivoName));
        }
        detallePagosTercerosDtoMapped.setDetalle(rs.getString("detalle"));
        detallePagosTercerosDtoMapped.setFecha(formatosDetPagosTer.dateTostring(
                formatosDetPagosTer.extractDateResultSet(rs, "fecha")));
        detallePagosTercerosDtoMapped.setIdCuenta(rs.getString("idcuenta"));
        final String idSedeName = "idSede";
        if(formatosDetPagosTer.hasColumn(rs, idSedeName)){
            detallePagosTercerosDtoMapped.setIdSede(rs.getLong(idSedeName));
        }
        detallePagosTercerosDtoMapped.setIdpagotercero(rs.getLong("idpagotercero"));
        detallePagosTercerosDtoMapped.setNombreSede(rs.getString("nombreSede"));
        detallePagosTercerosDtoMapped.setNumero(rs.getInt("numero"));
        detallePagosTercerosDtoMapped.setTotal(rs.getDouble("total"));
        
        return detallePagosTercerosDtoMapped;
    }
    
}
