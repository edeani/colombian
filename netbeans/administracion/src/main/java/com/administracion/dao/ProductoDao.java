/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.dto.ProductoDto;
import com.administracion.entidad.Producto;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author user
 */
public interface ProductoDao extends GenericDao<Producto>{
    List<ProductoDto> findAllPageSql(Integer first,Integer cantidad,HashMap<String,Object> parametros);
}
