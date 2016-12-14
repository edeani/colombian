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
import domicilios.dto.PedidoViewDto;
import domicilios.entidad.Detallepedido;
import domicilios.entidad.Pedido;
import domicilios.entidad.Tipopago;
import domicilios.entidad.Usuario;
import domicilios.mapper.PedidoClienteDtoMapper;
import domicilios.util.LeerXml;
import domicilios.util.Util;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    @Override
    @Transactional(readOnly = true)
    public List<PedidoViewDto> findPedidosXPageUsuario(Integer page, Integer cantidad, Long idusuario) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("idusuario", idusuario);
        Integer firstItem = Util.firstItemPage(page, cantidad);
        return pedidoDao.findAllPageSql(firstItem, cantidad, parametros);
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido findById(Long idpedido) {
       return  pedidoDao.findById(idpedido);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Detallepedido> listDetallePedido(Long idpedido) {
        HashMap<String,Object> parametros = new HashMap<>();
        parametros.put("idpedido", idpedido);
        
        return detallePedidoDao.queryJpa(new LeerXml().getQuery("PedidoJpa.listDetallePedido"), parametros);
    }
}
