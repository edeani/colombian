/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.PagosCabeceraDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class PagosCabeceraDtoRowMapper implements RowMapper<PagosCabeceraDto>{

    @Override
    public PagosCabeceraDto mapRow(ResultSet rs, int i) throws SQLException {
        PagosCabeceraDto pagosCabeceraDtoMapped = new PagosCabeceraDto();
        Formatos formatosPagosCab = new Formatos();
        pagosCabeceraDtoMapped.setFecha(formatosPagosCab.dateTostring(
                formatosPagosCab.extractDateResultSet(rs, "fecha")));
        pagosCabeceraDtoMapped.setIdProveedor(rs.getString("idProveedor"));
        final String idSedeName= "idsede";
        if(formatosPagosCab.hasColumn(rs, idSedeName)){
            pagosCabeceraDtoMapped.setIdSede(rs.getLong(idSedeName));
        }
        
        pagosCabeceraDtoMapped.setIdpagos(rs.getLong("idpagos"));
        pagosCabeceraDtoMapped.setNombreProveedor(rs.getString("nombreProveedor"));
        final String sedeName= "sede";
        if(formatosPagosCab.hasColumn(rs, sedeName)){
            pagosCabeceraDtoMapped.setSede(rs.getString(sedeName));
        }
        final String tipoName = "tipo";
        if(formatosPagosCab.hasColumn(rs, tipoName)){
            pagosCabeceraDtoMapped.setTipo(rs.getInt(tipoName));
        } 
        pagosCabeceraDtoMapped.setTotal(rs.getDouble("total"));
        
        return pagosCabeceraDtoMapped;
    }
    
}
