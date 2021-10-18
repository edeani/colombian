/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.BalanceDto;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.EstadoPerdidaGananciaProvisionalDto;
import com.colombian.cali.colombiancaliycali.dto.MovimientoCajaDto;
import com.colombian.cali.colombiancaliycali.dto.PagosConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteConsolidadoDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteTotalCuentasXNivelDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface ReportesService {
    
    public List<ReporteConsolidadoDto> reporteConsolidado(String nameDatasource,String fechaInicial,String fechaFinal);
    public List<ComprobanteConsolidadoSedeDto> comprobanteConsolidado(Long idSede,Date fecha);
    public List<MovimientoCajaDto> movimientoCajaMayor(String nameDataSource,Date fechaInicial,Date fechaFinal);
    public List<MovimientoCajaDto> movimientoCajaMenor(String nameDataSource, String fechaInicial, String fechaFinal);
    public PagosConsolidadoSedeDto generarPagoConsolidadoSedePorcentaje(String nameDataSource,int mes);
    public ReporteTotalCuentasXNivelDto reportePerdidaIngresoTotalXNivel(String nameDataSource,String fechInicial, String fechaFinal);
    public List<ReporteTotalCuentasXNivelDto> reportePerdidaIngresoTotalXNivelSede(String nameDataSource,String fechInicial, String fechaFinal);
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisional(String nameDataSource,String fechInicial, String fechaFinal);
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisionalXSede(String nameDataSource,String fechInicial, String fechaFinal,Long idSede);
    public List<BalanceDto> reporteBalanceService(String nameDataSource,String fechInicial, String fechaFinal,Long idsede);
}
