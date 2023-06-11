/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class ComprobanteConsolidadoSedeDtoRowMapper implements RowMapper<ComprobanteConsolidadoSedeDto> {

    @Override
    public ComprobanteConsolidadoSedeDto mapRow(ResultSet rs, int i) throws SQLException {
        ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeMapped = new ComprobanteConsolidadoSedeDto();

        comprobanteConsolidadoSedeMapped.setConcepto(rs.getString("concepto"));
        comprobanteConsolidadoSedeMapped.setConsecutivo(rs.getLong("consecutivo"));

        Formatos formatosComprobanteConsSed = new Formatos();
        comprobanteConsolidadoSedeMapped.setFecha(formatosComprobanteConsSed.dateTostring(
                formatosComprobanteConsSed.extractDateResultSet(rs, "fecha")));

        final String fechaComprobanteCol = "fecha_comprobante";
        if (formatosComprobanteConsSed.hasColumn(rs, fechaComprobanteCol)) {
            comprobanteConsolidadoSedeMapped.setFechaComprobante(
                    formatosComprobanteConsSed.dateTostring(
                            formatosComprobanteConsSed.extractDateResultSet(rs, fechaComprobanteCol)));
        }

        comprobanteConsolidadoSedeMapped.setIdComprobante(rs.getLong("idComprobante"));
        
        final String idConceptoCol = "idConcepto";
        if (formatosComprobanteConsSed.hasColumn(rs, idConceptoCol)) {
            comprobanteConsolidadoSedeMapped.setIdConcepto(rs.getLong(idConceptoCol));
        }
        
        comprobanteConsolidadoSedeMapped.setIdCuenta(rs.getString("idcuenta"));
        final String idsedeCol = "idsede";
        if (formatosComprobanteConsSed.hasColumn(rs, idsedeCol)) {
            comprobanteConsolidadoSedeMapped.setIdSede(rs.getLong(idsedeCol));
        }
        final String sedeCol = "sede";
        if (formatosComprobanteConsSed.hasColumn(rs, sedeCol)) {
            comprobanteConsolidadoSedeMapped.setSede(rs.getString(sedeCol));
        }
        
        final String tipoComprobCol = "tipoComprobante";
        if (formatosComprobanteConsSed.hasColumn(rs, tipoComprobCol)) {
            comprobanteConsolidadoSedeMapped.setTipoComprobante(rs.getInt(tipoComprobCol));
        }
        
        comprobanteConsolidadoSedeMapped.setTotal(rs.getLong("total"));
        
        return comprobanteConsolidadoSedeMapped;
    }

}
