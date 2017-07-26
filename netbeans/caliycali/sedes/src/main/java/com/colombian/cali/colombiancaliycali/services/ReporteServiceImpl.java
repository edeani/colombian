/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.CierreSedesDao;
import com.colombia.cali.colombiancaliycali.dao.PagosDao;
import com.colombia.cali.colombiancaliycali.dao.ReportesDao;
import com.colombia.cali.colombiancaliycali.dao.SedesDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombia.cali.colombiancaliycali.util.GenericService;
import com.colombian.cali.colombiancaliycali.dto.BalanceDto;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosCosolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.EstadoPerdidaGananciaProvisionalDto;
import com.colombian.cali.colombiancaliycali.dto.MovimientoCajaDto;
import com.colombian.cali.colombiancaliycali.dto.PagosConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteConsolidadoDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteTotalCuentasXNivelDto;
import com.colombian.cali.colombiancaliycali.entidades.DetallePorcentajeVentas;
import com.colombian.cali.colombiancaliycali.entidades.PorcentajeVentas;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import com.colombian.cali.colombiancaliycali.mapper.MovimientoCajaMapper;
import com.colombian.cali.colombiancaliycali.mapper.PagosMapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jose Efren
 */
@Service
public class ReporteServiceImpl extends GenericService implements ReportesService {

    @Autowired
    private CierreSedesDao cierreSedesDao;
    @Autowired
    private PagosDao pagosDao;
    @Autowired
    private ReportesDao reportesDao;
    @Autowired
    private SedesDao sedesDao;
    private static final String conexion_principal = "dataSource";
    private static final String cuenta_ventas = "414015";
    private static final String cuenta_consignaciones = "11050501";
    private static final String cuenta_pagos_con_tarjeta = "11201010";
    private static final String cuenta_descuentos = "421040";
    private static final String propiedades_cuentas = "/bd/cuentas.properties";
    private static final String propiedad_ingresos = "prefijo_ingresos";
    private static final String propiedad_gastos = "prefijo_gastos";
    private static final String propiedad_costos = "prefijo_costos";
    private static final String propiedad_bdprincipal = "sede_principal";
    
    @Override
    @Transactional(readOnly = true)
    public List<ReporteConsolidadoDto> reporteConsolidado(String nameDatasource, String fechaInicial, String fechaFinal) {
        List<ReporteConsolidadoDto> reporte = null;
        List<Sedes> sedes = sedesDao.listSedes();

        reporte = reportesDao.reporteConsolidado(sedes, fechaInicial, fechaFinal);

        return reporte;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprobanteConsolidadoSedeDto> comprobanteConsolidado(Long idSede, Date fecha) {
        Sedes sede = sedesDao.findSede(idSede);

        List<ComprobanteConsolidadoSedeDto> comprobante = new ArrayList<ComprobanteConsolidadoSedeDto>();

        //Ventas
        ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDtoVentas = new ComprobanteConsolidadoSedeDto();
        String sfecha = Formatos.dateTostring(fecha);
        comprobanteConsolidadoSedeDtoVentas.setFecha(sfecha);
        comprobanteConsolidadoSedeDtoVentas.setIdSede(idSede);
        comprobanteConsolidadoSedeDtoVentas.setSede(sede.getSede());

        Long totalVentas = reportesDao.totalConsolidadoSede(sede, sfecha);

        comprobanteConsolidadoSedeDtoVentas.setTotal(totalVentas);
        comprobanteConsolidadoSedeDtoVentas.setConcepto("Ventas " + sede.getSede());
        comprobanteConsolidadoSedeDtoVentas.setIdCuenta(cuenta_ventas);

        //Consignaciones
        ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDtoConsignaciones = new ComprobanteConsolidadoSedeDto();
        comprobanteConsolidadoSedeDtoConsignaciones.setFecha(sfecha);
        comprobanteConsolidadoSedeDtoConsignaciones.setIdSede(idSede);
        comprobanteConsolidadoSedeDtoConsignaciones.setSede(sede.getSede());

        Long consignaciones = reportesDao.consignacionesConsolidadoSede(sede, sfecha);

        comprobanteConsolidadoSedeDtoConsignaciones.setTotal(consignaciones);
        comprobanteConsolidadoSedeDtoConsignaciones.setConcepto("Consignaciones " + sede.getSede());
        comprobanteConsolidadoSedeDtoConsignaciones.setIdCuenta(cuenta_consignaciones);

        boolean agregarRegistro = false;
        if (totalVentas != 0L) {
            agregarRegistro = true;
        }

        if (consignaciones != 0L) {
            agregarRegistro = true;
        }

        if (agregarRegistro) {
            comprobante.add(comprobanteConsolidadoSedeDtoVentas);
            comprobante.add(comprobanteConsolidadoSedeDtoConsignaciones);
        }
        //Gastos
        List<ComprobanteConsolidadoSedeDto> gastos = reportesDao.buscarGastosXFecha(sede.getSede(), sfecha);
        if(gastos!=null){
            for (ComprobanteConsolidadoSedeDto gasto : gastos) {
                gasto.setIdSede(idSede);
                gasto.setSede(sede.getSede());
            }
        }
        if (gastos != null) {
            comprobante.addAll(gastos);
        }

        /**
         * Pagos con tarjeta
         */
        Long pagosContarjeta = reportesDao.pagosContarjetaTotal(sede.getSede(), sfecha);
        if(pagosContarjeta!=null){
            if(pagosContarjeta!=0L){
                ComprobanteConsolidadoSedeDto comprobantePagosConTarjeta = new ComprobanteConsolidadoSedeDto();
                comprobantePagosConTarjeta.setTotal(pagosContarjeta);
                comprobantePagosConTarjeta.setConcepto("Pagos con Tarjeta "+sede.getSede());
                comprobantePagosConTarjeta.setFecha(sfecha);
                comprobantePagosConTarjeta.setIdCuenta(cuenta_pagos_con_tarjeta);
                comprobantePagosConTarjeta.setIdSede(idSede);
                comprobantePagosConTarjeta.setSede(sede.getSede());
                comprobante.add(comprobantePagosConTarjeta);
            }
        }
        
        /**
         * Descuento de los pagos
         */
        Long pagosDescuento = reportesDao.pagosDescuentoTotal(sede.getSede(), sfecha);
         if(pagosContarjeta!=null){
            if(pagosContarjeta!=0L){
                ComprobanteConsolidadoSedeDto comprobantePagosDescuento = new ComprobanteConsolidadoSedeDto();
                comprobantePagosDescuento.setTotal(pagosDescuento);
                comprobantePagosDescuento.setConcepto("Descuentos "+sede.getSede());
                comprobantePagosDescuento.setFecha(sfecha);
                comprobantePagosDescuento.setIdCuenta(cuenta_descuentos);
                comprobantePagosDescuento.setIdSede(idSede);
                comprobantePagosDescuento.setSede(sede.getSede());
                comprobante.add(comprobantePagosDescuento);
            }
        }
        return comprobante;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoCajaDto> movimientoCajaMayor(String nameDataSource, Date fechaInicial, Date fechaFinal) {

        String sfechaInicial = Formatos.dateTostring(fechaInicial);
        String sfechaFinal = Formatos.dateTostring(fechaFinal);
        List<ComprobanteConsolidadoSedeDto> movs = reportesDao.bucarMovimientoCajaMayor(nameDataSource, sfechaInicial, sfechaFinal);

        MovimientoCajaMapper movimientoCajaMayorMapper = new MovimientoCajaMapper();
        List<MovimientoCajaDto> movimientos = movimientoCajaMayorMapper.comprobanteConsolidadoSedeDtoToMovimietoCajaMayorDto(movs);

        return movimientos;
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<MovimientoCajaDto> movimientoCajaMenor(String nameDataSource, String fechaInicial, String fechaFinal) {

        List<ComprobanteConsolidadoSedeDto> movs = reportesDao.bucarMovimientoCajaMenor(nameDataSource, fechaInicial, fechaFinal);

        MovimientoCajaMapper movimientoCajaMapper = new MovimientoCajaMapper();
        List<MovimientoCajaDto> movimientos = movimientoCajaMapper.comprobanteConsolidadoSedeDtoToMovimietoCajaMenorDto(movs);

        return movimientos;
    }

    @Override
    @Transactional(readOnly = true)
    public PagosConsolidadoSedeDto generarPagoConsolidadoSedePorcentaje(String nameDataSource, int mes) {
        PagosMapper pagosMapper = new PagosMapper();
        PorcentajeVentas porcentajeVentas = reportesDao.buscarPagoConsolidadoMes(nameDataSource, mes);
        PagosConsolidadoSedeDto pagosConsolidadoSedeDto = pagosMapper.porcentajeVentaTopagosConsolidadoSedeDto(porcentajeVentas);

        List<DetallePorcentajeVentas> detallePorcentajeVentases = reportesDao.buscarDetallePagoConsolidadoMes(nameDataSource, mes);
        List<DetallePagosCosolidadoSedeDto> detallePagosCosolidadoSedeDtos = pagosMapper.detallePorcentajeVentaToDetallePagosCosolidadoSedeDto(detallePorcentajeVentases);
        if (detallePagosCosolidadoSedeDtos != null) {
            for (DetallePagosCosolidadoSedeDto detallePagosCosolidadoSedeDto : detallePagosCosolidadoSedeDtos) {
                Sedes sedes = sedesDao.findSede(detallePagosCosolidadoSedeDto.getIdSede());
                detallePagosCosolidadoSedeDto.setNombreSede(sedes.getSede());
            }
        }
        pagosConsolidadoSedeDto.setDetallePagosCosolidadoSedeDtos(detallePagosCosolidadoSedeDtos);

        return pagosConsolidadoSedeDto;
    }

    @Override
    @Transactional(readOnly = true)
    public ReporteTotalCuentasXNivelDto reportePerdidaIngresoTotalXNivel(String nameDataSource, String fechInicial, String fechaFinal) {
        getPropiedades().setArchivo(propiedades_cuentas);
        int ingresos = Integer.parseInt(getPropiedades().leerPropiedad(propiedad_ingresos));
        ReporteTotalCuentasXNivelDto ingresoxnivel = cierreSedesDao.totalCierreCuentaXNivel(nameDataSource, ingresos, fechInicial, fechaFinal);
        return ingresoxnivel;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteTotalCuentasXNivelDto> reportePerdidaIngresoTotalXNivelSede(String nameDataSource, String fechInicial, String fechaFinal) {
        getPropiedades().setArchivo(propiedades_cuentas);
        int ingresos = Integer.parseInt(getPropiedades().leerPropiedad(propiedad_ingresos));
        Long sedePrincipal = Long.parseLong(getPropiedades().leerPropiedad(propiedad_bdprincipal));
        List<Sedes> sedes = sedesDao.listSedes();
        List<ReporteTotalCuentasXNivelDto> reporteIngresoxNivel =  new ArrayList<ReporteTotalCuentasXNivelDto>();
        for (Sedes sedes1 : sedes) {
            if(sedes1.getIdsedes() != sedePrincipal){
                ReporteTotalCuentasXNivelDto ingresoxnivel = cierreSedesDao.totalCierreCuentaXNivelSede(nameDataSource,sedes1.getIdsedes(), ingresos, fechInicial, fechaFinal);
                ingresoxnivel.setIdSede(sedes1.getIdsedes());
                ingresoxnivel.setSede(sedes1.getSede());
                reporteIngresoxNivel.add(ingresoxnivel);
            }
        }
        
        return reporteIngresoxNivel;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisional(String nameDataSource, String fechInicial, String fechaFinal) {
       return reportesDao.reporteEstadoPerdidaGananciaProvisional(nameDataSource, fechInicial, fechaFinal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisionalXSede(String nameDataSource, String fechInicial, String fechaFinal, Long idSede) {
        return reportesDao.reporteEstadoPerdidaGananciaProvisionalXSede(nameDataSource, fechInicial, fechaFinal, idSede);
    }

    /**
     * Si la sede viene nula el dao ejecutala consulta general
     * @param nameDataSource
     * @param fechInicial
     * @param fechaFinal
     * @param idsede
     * @return 
     */
    @Override
    @Transactional(readOnly = true)
    public List<BalanceDto> reporteBalanceService(String nameDataSource, String fechInicial, String fechaFinal,Long idsede) {
        return reportesDao.reporteBalance(nameDataSource, fechInicial, fechaFinal,idsede);
    }

    
}
