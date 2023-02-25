/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.util;

import com.mycompany.dto.ReporteComprasSedeDto;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public class OperacionesUtil {

    public static void promedioCompras(List<ReporteComprasSedeDto> compras) {
        if (compras != null) {
            for (ReporteComprasSedeDto compra : compras) {
                Double promedio = 0D;
                promedio = compra.getDetalle_factura_valor_producto() / compra.getNumero_unidades();
                compra.setPromedio(promedio);
            }
        }
    }
    
    
}
