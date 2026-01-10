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
import com.administracion.dao.ReportesDao;
import com.administracion.dao.SedesDao;
import com.administracion.dao.SubSedesDao;
import com.administracion.dto.BalanceDto;
import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.ConsolidadoVentasPorcentajeDTO;
import com.administracion.dto.DetallePagosCosolidadoSedeDto;
import com.administracion.dto.EstadoPerdidaGananciaProvisionalDto;
import com.administracion.dto.ItemsHashDTO;
import com.administracion.dto.MovimientoCajaDto;
import com.administracion.dto.PagosConsolidadoSedeDto;
import com.administracion.dto.reports.general.ReporteConsolidadoDto;
import com.administracion.dto.reports.general.ReporteTotalCuentasXNivelDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.dto.TiempoRealSedeDto;
import com.administracion.dto.reports.general.ReporteCuentasDetalleDTO;
import com.administracion.entidad.ClasePago;
import com.administracion.entidad.DetallePorcentajeVentas;
import com.administracion.entidad.PorcentajeVentas;
import com.administracion.entidad.Sedes;
import com.administracion.entidad.SubSedes;
import com.administracion.enumeration.EstadosEnum;
import com.administracion.service.jsf.CierreColombianService;
import com.administracion.util.Formatos;
import com.administracion.util.LectorPropiedades;
import com.mycompany.enums.EnumTipoPagoTarjeta;
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
public class ReporteServiceImpl extends GenericService implements ReporteService {

    @Autowired
    private SubSedesDao subSedesDao;

    @Autowired
    private ReportesDao reportesDao;

    @Autowired
    private CierreSedesDao cierreSedesDao;
    @Autowired
    private SedesDao sedesDao;
    @Autowired
    private ClasePagoDao clasePagoDao;
    @Autowired
    private LectorPropiedades lectorPropiedades;

    @Autowired
    private CierreColombianService cierreColombianService;

    private final String cuenta_ventas = "414015";
    private final String cuenta_consignaciones = "11050501";
    private final String cuenta_pagos_con_tarjeta = "11201010";
    private final String cuenta_propina = "281505";
    private final String cuenta_pagos_nequi = "11201011";
    private final String cuenta_pagos_daviplata = "11201012";
    private final String cuenta_pagos_transferencias = "11201013";

    private final String cuenta_descuentos = "421040";
    private final String propiedades_cuentas = "/bd/cuentas.properties";
    private final String propiedad_ingresos = "prefijo_ingresos";
    private final String propiedad_bdprincipal = "sede_principal";

    @Override
    @Transactional(readOnly = true)
    public List<ReporteConsolidadoDto> reporteConsolidado(Integer idSede, String fechaInicial, String fechaFinal) {
        List<SubSedesDto> subSedes = subSedesDao.subsedesXIdSede(idSede);
        return reportesDao.reporteConsolidado(subSedes, fechaInicial, fechaFinal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComprobanteConsolidadoSedeDto> comprobanteConsolidado(String nameDataSourceSede, Integer idSubSede, Date fecha) {

        SubSedes subSedes = subSedesDao.findById(idSubSede);

        List<ComprobanteConsolidadoSedeDto> comprobantes = new ArrayList<>();

        //Ventas
        ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDtoVentas = new ComprobanteConsolidadoSedeDto();
        String sfecha = Formatos.dateTostring(fecha);
        comprobanteConsolidadoSedeDtoVentas.setFecha(sfecha);
        comprobanteConsolidadoSedeDtoVentas.setIdSede(subSedes.getIdsedepoint().longValue());
        comprobanteConsolidadoSedeDtoVentas.setSede(subSedes.getSede());

        Long totalVentas = reportesDao.totalConsolidadoSede(subSedes, sfecha);

        comprobanteConsolidadoSedeDtoVentas.setTotal(totalVentas);
        comprobanteConsolidadoSedeDtoVentas.setConcepto("Ventas " + subSedes.getSede());
        comprobanteConsolidadoSedeDtoVentas.setIdCuenta(cuenta_ventas);

        //Consignaciones
        ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDtoConsignaciones = new ComprobanteConsolidadoSedeDto();
        comprobanteConsolidadoSedeDtoConsignaciones.setFecha(sfecha);
        comprobanteConsolidadoSedeDtoConsignaciones.setIdSede(subSedes.getIdsedepoint().longValue());
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
            comprobantes.add(comprobanteConsolidadoSedeDtoVentas);
            comprobantes.add(comprobanteConsolidadoSedeDtoConsignaciones);
        }
        //Gastos
        List<ComprobanteConsolidadoSedeDto> gastos = reportesDao.buscarGastosXFecha(connectsAuth.getDataSourceSubSede(subSedes.getSede()), sfecha);
        if (gastos != null) {
            gastos.stream().map((gasto) -> {
                gasto.setIdSede(subSedes.getIdsedepoint().longValue());
                return gasto;
            }).forEachOrdered((gasto) -> {
                gasto.setSede(subSedes.getSede());
            });
        }
        if (gastos != null) {
            comprobantes.addAll(gastos);
        }

        /**
         * Pagos con tarjeta
         */
        DataSource ds = connectsAuth.getDataSourceSubSede(subSedes.getSede());
        ClasePago clasePago = clasePagoDao.findClasePagoById(1, ds);
        if (clasePago.getEstado().equals(EstadosEnum.Activo.getEstado())) {
            Double pagosContarjeta = cierreColombianService.cierrePagosConTarjetas(Formatos.StringDateToDate(sfecha), subSedes.getSede());
            pagosContarjeta = checkNullDoubleTotal(pagosContarjeta);
            comprobantes.add(buildComprobante(subSedes, sfecha, cuenta_pagos_con_tarjeta, "Pagos con Tarjeta " + subSedes.getSede(),
                    pagosContarjeta.longValue()));

        }
        /**
         * Descuento de los pagos
         */
        clasePago = clasePagoDao.findClasePagoById(2, ds);
        if (clasePago.getEstado().equals(EstadosEnum.Activo.getEstado())) {
            Long pagosDescuento = reportesDao.pagosDescuentoTotal(ds, sfecha);
            pagosDescuento = checkNullLongTotal(pagosDescuento);
            comprobantes.add(buildComprobante(subSedes, sfecha, cuenta_descuentos, "Descuentos " + subSedes.getSede(),
                    pagosDescuento));
        }

        TiempoRealSedeDto tiempoRealSede = new TiempoRealSedeDto();
        List<ItemsHashDTO> itemsCierre = cierreColombianService.cierreRealData(Formatos.StringDateToDate(sfecha), subSedes.getSede());

        for (ItemsHashDTO itemsHashDTO : itemsCierre) {
            if (EnumTipoPagoTarjeta.NEQUI.getName().equals(itemsHashDTO.getName())) {
                comprobantes.add(buildComprobante(subSedes, sfecha, cuenta_pagos_nequi, "Pagos Nequi",
                        checkNullDoubleTotal(itemsHashDTO.getValue()).longValue()));
                tiempoRealSede.setPagosNequi(itemsHashDTO.getValue());
            } else if (EnumTipoPagoTarjeta.DAVIPLATA.getName().equals(itemsHashDTO.getName())) {
                comprobantes.add(buildComprobante(subSedes, sfecha, cuenta_pagos_daviplata, "Pagos Daviplata ",
                        checkNullDoubleTotal(itemsHashDTO.getValue()).longValue()));
                tiempoRealSede.setPagosDaviplata(itemsHashDTO.getValue());
            } else if (EnumTipoPagoTarjeta.TRANSFERENCIA.getName().equals(itemsHashDTO.getName())) {
                comprobantes.add(buildComprobante(subSedes, sfecha, cuenta_pagos_transferencias, "Pagos Transferencias ",
                        checkNullDoubleTotal(itemsHashDTO.getValue()).longValue()));
                tiempoRealSede.setPagosTransferencias(itemsHashDTO.getValue());
            }
        }

        /**
         * Propinas y transferencias
         */
        clasePago = clasePagoDao.findClasePagoById(3, ds);
        if (clasePago.getEstado().equals(EstadosEnum.Activo.getEstado())) {

            Double propinas = cierreColombianService.propinasDiario(Formatos.StringDateToDate(sfecha), subSedes.getSede());

            comprobantes.add(1, buildComprobante(subSedes, sfecha, cuenta_propina, "Propinas",
                    propinas.longValue()));

            ComprobanteConsolidadoSedeDto comprobantePT = getComprobanteByCuenta(comprobantes, cuenta_pagos_con_tarjeta);
            if (Objects.nonNull(comprobantePT)) {
                comprobantePT.setTotal(comprobantePT.getTotal() - tiempoRealSede.getPagosNequi().longValue()
                        - tiempoRealSede.getPagosDaviplata().longValue()
                        - tiempoRealSede.getPagosTransferencias().longValue());
            }

        }

        return comprobantes;
    }

    private ComprobanteConsolidadoSedeDto buildComprobante(SubSedes subSedes, String fecha,
            String idCuenta, String concepto, Long total) {

        ComprobanteConsolidadoSedeDto comprobantePagosPropinas = new ComprobanteConsolidadoSedeDto();
        comprobantePagosPropinas.setTotal(total);
        comprobantePagosPropinas.setConcepto(concepto);
        comprobantePagosPropinas.setFecha(fecha);
        comprobantePagosPropinas.setIdCuenta(idCuenta);
        comprobantePagosPropinas.setIdSede(subSedes.getIdsedepoint().longValue());
        comprobantePagosPropinas.setSede(subSedes.getSede());

        return comprobantePagosPropinas;
    }

    private ComprobanteConsolidadoSedeDto getComprobanteByCuenta(List<ComprobanteConsolidadoSedeDto> comprobantes,
            String idCuenta) {

        for (ComprobanteConsolidadoSedeDto comprobante : comprobantes) {
            if (comprobante.getIdCuenta() != null) {
                if (comprobante.getIdCuenta().equals(idCuenta)) {
                    return comprobante;
                }
            }

        }

        return null;
    }

    private Double checkNullDoubleTotal(Double doubleValue) {
        return Objects.isNull(doubleValue) ? 0 : doubleValue;
    }

    private Long checkNullLongTotal(Long longValue) {
        return Objects.isNull(longValue) ? 0 : longValue;
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
    public List<MovimientoCajaDto> movimientoCajaMayorSubsede(String nameDataSource, Date fechaInicial, Date fechaFinal, Integer idSubsede) {

        String sfechaInicial = Formatos.dateTostring(fechaInicial);
        String sfechaFinal = Formatos.dateTostring(fechaFinal);
        List<ComprobanteConsolidadoSedeDto> movs = reportesDao.bucarMovimientoCajaMayorSubsede(connectsAuth.getDataSourceSede(nameDataSource), sfechaInicial, sfechaFinal, idSubsede);

        MovimientoCajaMapper movimientoCajaMayorMapper = new MovimientoCajaMapper();
        List<MovimientoCajaDto> movimientos = movimientoCajaMayorMapper.comprobanteConsolidadoSedeDtoToMovimietoCajaMayorDto(movs);

        return movimientos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovimientoCajaDto> movimientoCajaMenor(String nameDataSource, String fechaInicial, String fechaFinal) {

        List<ComprobanteConsolidadoSedeDto> movs = reportesDao.bucarMovimientoCajaMenor(connectsAuth.getDataSourceSede(nameDataSource), fechaInicial, fechaFinal);

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
        if (Objects.nonNull(porcentajeVentas)) {
            PagosConsolidadoSedeDto pagosConsolidadoSedeDto = pagosMapper.porcentajeVentaTopagosConsolidadoSedeDto(porcentajeVentas);

            List<DetallePorcentajeVentas> detallePorcentajeVentases = reportesDao.buscarDetallePagoConsolidadoMes(ds, mes);
            List<DetallePagosCosolidadoSedeDto> detallePagosCosolidadoSedeDtos = pagosMapper.detallePorcentajeVentaToDetallePagosCosolidadoSedeDto(detallePorcentajeVentases);
            if (detallePagosCosolidadoSedeDtos != null) {
                detallePagosCosolidadoSedeDtos.forEach((detallePagosCosolidadoSedeDto) -> {
                    Integer idSubSedeCred = connectsAuth.getIdSubSedePrincpipal(nameDataSource, detallePagosCosolidadoSedeDto.getIdSede().intValue());
                    SubSedesDto subSede = connectsAuth.findSubsedeXId(idSubSedeCred);
                    detallePagosCosolidadoSedeDto.setNombreSede(subSede.getSede());
                });
            }
            pagosConsolidadoSedeDto.setDetallePagosCosolidadoSedeDtos(detallePagosCosolidadoSedeDtos);
            return pagosConsolidadoSedeDto;
        }

        return null;
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
        Integer ingresos = Integer.parseInt(lectorPropiedades.leerPropiedad(propiedad_ingresos));
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
        return reportesDao.reporteBalance(connectsAuth.getDataSourceSede(nameDataSource), fechInicial, fechaFinal, idsede);
    }

    @Override
    public List<ConsolidadoVentasPorcentajeDTO> reportesVentasTotales(Integer idSede, String fechaInicioD, String fechaFinD) {
        List<SubSedesDto> subSedes = subSedesDao.subsedesXIdSede(idSede);
        return reportesDao.reportePorcentajesVentas(subSedes, fechaInicioD, fechaFinD);
    }

    @Override
    public List<ReporteCuentasDetalleDTO> buscarDetallesCuentas(String nameDataSource,String idCuenta, String fechaInicio, String fechaFin) {
        return reportesDao.buscarDetallesCuentas(connectsAuth.getDataSourceSede(nameDataSource),idCuenta, fechaInicio, fechaFin);
    }
}
