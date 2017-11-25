/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;


import com.administracion.dto.DetalleFacturaDTO;
import com.administracion.entidad.CambioSede;
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
