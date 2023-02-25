/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.mapper;

import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaDTO;
import com.colombian.cali.colombiancaliycali.entidades.CambioSede;
import java.util.Date;

/**
 *
 * @author edeani
 */
public class CambioSedeMapper {
    public CambioSede detalleDtoTOtraslado(DetalleFacturaDTO detalleFacturaDTO){
        
        CambioSede traslado = new CambioSede();
        traslado.setNumeroFactura(Long.parseLong(detalleFacturaDTO.getNumeroFactura()));
        traslado.setSedeDestino(detalleFacturaDTO.getSede());
        traslado.setFechaTraslado(new Date());
        return traslado;
    }
}
