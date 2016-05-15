/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.mapper;

import co.facturador.dto.SelectItemDto;
import co.facturador.entity.Productos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public class SelectMapper {
    
    /**
     * Devuelve una lista de productos para ser mostrada en un ComboBox
     * @param productos
     * @return 
     */
    public static List<SelectItemDto> listoProductosTolistSelectItem(List<Productos> productos){
        List<SelectItemDto> items = null;
        if(productos!=null){
            if(productos.size()>0){
                items = new ArrayList<>();
                for (Productos producto : productos) {
                    items.add(productoToSelectItem(producto));
                }
            }
        }
        
        return items;
    }
    /**
     * Este método recibe un producto y lo convierte e un item select
     * @param producto
     * @return 
     */
    public static SelectItemDto productoToSelectItem(Productos producto){
        Float precio = producto.getValorProducto();
        
        return new SelectItemDto(producto.getCodigoProducto(),producto.getDescripcionProducto(),
        precio.longValue());
    }
}
