/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dto.CierreSedesDto;
import com.administracion.dto.ComprobanteCierreSedesDto;
import com.administracion.dto.ReporteComprobanteCierreDto;
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
