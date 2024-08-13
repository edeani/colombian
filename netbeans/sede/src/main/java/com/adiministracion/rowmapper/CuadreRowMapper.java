/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.mycompany.mapper.Cuadre;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class CuadreRowMapper implements RowMapper<Cuadre>{

    @Override
    public Cuadre mapRow(ResultSet rs, int i) throws SQLException {
        Cuadre cuadreMapped = new Cuadre();
        
        cuadreMapped.setFecha((new Formatos()).extractDateResultSet(rs, "FECHA"));
        cuadreMapped.setValorCajaReal(rs.getString("ValorCajaReal"));
        cuadreMapped.setValorConsignaciones(rs.getString("valorConsignaciones"));
        cuadreMapped.setValorDescuentos(rs.getString("valorDescuentos"));
        cuadreMapped.setValorGastos(rs.getString("valorGastos"));
        cuadreMapped.setValorPagosTarjeta(rs.getString("valorPagosTarjeta"));
        cuadreMapped.setValorVentas(rs.getString("valorVentas"));
        
        return cuadreMapped;
    }
    
}
