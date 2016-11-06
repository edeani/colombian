/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;

import com.administracion.dto.PedidoDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author edeani
 */
public class PedidosDtoMapper implements RowMapper{

    @Override
    public PedidoDto mapRow(ResultSet rs, int i) throws SQLException {
        PedidoDto pedido = new PedidoDto();
        pedido.setCoordenadas(rs.getString("coordenadas"));
        pedido.setCorreo(rs.getString("correo"));
        pedido.setDireccion(rs.getString("direccion"));
        pedido.setFecha(rs.getString("fecha"));
        pedido.setIdpedido(rs.getLong("idpedido"));
        pedido.setIdtipopago(rs.getInt("idtipopago"));
        pedido.setNombre(rs.getString("nombreusuario"));
        pedido.setTelefono(rs.getString("telefono"));
        pedido.setTipopago(rs.getString("tipopago"));
        pedido.setTotalpedido(rs.getFloat("totalpedido"));
        pedido.setIdusuario(rs.getLong("idusuario"));
        pedido.setEstadopedido(rs.getString("estadopedido"));
        pedido.setCedula(rs.getString("cedula"));
        return pedido;
    }
    
}
