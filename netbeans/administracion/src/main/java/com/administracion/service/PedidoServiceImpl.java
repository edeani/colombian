/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.DetallePedidoDao;
import com.administracion.dao.PedidoDao;
import com.administracion.dao.ProductoDao;
import com.administracion.dao.TipoPagoDao;
import com.administracion.dao.UsuarioDao;
import com.administracion.dto.PedidoClienteDto;
import com.administracion.dto.ProductoClienteDto;
import com.administracion.entidad.Detallepedido;
import com.administracion.entidad.Pedido;
import com.administracion.entidad.Tipopago;
import com.administracion.entidad.Usuario;
import com.adiministracion.mapper.PedidoClienteDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoDao pedidoDao;
    
    @Autowired
    private DetallePedidoDao detallePedidoDao;
    
    @Autowired
    private ProductoDao productoDao;
    
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private TipoPagoDao tipoPagoDao;
    
    
    @Transactional
    @Override
    public void guardarPedido(PedidoClienteDto pedidoClienteDto,Usuario usuario) {
        Pedido pedido = PedidoClienteDtoMapper.pedidcoClienteDtoToPedido(pedidoClienteDto);
        pedido.setIdusuario(usuario);
        Tipopago tipoPago = tipoPagoDao.findById(pedidoClienteDto.getMedioPago());
        pedido.setIdtipopago(tipoPago);
        pedidoDao.save(pedido);
        
        for(ProductoClienteDto producto : pedidoClienteDto.getProductos() ){
            Detallepedido detallepedido = new Detallepedido();
            try {
                detallepedido.setIdproducto(productoDao.findById(producto.getIdproducto()));
            } catch (Exception e) {
              throw  new NullPointerException("Error en guardarPedido::No se encontró el producto");
            }
            detallepedido.setCantidadorden(producto.getCantidad());
            detallepedido.setPreciounitario(producto.getPrecio());
            detallepedido.setTotalproducto(producto.getTotal());
            detallepedido.setPedido(pedido);
            
            detallePedidoDao.save(detallepedido);
        }
        
        /**
         * Actualizo los datos del cliente
         */
        usuario.setDireccion(pedidoClienteDto.getDireccion());
        usuario.setCedula(pedidoClienteDto.getCedula());
        usuario.setTelefono(pedidoClienteDto.getTelefono());
        
        usuarioDao.Update(usuario);
    }
    
}
