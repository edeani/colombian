/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adiministracion.rowmapper;

import com.administracion.dto.ItemsHashDTO;
import com.administracion.dto.TiempoRealSedeDto;
import com.mycompany.enums.EnumTipoPagoTarjeta;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Anlod
 */
public class CierreTiempoRealRowMapper implements RowMapper<ItemsHashDTO>{

    @Override
    public ItemsHashDTO mapRow(ResultSet rs, int i) throws SQLException {
       ItemsHashDTO trs = new ItemsHashDTO();
       
       String tipoPago = rs.getString("tipo");
       tipoPago = Objects.isNull(tipoPago) ? "" : tipoPago;
       
       
       Double total = rs.getObject("total",Double.class);
       total = (total == null) ? 0D : total;
       
       trs.setName(tipoPago);
       trs.setValue(total);
       
       return trs;
    }
    
    
}
