/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.entidad.Pagos;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class PagosRowMapper implements RowMapper<Pagos>{

    @Override
    public Pagos mapRow(ResultSet rs, int i) throws SQLException {
        Pagos pagosMapped = new Pagos();
        Formatos formatosPagos = new Formatos();
        pagosMapped.setFecha(formatosPagos.extractDateResultSet(rs, "fecha"));
        pagosMapped.setIdbeneficiario(rs.getString("idbeneficiario"));
        pagosMapped.setIdpagos(rs.getLong("idpagos"));
        pagosMapped.setTipo(rs.getInt("tipo"));
        pagosMapped.setTotal(rs.getDouble("total"));
        
        return pagosMapped;
    }
    
}
