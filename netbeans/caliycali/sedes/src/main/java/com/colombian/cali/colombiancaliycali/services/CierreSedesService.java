/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.CierreSedesDto;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteCierreSedesDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteComprobanteCierreDto;
import java.util.List;

/**
 *
 * @author user
 */
public interface CierreSedesService {
    public void guardarComprobanteCierreService(String nameDatasource,ComprobanteCierreSedesDto comprobanteCierreSedesDto);
    public CierreSedesDto buscarComprobanteCierreDto(String nameDataSource, Long idComprobanteCierre);
    public List<ReporteComprobanteCierreDto> buscarDetalleComprobanteCierreSedesView(String nameDataSource, Long idComprobanteCierre);
    public List<CierreSedesDto> buscarComprobanteCierreDtoXFecha(String nameDataSource,String fechaInicial,String fechaFinal);
    public List<CierreSedesDto> reporteComprobanteCierreSedesView(String nameDataSource, String fechaInicial, String fechaFinal,Long idsede);
}
