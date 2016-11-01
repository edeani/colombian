/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.ProductoDetailDto;
import com.administracion.dto.ProductoDto;
import com.administracion.entidad.Categoria;
import com.administracion.entidad.Producto;
import java.util.List;

/**
 *
 * @author user
 */
public interface ProductoService {

    List<ProductoDto> listAllPage(Integer page);
    Integer numeroProducto();
    List<Categoria> listCategory();
    void eliminarProductoXid(Integer idproducto);
    void activarProductoXid(Integer idproducto);
    void crearProductoAdministrador(ProductoDetailDto producto);
    Producto findProductoXid(Integer idproducto);
    void actualizarProductoAdministrador(ProductoDetailDto productoDetailDto);
}
