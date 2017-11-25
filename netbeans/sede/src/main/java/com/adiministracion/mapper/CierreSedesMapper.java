/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.adiministracion.mapper;

import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteCierreSedesDto;
import com.colombian.cali.colombiancaliycali.entidades.CierreSedes;

/**
 *
 * @author Jose Efren
 */
public class CierreSedesMapper {
    
    public CierreSedes comprobanteConsolidadoSedeDtoTOCierreSede(ComprobanteCierreSedesDto comprobanteCierreSedesDto){
        CierreSedes cierreSedes = new CierreSedes();
        if(comprobanteCierreSedesDto.getConsecutivo()!=null)
        cierreSedes.setConsecutivo(comprobanteCierreSedesDto.getConsecutivo());
        
        cierreSedes.setFecha(Formatos.StringDateToDate(comprobanteCierreSedesDto.getFecha()));
        cierreSedes.setIdsede(comprobanteCierreSedesDto.getIdSede());
        cierreSedes.setTotaldeber(comprobanteCierreSedesDto.getTotalDeber());
        cierreSedes.setTotalhaber(comprobanteCierreSedesDto.getTotalHaber());
        
        return cierreSedes;
    }
}
