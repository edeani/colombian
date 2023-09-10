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
    
    private String telefono;
    private String codigoProductoInventario;
    private String nombreProducto;
    private Float unidades;
    private Float promedioValorProducto;
    private Float totalInventarioProducto;
    
}
