/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.CategoriaDao;
import com.administracion.dao.ProductoDao;
import com.administracion.dto.ProductoDetailDto;
import com.administracion.dto.ProductoDto;
import com.administracion.entidad.Categoria;
import com.administracion.entidad.Producto;
import com.administracion.enumration.ExtencionesEnum;
import com.administracion.util.Util;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Autowired
    private CategoriaDao categoriaDao;

    @Value("${producto.cantidad}")
    Integer cantidad;

    @Transactional(readOnly = true)
    @Override
    public List<ProductoDto> listAllPage(Integer page) {
        Integer firstItem = Util.firstItemPage(page, cantidad);
        return productoDao.findAllPageSql(firstItem + 1, cantidad + firstItem, null);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer numeroProducto() {
        return productoDao.findAll().size();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> listCategory() {
        return categoriaDao.findAll();
    }

    @Override
    @Transactional
    public void eliminarProductoXid(Integer idproducto) {
        Producto producto = productoDao.findById(idproducto);
        producto.setEstado("I");
        productoDao.Update(producto);
    }

    @Override
    @Transactional
    public void crearProductoAdministrador(ProductoDetailDto producto) {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setDescripcion(producto.getDescripcion());
        nuevoProducto.setNombreproducto(producto.getNombreproducto());
        nuevoProducto.setPrecioproducto(producto.getPrecioproducto());
        nuevoProducto.setEstado(producto.getEstado());
        nuevoProducto.setTipo(producto.getTipo());
        productoDao.save(nuevoProducto);

        //Recupero consecutivo del producto guardado
        producto.setIdproducto(nuevoProducto.getIdproducto());

        if (producto.getImagen().getContentType().contains("jpeg")) {
            nuevoProducto.setImagen(producto.getIdproducto() + ExtencionesEnum.JPG.getExt());
        } else if (producto.getImagen().getContentType().contains("png")) {
            nuevoProducto.setImagen(producto.getIdproducto() + ExtencionesEnum.PNG.getExt());
        } else if (producto.getImagen().getContentType().contains("gif")) {
            nuevoProducto.setImagen(producto.getIdproducto() + ExtencionesEnum.GIF.getExt());
        } else {
            nuevoProducto.setImagen(producto.getImagen().getOriginalFilename());
        }
        
        productoDao.Update(nuevoProducto);
    }

}
