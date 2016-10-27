/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;

import com.administracion.dto.PedidoClienteDto;
import com.administracion.entidad.Pedido;

/**
 *
 * @author user
 */
public class PedidoClienteDtoMapper {
    public static Pedido pedidcoClienteDtoToPedido(PedidoClienteDto pedidoClienteDto){
        Pedido pedido = new Pedido();
        if(pedidoClienteDto.getIdpedido()!=null){
            pedido.setIdpedido(pedidoClienteDto.getIdpedido());
        }
        
        pedido.setEstadopedido(pedidoClienteDto.getEstado());
        pedido.setTotalpedido(pedidoClienteDto.getTotal());
        pedido.setDireccion(pedidoClienteDto.getDireccion());
        return pedido;
    }
    

}
