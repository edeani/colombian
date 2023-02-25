/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.dto.ProductoDto;
import domicilios.entidad.Categoria;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author user
 */
public interface ProductoService {

    List<ProductoDto> listAllPage(Integer page);
    List<ProductoDto> searchProductPage(Integer page,Integer idCategory);
    Integer numeroProducto();
    Integer cantidadProductosFilter(HashMap<String,Object> filterParameters);
    List<Categoria> listCategory();
    void eliminarProductoXid(Integer idproducto);
}
