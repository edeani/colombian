/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;

import com.administracion.dto.PedidoClienteDto;
import com.administracion.dto.ProductoClienteDto;
import com.administracion.entidad.Detallepedido;
import com.administracion.entidad.Pedido;
import java.util.ArrayList;
import java.util.List;

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
    
    public static List<ProductoClienteDto> listDetallePedidoToProductoClienteDto(List<Detallepedido> productosCliente){
        List<ProductoClienteDto> p = new ArrayList<>();
        productosCliente.stream().forEach((productoClienteDto) -> {
            p.add(detallePedidoToProductoClienteDto(productoClienteDto));
        });
        return p;
    }
    
    public static ProductoClienteDto detallePedidoToProductoClienteDto(Detallepedido productoClienteDto){
        ProductoClienteDto prod= new ProductoClienteDto();
        
        prod.setCantidad(productoClienteDto.getCantidadorden());
        prod.setIdproducto(productoClienteDto.getIdproducto().getIdproducto());
        prod.setNombreproducto(productoClienteDto.getIdproducto().getNombreproducto());
        prod.setPrecio(productoClienteDto.getPreciounitario());
        prod.setTotal(productoClienteDto.getTotalproducto());
        
        return prod;
    }
}
