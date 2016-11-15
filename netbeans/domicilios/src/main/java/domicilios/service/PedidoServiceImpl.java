/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.dao.DetallePedidoDao;
import domicilios.dao.PedidoDao;
import domicilios.dao.ProductoDao;
import domicilios.dao.TipoPagoDao;
import domicilios.dao.UsuarioDao;
import domicilios.dto.PedidoClienteDto;
import domicilios.dto.ProductoClienteDto;
import domicilios.entidad.Detallepedido;
import domicilios.entidad.Pedido;
import domicilios.entidad.Tipopago;
import domicilios.entidad.Usuario;
import domicilios.mapper.PedidoClienteDtoMapper;
import java.util.Date;
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
        pedido.setFecha(new Date());
        pedido.setComentarios(pedidoClienteDto.getComentarios());
        pedidoDao.save(pedido);
        
        pedidoClienteDto.getProductos().stream().map((producto) -> {
            Detallepedido detallepedido = new Detallepedido();
            try {
                detallepedido.setIdproducto(productoDao.findById(producto.getIdproducto()));
            } catch (Exception e) {
                throw  new NullPointerException("Error en guardarPedido::No se encontró el producto");
            }
            detallepedido.setCantidadorden(producto.getCantidad());
            detallepedido.setPreciounitario(producto.getPrecio());
            detallepedido.setTotalproducto(producto.getTotal());
            return detallepedido;
        }).map((detallepedido) -> {
            detallepedido.setPedido(pedido);
            return detallepedido;            
        }).forEachOrdered((detallepedido) -> {
            detallePedidoDao.save(detallepedido);
        });
        
        /**
         * Actualizo los datos del cliente
         */
        usuario.setDireccion(pedidoClienteDto.getDireccion());
        usuario.setCedula(pedidoClienteDto.getCedula());
        usuario.setTelefono(pedidoClienteDto.getTelefono());
        
        usuarioDao.Update(usuario);
    }
    
}
