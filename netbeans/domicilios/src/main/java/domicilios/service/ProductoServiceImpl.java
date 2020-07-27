/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.dao.CategoriaDao;
import domicilios.dao.ProductoDao;
import domicilios.dto.ProductoDto;
import domicilios.entidad.Categoria;
import domicilios.entidad.Producto;
import domicilios.util.Util;
import java.util.HashMap;
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
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoDao productoDao;
    
    @Autowired
    private CategoriaDao categoriaDao;
    
    @Value("${producto.cantidad}")
    Integer cantidad;
    
    @Transactional(readOnly = true)
    @Override
    public List<ProductoDto> listAllPage(Integer page) {
       Integer firstItem  = Util.firstItemPage(page,cantidad);
       return productoDao.findAllPageSql(firstItem+1,cantidad,null);
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
    @Transactional(readOnly = true)
    public List<ProductoDto> searchProductPage(Integer page, Integer idCategory) {
        Integer firstItem  = Util.firstItemPage(page,cantidad);
        HashMap<String,Object> parameteres =  new HashMap<>();
        if(idCategory!=null){
            parameteres.put("p.idCategoria", idCategory);
        }
        
        return productoDao.searchAllPageSql(firstItem+1, cantidad, parameteres);
    }

    @Override
    public Integer cantidadProductosFilter(HashMap<String, Object> filterParameters) {
        return productoDao.countProducts(filterParameters);
    }
    
}
