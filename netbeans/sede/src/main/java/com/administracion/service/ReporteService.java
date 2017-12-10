/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.BalanceDto;
import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.EstadoPerdidaGananciaProvisionalDto;
import com.administracion.dto.MovimientoCajaDto;
import com.administracion.dto.PagosConsolidadoSedeDto;
import com.administracion.dto.ReporteConsolidadoDto;
import com.administracion.dto.ReporteTotalCuentasXNivelDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public interface ReporteService {
    public List<ReporteConsolidadoDto> reporteConsolidado(Integer idSede,String fechaInicial,String fechaFinal);
    public List<ComprobanteConsolidadoSedeDto> comprobanteConsolidado(String nameDataSourceSede,Integer idSubSede,Date fecha);
    public List<MovimientoCajaDto> movimientoCajaMayor(String nameDataSource,Date fechaInicial,Date fechaFinal);
    public List<MovimientoCajaDto> movimientoCajaMenor(String nameDataSource, String fechaInicial, String fechaFinal);
    public PagosConsolidadoSedeDto generarPagoConsolidadoSedePorcentaje(String nameDataSource,int mes);
    public ReporteTotalCuentasXNivelDto reportePerdidaIngresoTotalXNivel(String nameDataSource,String fechInicial, String fechaFinal);
    public List<ReporteTotalCuentasXNivelDto> reportePerdidaIngresoTotalXNivelSede(String nameDataSource,String fechInicial, String fechaFinal);
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisional(String nameDataSource,String fechInicial, String fechaFinal);
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisionalXSede(String nameDataSource,String fechInicial, String fechaFinal,Long idSede);
    public List<BalanceDto> reporteBalanceService(String nameDataSource,String fechInicial, String fechaFinal,Long idsede);
}
