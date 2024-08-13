/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.entidad.CajaMenor;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class CajaMenorRowMapper implements RowMapper<CajaMenor>{

    @Override
    public CajaMenor mapRow(ResultSet rs, int i) throws SQLException {
        CajaMenor cajaMenorMapped = new CajaMenor();
        Formatos formatosCajaMenor = new Formatos();
        cajaMenorMapped.setFecha(formatosCajaMenor.extractDateResultSet(rs, "fecha"));
        cajaMenorMapped.setIdbeneficiario(rs.getString("idbeneficiario"));
        cajaMenorMapped.setIdcajamenor(rs.getLong("idcajamenor"));
        cajaMenorMapped.setTotal(rs.getDouble("total"));
        
        return cajaMenorMapped;
    }
    
}
