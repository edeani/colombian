/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import domicilios.dto.PedidoViewDto;

/**
 *
 * @author edeani
 */
public class PedidoViewDtoMapper implements RowMapper<PedidoViewDto>{

    @Override
    public PedidoViewDto mapRow(ResultSet rs, int i) throws SQLException {
        PedidoViewDto pedidoViewDto = new PedidoViewDto();
        pedidoViewDto.setIdpedido(rs.getLong("idpedido"));
        pedidoViewDto.setDireccion(rs.getString("direccion"));
        pedidoViewDto.setFecha(rs.getString("fecha"));
        pedidoViewDto.setTotal(rs.getFloat("total"));
        pedidoViewDto.setTipopago(rs.getString("tipopago"));
        return pedidoViewDto;
    }
    
}
