/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;

import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaDTO;
import com.colombian.cali.colombiancaliycali.dto.DetalleFacturaTrasladoDTO;
import com.colombian.cali.colombiancaliycali.entidades.Traslado;
import java.util.Date;

/**
 *
 * @author edeani
 */
public class TrasladoMapper {

    public Traslado detalleFacturaTrasladoDtoToTraslado(DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO) {
        Traslado traslado = new Traslado();
        traslado.setFecha(new Date());
        traslado.setIdsedeDestino(detalleFacturaTrasladoDTO.getSedeDestino());
        traslado.setIdsedeOrigen(detalleFacturaTrasladoDTO.getSedeOrigen());
        
        return traslado;
    }
    
    public DetalleFacturaDTO DetalleFacturaTrasladoDtoToDetalleFacturaDTO(DetalleFacturaTrasladoDTO detalleFacturaTrasladoDTO,String sede){
        DetalleFacturaDTO detalleFacturaDTO = new DetalleFacturaDTO();
        String trama = detalleFacturaTrasladoDTO.getFactura();
        String[] factura = trama.split("\\|");
        if(sede.equals("origen")){
            detalleFacturaDTO.setFactura(factura[0]);
            detalleFacturaDTO.setSede(detalleFacturaTrasladoDTO.getSedeOrigen());
            detalleFacturaDTO.setTotalFactura("-"+detalleFacturaTrasladoDTO.getTotalFactura());
        }else if(sede.equals("destino")){
            detalleFacturaDTO.setFactura(factura[1]);
            detalleFacturaDTO.setSede(detalleFacturaTrasladoDTO.getSedeDestino());
            detalleFacturaDTO.setTotalFactura(detalleFacturaTrasladoDTO.getTotalFactura());
        }
        return detalleFacturaDTO;
    }
    
    
}
