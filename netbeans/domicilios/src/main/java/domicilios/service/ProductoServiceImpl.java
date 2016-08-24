/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.dao.ProductoDao;
import domicilios.dto.ProductoDto;
import domicilios.util.Util;
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
    
    @Value("${producto.cantidad}")
    Integer cantidad;
    
    @Transactional(readOnly = true)
    @Override
    public List<ProductoDto> listAllPage(Integer page) {
       return productoDao.findAllPageSql(Util.firstItemPage(page,cantidad),cantidad,null);
    }

    @Override
    @Transactional(readOnly = true)
    public Integer numeroProducto() {
        return productoDao.findAll().size();
    }
    
}
