/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import domicilios.dto.ProductoDto;
import domicilios.entidad.Producto;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author user
 */
public interface ProductoDao extends GenericDao<Producto>{
    List<ProductoDto> findAllPageSql(Integer first,Integer cantidad,HashMap<String,Object> parametros);
    List<ProductoDto> searchAllPageSql(Integer first,Integer cantidad,HashMap<String,Object> parametros);
    Integer countProducts(HashMap<String,Object> parametros);
}
