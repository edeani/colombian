/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.mycompany.dto.OrdenesColombianDto;
import com.mycompany.util.Formatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class OrdenesColombianDtoRowMapper implements RowMapper<OrdenesColombianDto>{

    @Override
    public OrdenesColombianDto mapRow(ResultSet rs, int i) throws SQLException {
        OrdenesColombianDto ordenColombianMapped = new OrdenesColombianDto();
        
        ordenColombianMapped.setDia(rs.getString("Dia"));
        ordenColombianMapped.setDomicilios(rs.getLong("Domicilios"));
        ordenColombianMapped.setFecha((new Formatos()).extractDateResultSet(rs, "Fecha"));
        ordenColombianMapped.setValor_total(rs.getLong("Valor_Total"));
        
        return ordenColombianMapped;
    }
    
}
