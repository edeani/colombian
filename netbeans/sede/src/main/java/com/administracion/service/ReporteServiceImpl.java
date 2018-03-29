/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.adiministracion.mapper.MovimientoCajaMapper;
import com.adiministracion.mapper.PagosMapper;
import com.administracion.dao.CierreSedesDao;
import com.administracion.dao.ClasePagoDao;
import com.administracion.dao.PagosDao;
import com.administracion.dao.ReportesDao;
import com.administracion.dao.SedesDao;
import com.administracion.dao.SubSedesDao;
import com.administracion.dto.BalanceDto;
import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.DetallePagosCosolidadoSedeDto;
import com.administracion.dto.EstadoPerdidaGananciaProvisionalDto;
import com.administracion.dto.MovimientoCajaDto;
import com.administracion.dto.PagosConsolidadoSedeDto;
import com.administracion.dto.ReporteConsolidadoDto;
import com.administracion.dto.ReporteTotalCuentasXNivelDto;
import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.ClasePago;
import com.administracion.entidad.DetallePorcentajeVentas;
import com.administracion.entidad.PorcentajeVentas;
import com.administracion.entidad.Sedes;
import com.administracion.entidad.SubSedes;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.util.Formatos;
import com.administracion.util.LectorPropiedades;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EderArmando
 */
@Service
public class ReporteServiceImpl extends GenericService implements ReporteService{

    @Autowired
    private SubSedesDao subSedesDao;
    
    @Autowired
    private ReportesDao reportesDao;
    
    @Autowired
    private CierreSedesDao cierreSedesDao;
    @Autowired
    private PagosDao pagosDao;
    @Autowired
    private SedesDao sedesDao;
    @Autowired
    private ClasePagoDao clasePagoDao;
    @Autowired
    private LectorPropiedades lectorPropiedades;
    private  final String conexion_principal = "dataSource";
    private  final String cuenta_ventas = "414015";
    private  final String cuenta_consignaciones = "11050501";
    private  final String cuenta_pagos_con_tarjeta = "11201010";
    private  final String cuenta_descuentos = "421040";
    private  final String propiedades_cuentas = "/bd/cuentas.properties";
    private  final String propiedad_ingresos = "prefijo_ingresos";
    private  final String propiedad_gastos = "prefijo_gastos";
    private  final String propiedad_costos = "prefijo_costos";
    private  final String propiedad_bdprincipal = "sede_principal";
    
    
    @Override
    @Transactional(readOnly = true)
    public List<ReporteConsolidadoDto> reporteConsolidado(Integer idSede,String fechaInicial, String fechaFinal) {
        List<SubSedesDto> subSedes = subSedesDao.subsedesXIdSede(idSede);
        return reportesDao.reporteConsolidado(subSedes, fechaInicial, fechaFinal);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ComprobanteConsolidadoSedeDto> comprobanteConsolidado(String nameDataSourceSede,Integer idSubSede, Date fecha) {
        
        SubSedes subSedes = subSedesDao.findById(idSubSede);

        List<ComprobanteConsolidadoSedeDto> comprobante = new ArrayList<>();

        //Ventas
        ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDtoVentas = new ComprobanteConsolidadoSedeDto();
        String sfecha = Formatos.dateTostring(fecha);
        comprobanteConsolidadoSedeDtoVentas.setFecha(sfecha);
        comprobanteConsolidadoSedeDtoVentas.setIdSede(idSubSede.longValue());
        comprobanteConsolidadoSedeDtoVentas.setSede(subSedes.getSede());

        Long totalVentas = reportesDao.totalConsolidadoSede(subSedes, sfecha);

        comprobanteConsolidadoSedeDtoVentas.setTotal(totalVentas);
        comprobanteConsolidadoSedeDtoVentas.setConcepto("Ventas " + subSedes.getSede());
        comprobanteConsolidadoSedeDtoVentas.setIdCuenta(cuenta_ventas);

        //Consignaciones
        ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDtoConsignaciones = new ComprobanteConsolidadoSedeDto();
        comprobanteConsolidadoSedeDtoConsignaciones.setFecha(sfecha);
        comprobanteConsolidadoSedeDtoConsignaciones.setIdSede(idSubSede.longValue());
        comprobanteConsolidadoSedeDtoConsignaciones.setSede(subSedes.getSede());

        Long consignaciones = reportesDao.consignacionesConsolidadoSede(subSedes, sfecha);

        comprobanteConsolidadoSedeDtoConsignaciones.setTotal(consignaciones);
        comprobanteConsolidadoSedeDtoConsignaciones.setConcepto("Consignaciones " + subSedes.getSede());
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
        List<ComprobanteConsolidadoSedeDto> gastos = reportesDao.buscarGastosXFecha(connectsAuth.getDataSourceSubSede(subSedes.getSede()), sfecha);
        if (gastos != null) {
            gastos.stream().map((gasto) -> {
                gasto.setIdSede(idSubSede.longValue());
                return gasto;
            }).forEachOrdered((gasto) -> {
                gasto.setSede(subSedes.getSede());
            });
        }
        if (gastos != null) {
            comprobante.addAll(gastos);
        }

        /**
         * Pagos con tarjeta
         */
        DataSource ds =connectsAuth.getDataSourceSubSede(subSedes.getSede());
        ClasePago clasePago = clasePagoDao.findClasePagoById(1,ds);
        if (clasePago.getEstado().equals("A")) {
            Long pagosContarjeta = reportesDao.pagosContarjetaTotal(ds, sfecha);
            if (pagosContarjeta != null) {
                if (pagosContarjeta != 0L) {
                    ComprobanteConsolidadoSedeDto comprobantePagosConTarjeta = new ComprobanteConsolidadoSedeDto();
                    comprobantePagosConTarjeta.setTotal(pagosContarjeta);
                    comprobantePagosConTarjeta.setConcepto("Pagos con Tarjeta " + subSedes.getSede());
                    comprobantePagosConTarjeta.setFecha(sfecha);
                    comprobantePagosConTarjeta.setIdCuenta(cuenta_pagos_con_tarjeta);
                    comprobantePagosConTarjeta.setIdSede(idSubSede.longValue());
                    comprobantePagosConTarjeta.setSede(subSedes.getSede());
                    comprobante.add(comprobantePagosConTarjeta);
                }
            }
        }
        /**
         * Descuento de los pagos
         */
        clasePago = clasePagoDao.findClasePagoById(2, ds);
        if (clasePago.getEstado().equals("A")) {
            Long pagosDescuento = reportesDao.pagosDescuentoTotal(ds, sfecha);
            if (pagosDescuento != null) {
                if (pagosDescuento != 0L) {
                    ComprobanteConsolidadoSedeDto comprobantePagosDescuento = new ComprobanteConsolidadoSedeDto();
                    comprobantePagosDescuento.setTotal(pagosDescuento);
                    comprobantePagosDescuento.setConcepto("Descuentos " + subSedes.getSede());
                    comprobantePagosDescuento.setFecha(sfecha);
                    comprobantePagosDescuento.setIdCuenta(cuenta_descuentos);
                    comprobantePagosDescuento.setIdSede(idSubSede.longValue());
                    comprobantePagosDescuento.setSede(subSedes.getSede());
                    comprobante.add(comprobantePagosDescuento);
                }
            }
        }
        return comprobante;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoCajaDto> movimientoCajaMayor(String nameDataSource, Date fechaInicial, Date fechaFinal) {

        String sfechaInicial = Formatos.dateTostring(fechaInicial);
        String sfechaFinal = Formatos.dateTostring(fechaFinal);
        List<ComprobanteConsolidadoSedeDto> movs = reportesDao.bucarMovimientoCajaMayor(connectsAuth.getDataSourceSede(nameDataSource), sfechaInicial, sfechaFinal);

        MovimientoCajaMapper movimientoCajaMayorMapper = new MovimientoCajaMapper();
        List<MovimientoCajaDto> movimientos = movimientoCajaMayorMapper.comprobanteConsolidadoSedeDtoToMovimietoCajaMayorDto(movs);

        return movimientos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoCajaDto> movimientoCajaMenor(String nameDataSource, String fechaInicial, String fechaFinal) {

        List<ComprobanteConsolidadoSedeDto> movs = reportesDao.bucarMovimientoCajaMenor(connectsAuth.getDataSourceSubSede(nameDataSource), fechaInicial, fechaFinal);

        MovimientoCajaMapper movimientoCajaMapper = new MovimientoCajaMapper();
        List<MovimientoCajaDto> movimientos = movimientoCajaMapper.comprobanteConsolidadoSedeDtoToMovimietoCajaMenorDto(movs);

        return movimientos;
    }

    @Override
    @Transactional(readOnly = true)
    public PagosConsolidadoSedeDto generarPagoConsolidadoSedePorcentaje(String nameDataSource, int mes) {
        PagosMapper pagosMapper = new PagosMapper();
        DataSource ds = connectsAuth.getDataSourceSede(nameDataSource);
        PorcentajeVentas porcentajeVentas = reportesDao.buscarPagoConsolidadoMes(ds, mes);
        PagosConsolidadoSedeDto pagosConsolidadoSedeDto = pagosMapper.porcentajeVentaTopagosConsolidadoSedeDto(porcentajeVentas);

        List<DetallePorcentajeVentas> detallePorcentajeVentases = reportesDao.buscarDetallePagoConsolidadoMes(ds, mes);
        List<DetallePagosCosolidadoSedeDto> detallePagosCosolidadoSedeDtos = pagosMapper.detallePorcentajeVentaToDetallePagosCosolidadoSedeDto(detallePorcentajeVentases);
        if (detallePagosCosolidadoSedeDtos != null) {
            detallePagosCosolidadoSedeDtos.forEach((detallePagosCosolidadoSedeDto) -> {
                Sedes sedes = sedesDao.buscarSede(detallePagosCosolidadoSedeDto.getIdSede());
                detallePagosCosolidadoSedeDto.setNombreSede(sedes.getSede());
            });
        }
        pagosConsolidadoSedeDto.setDetallePagosCosolidadoSedeDtos(detallePagosCosolidadoSedeDtos);

        return pagosConsolidadoSedeDto;
    }

    @Override
    @Transactional(readOnly = true)
    public ReporteTotalCuentasXNivelDto reportePerdidaIngresoTotalXNivel(String nameDataSource, String fechInicial, String fechaFinal) {
        lectorPropiedades.setArchivo(propiedades_cuentas);
        int ingresos = Integer.parseInt(lectorPropiedades.leerPropiedad(propiedad_ingresos));
        ReporteTotalCuentasXNivelDto ingresoxnivel = cierreSedesDao.totalCierreCuentaXNivel(connectsAuth.getDataSourceSubSede(nameDataSource), ingresos, fechInicial, fechaFinal);
        return ingresoxnivel;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteTotalCuentasXNivelDto> reportePerdidaIngresoTotalXNivelSede(String nameDataSource, String fechInicial, String fechaFinal) {
        lectorPropiedades.setArchivo(propiedades_cuentas);
        int ingresos = Integer.parseInt(lectorPropiedades.leerPropiedad(propiedad_ingresos));
        Long sedePrincipal = Long.parseLong(lectorPropiedades.leerPropiedad(propiedad_bdprincipal));
        List<Sedes> sedes = sedesDao.findAll();
        List<ReporteTotalCuentasXNivelDto> reporteIngresoxNivel = new ArrayList<>();
        sedes.stream().filter((sedes1) -> (!Objects.equals(sedes1.getIdsedes(), sedePrincipal))).map((Sedes sedes1) -> {
            ReporteTotalCuentasXNivelDto ingresoxnivel = cierreSedesDao.totalCierreCuentaXNivelSede(connectsAuth.getDataSourceSubSede(nameDataSource), sedes1.getIdsedes().longValue(), ingresos, fechInicial, fechaFinal);
            ingresoxnivel.setIdSede(sedes1.getIdsedes().longValue());
            ingresoxnivel.setSede(sedes1.getSede());
            return ingresoxnivel;
        }).forEachOrdered((ingresoxnivel) -> {
            reporteIngresoxNivel.add(ingresoxnivel);
        });

        return reporteIngresoxNivel;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisional(String nameDataSource, String fechInicial, String fechaFinal) {
        return reportesDao.reporteEstadoPerdidaGananciaProvisional(connectsAuth.getDataSourceSede(nameDataSource), fechInicial, fechaFinal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisionalXSede(String nameDataSource, String fechInicial, String fechaFinal, Long idSede) {
        return reportesDao.reporteEstadoPerdidaGananciaProvisionalXSede(connectsAuth.getDataSourceSede(nameDataSource), fechInicial, fechaFinal, idSede);
    }

    /**
     * Si la sede viene nula el dao ejecutala consulta general
     *
     * @param nameDataSource
     * @param fechInicial
     * @param fechaFinal
     * @param idsede
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<BalanceDto> reporteBalanceService(String nameDataSource, String fechInicial, String fechaFinal, Long idsede) {
        return reportesDao.reporteBalance(connectsAuth.getDataSourceSubSede(nameDataSource), fechInicial, fechaFinal, idsede);
    }

    
}
