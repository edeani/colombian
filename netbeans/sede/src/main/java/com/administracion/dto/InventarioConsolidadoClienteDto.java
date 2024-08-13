package com.administracion.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Anlod
 */
@Data
@NoArgsConstructor
public class InventarioConsolidadoClienteDto {
    
    private String subsede;
    private String codigoProductoInventario;
    private String nombreProducto;
    private Float unidades;
    private Float promedioValorProducto;
    private Float totalInventarioProducto;
    
}
