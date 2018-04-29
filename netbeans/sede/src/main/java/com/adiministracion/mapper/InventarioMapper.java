/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;

import com.administracion.dto.InventarioDTO;
import com.administracion.entidad.Inventario;



/**
 *
 * @author edeani
 */
public class InventarioMapper {
    
    public String FacturasToString(Inventario inventario){
        
        return inventario.getCodigoProductoInventario()+","+
                inventario.getDescripcionProducto()+","+inventario.getPromedio();
        
    }
    
    public InventarioDTO tramaProductoToInventarioDTO(String tramaProducto){
        
        InventarioDTO inventarioDTO = new InventarioDTO();
        
        String arrInventarioDTO[] = tramaProducto.split("@");
        
        inventarioDTO.setCodigoProductoInventario(arrInventarioDTO[0]);
        inventarioDTO.setDescripcionProducto(arrInventarioDTO[1]);
        inventarioDTO.setStockMinimo(arrInventarioDTO[2]);
        inventarioDTO.setStockReal(arrInventarioDTO[5]);
        inventarioDTO.setFechaFinal(arrInventarioDTO[6]);
        inventarioDTO.setStockHoy(arrInventarioDTO[3]);
        inventarioDTO.setFechaInicial(arrInventarioDTO[4]);
        inventarioDTO.setPromedio(arrInventarioDTO[7]);
        
        return inventarioDTO;
    }   
}
