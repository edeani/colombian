/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.CierreSedesDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class CierreSedesRowMapper implements RowMapper<CierreSedesDto> {

    @Override
    public CierreSedesDto mapRow(ResultSet rs, int i) throws SQLException {
        CierreSedesDto cierreSedesMapped = new CierreSedesDto();

        cierreSedesMapped.setConcepto(rs.getString("concepto"));
        cierreSedesMapped.setConsecutivo(rs.getLong("consecutivo"));
        Formatos formatoCierreSede = new Formatos();
        cierreSedesMapped.setFecha(formatoCierreSede.extractDateResultSet(rs, "fecha"));

        final String fechaGeneradoCol = "fecha_comprobante";
        if (formatoCierreSede.hasColumn(rs, fechaGeneradoCol)) {
            cierreSedesMapped.setFechaGenerado(formatoCierreSede.extractDateResultSet(rs, fechaGeneradoCol));
        }
        final String idsedeCol = "idsede";
        if (formatoCierreSede.hasColumn(rs, idsedeCol)) {
            cierreSedesMapped.setIdesede(rs.getLong(idsedeCol));
        }
        final String sedeCol = "sede";
        if (formatoCierreSede.hasColumn(rs, sedeCol)) {
            cierreSedesMapped.setSede(rs.getString(sedeCol));
        }
        cierreSedesMapped.setIdcuenta(rs.getString("idcuenta"));
        cierreSedesMapped.setTotaldeber(rs.getDouble("totaldeber"));
        cierreSedesMapped.setTotalhaber(rs.getDouble("totalhaber"));

        return cierreSedesMapped;
    }
}
