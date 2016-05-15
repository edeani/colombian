/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.services;

import co.facturador.daos.ProductosJpaController;
import co.facturador.entity.Productos;
import co.facturador.services.util.GenericService;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public class ProductoServiceImpl extends GenericService implements ProductoService{

    @Override
    public List<Productos> allProducts() {
        ProductosJpaController productoDao = new ProductosJpaController(getFactory());
        return productoDao.findProductosEntities();
    } 
    
}
