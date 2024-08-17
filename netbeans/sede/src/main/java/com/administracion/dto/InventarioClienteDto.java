package com.administracion.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Anlod
 */
@Data
@NoArgsConstructor
public class InventarioClienteDto {
    
    public static String[] fieldsOrder ={"telefono","codigoProductoInventario","nombreProducto","unidades","promedioValorProducto","totalInventarioProducto"};
    public static String [] fieldsType ={"string","string","string","float","float","float"};
    
    private String telefono;
    private String codigoProductoInventario;
    private String nombreProducto;
    private Float unidades;
    private Float promedioValorProducto;
    private Float totalInventarioProducto;
    
}
