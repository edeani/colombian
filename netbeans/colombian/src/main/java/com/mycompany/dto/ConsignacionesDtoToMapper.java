/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

import com.mycompany.entidades.Consignaciones;
import com.mycompany.mapper.ConsignacionesMapper;
import com.mycompany.util.Formatos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseefren
 */
public class ConsignacionesDtoToMapper {
    
    public List<ConsignacionesMapper> consignacionDtoToMapper(List<Consignaciones> consignaciones){
        
        
        List<ConsignacionesMapper> consignacionesMapper = new ArrayList<ConsignacionesMapper>();
        
        Formatos formato = new Formatos();
        for (int i = 0; i < consignaciones.size(); i++) {
            ConsignacionesMapper consignacionMapper = new ConsignacionesMapper();
            Consignaciones consignacion = consignaciones.get(i);
            
            consignacionMapper.setFechaConsignacion(consignacion.getFechaConsignacion());
            consignacionMapper.setNombreCajero(consignacion.getNombreCajero());
            consignacionMapper.setValorConsignacion(formato.numeroToStringFormato(consignacion.getValorConsignacion()));
            
            consignacionesMapper.add(consignacionMapper);
        }
        return consignacionesMapper;
   }
    
}
