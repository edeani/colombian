/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.mycompany.dto.OrdenesDomiciliosDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class OrdenesDomiciliosDtoRowMapper implements RowMapper<OrdenesDomiciliosDto>{

    @Override
    public OrdenesDomiciliosDto mapRow(ResultSet rs, int i) throws SQLException {
       OrdenesDomiciliosDto ordenMapper= new OrdenesDomiciliosDto();
       
       ordenMapper.setBarrio(rs.getString("barrio"));
       ordenMapper.setCliente(rs.getString("cliente"));
       ordenMapper.setFecha((new Formatos()).extractDateResultSet(rs, "fecha"));
       ordenMapper.setOrden(rs.getString("orden"));
       ordenMapper.setValor(rs.getString("valor"));
       
       return ordenMapper;
    }
    
}
