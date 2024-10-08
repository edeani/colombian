/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.CategoriaDao;
import com.administracion.dao.ProductoDao;
import com.administracion.dto.ProductoAutocompletarDto;
import com.administracion.dto.ProductoDetailDto;
import com.administracion.dto.ProductoDto;
import com.administracion.entidad.Categoria;
import com.administracion.entidad.Producto;
import com.administracion.enumeration.ExtencionesEnum;
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

    @Value("${producto.autocompletar}")
    Integer autocompletar;
    
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
        nuevoProducto.setIdCategoria(producto.getIdCategoria());
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

    @Override
    @Transactional(readOnly = true)
    public Producto findProductoXid(Integer idproducto) {
        return productoDao.findById(idproducto);
    }

    @Override
    @Transactional
    public void actualizarProductoAdministrador(ProductoDetailDto productoDetailDto) {
        Producto producto = productoDao.findById(productoDetailDto.getIdproducto());

        producto.setDescripcion(productoDetailDto.getDescripcion());
        producto.setImagen(productoDetailDto.getRutaImagen());
        producto.setNombreproducto(productoDetailDto.getNombreproducto());
        producto.setPrecioproducto(productoDetailDto.getPrecioproducto());
        producto.setIdCategoria(productoDetailDto.getIdCategoria());
        if (producto.getImagen() == null) {
            producto.setImagen("");
        }
        if (producto.getImagen().isEmpty()) {
            if (productoDetailDto.getImagen().getContentType().contains("jpeg")) {
                producto.setImagen(productoDetailDto.getIdproducto() + ExtencionesEnum.JPG.getExt());
            } else if (productoDetailDto.getImagen().getContentType().contains("png")) {
                producto.setImagen(productoDetailDto.getIdproducto() + ExtencionesEnum.PNG.getExt());
            } else if (productoDetailDto.getImagen().getContentType().contains("gif")) {
                producto.setImagen(productoDetailDto.getIdproducto() + ExtencionesEnum.GIF.getExt());
            } else {
                producto.setImagen(productoDetailDto.getImagen().getOriginalFilename());
            }
        } 
        //Actualizo la ruta para el objeto vista en pantalla
        productoDetailDto.setRutaImagen(producto.getImagen());
        productoDao.Update(producto);
    }

    @Override
    @Transactional
    public void activarProductoXid(Integer idproducto) {
        Producto producto = productoDao.findById(idproducto);
        producto.setEstado("A");
        productoDao.Update(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoAutocompletarDto> autocompletarProducto(String nombreproducto) {
        return productoDao.findProductoLikeNombre(nombreproducto, autocompletar);
    }

}
