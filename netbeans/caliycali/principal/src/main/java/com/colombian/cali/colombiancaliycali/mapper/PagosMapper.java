/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombian.cali.colombiancaliycali.mapper;

import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosCosolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosTercerosDto;
import com.colombian.cali.colombiancaliycali.dto.PagosConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.PagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.PagosTercerosDto;
import com.colombian.cali.colombiancaliycali.entidades.DetallePagos;
import com.colombian.cali.colombiancaliycali.entidades.DetallePorcentajeVentas;
import com.colombian.cali.colombiancaliycali.entidades.Pagos;
import com.colombian.cali.colombiancaliycali.entidades.PorcentajeVentas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public class PagosMapper {
    public Pagos pagoBeneficiarioDtoToPagoCabecera(PagosTercerosDto pagosTercerosDto){
    
        Pagos pagosTerceros = new Pagos();
        
        if(pagosTercerosDto.getSecuencia()!= null){
            pagosTerceros.setIdpagos(pagosTercerosDto.getSecuencia());
        }
        
        Date fechaPago = Formatos.StringDateToDate(pagosTercerosDto.getFechaPago());
        pagosTerceros.setFecha(fechaPago);
        
        pagosTerceros.setIdbeneficiario(pagosTercerosDto.getIdBeneficiario());
        pagosTerceros.setTotal(pagosTercerosDto.getTotalPago());
        return  pagosTerceros;
    }
    
    public Pagos pagoProveedorDtoToPagoCabecera(PagosProveedorDto pagosProveedorDto){
    
        Pagos pagosProveedor = new Pagos();
        
        if(pagosProveedorDto.getSecuencia()!= null){
            pagosProveedor.setIdpagos(pagosProveedorDto.getSecuencia());
        }
        
        Date fechaPago = Formatos.StringDateToDate(pagosProveedorDto.getFechaPago());
        pagosProveedor.setFecha(fechaPago);
        
        pagosProveedor.setIdbeneficiario(pagosProveedorDto.getIdProveedor());
        pagosProveedor.setTotal(pagosProveedorDto.getTotalPago());
        return  pagosProveedor;
    }
    
    public Pagos pagoConsolidadoSedeDtoToPagoCabecera(PagosConsolidadoSedeDto pagosConsolidadoSedeDto){
        
        Pagos pagos = new Pagos();
        
        pagos.setFecha(Formatos.StringDateToDate(pagosConsolidadoSedeDto.getFechaPago()));
        pagos.setIdbeneficiario(pagosConsolidadoSedeDto.getIdBeneficiario());
        pagos.setIdpagos(pagosConsolidadoSedeDto.getSecuencia());
        pagos.setTotal(pagosConsolidadoSedeDto.getTotal().doubleValue());
        
        return pagos;
    }
    
    public List<DetallePagos> detallePagosCosolidadoSedeDtosToDetallePagos(List<DetallePagosCosolidadoSedeDto> detallePagosCosolidadoSedeDtos){
        List<DetallePagos> detalle = new ArrayList<DetallePagos>();
        
        for (DetallePagosCosolidadoSedeDto detallePagosCosolidadoSedeDto : detallePagosCosolidadoSedeDtos) {
                DetallePagos detallePagos = new DetallePagos();
                detallePagos.setDescripcion(detallePagosCosolidadoSedeDto.getDetalle());
                detallePagos.setFecha(Formatos.StringDateToDate(detallePagosCosolidadoSedeDto.getFecha()));
                detallePagos.setIdSede(detallePagosCosolidadoSedeDto.getIdSede());
                detallePagos.setNumero(detallePagosCosolidadoSedeDto.getNumero());
                detallePagos.setIdcuenta(detallePagosCosolidadoSedeDto.getIdCuenta());
                detallePagos.setTotal(detallePagosCosolidadoSedeDto.getTotal().doubleValue());
                detallePagos.setIdpago(detallePagosCosolidadoSedeDto.getIdPago());
                
                detalle.add(detallePagos);
        }
        
        return detalle;
    }
    
    public List<DetallePagos>  detallePagosTercerosDtoTodetallePagosTerceros(List<DetallePagosTercerosDto> detallePagosTercerosDtos){
        
        List<DetallePagos> detallePagosTerceros = new ArrayList<DetallePagos>();
        
        for (DetallePagosTercerosDto elementoDetallePagosTercerosDto : detallePagosTercerosDtos) {
            
            DetallePagos detallePagosTerceros1 = new DetallePagos();
            detallePagosTerceros1.setConsecutivo(elementoDetallePagosTercerosDto.getConsecutivo());
            detallePagosTerceros1.setDescripcion(elementoDetallePagosTercerosDto.getDetalle());
            detallePagosTerceros1.setFecha(Formatos.StringDateToDate(elementoDetallePagosTercerosDto.getFecha()));
            detallePagosTerceros1.setIdSede(elementoDetallePagosTercerosDto.getIdSede());
            detallePagosTerceros1.setIdcuenta(elementoDetallePagosTercerosDto.getIdCuenta());
            detallePagosTerceros1.setNumero(elementoDetallePagosTercerosDto.getNumero());
            detallePagosTerceros1.setTotal(elementoDetallePagosTercerosDto.getTotal());
            detallePagosTerceros1.setIdpago(elementoDetallePagosTercerosDto.getIdpagotercero());
            
            detallePagosTerceros.add(detallePagosTerceros1);
        }
        
        return detallePagosTerceros;
    }
    
    public List<DetallePagos>  detallePagosProveedorDtoTodetallePagosTerceros(List<DetallePagosProveedorDto> detallePagosProveedorDtos){
        
        List<DetallePagos> detallePagosProveedor = new ArrayList<DetallePagos>();
        
        for (DetallePagosProveedorDto elementoDetallePagosProveedorDto : detallePagosProveedorDtos) {
            
            DetallePagos detallePagosProveedor1 = new DetallePagos();
            detallePagosProveedor1.setConsecutivo(elementoDetallePagosProveedorDto.getConsecutivo());
            detallePagosProveedor1.setDescripcion(elementoDetallePagosProveedorDto.getDetalle());
            detallePagosProveedor1.setFecha(Formatos.StringDateToDate(elementoDetallePagosProveedorDto.getFecha()));
            detallePagosProveedor1.setIdcuenta(elementoDetallePagosProveedorDto.getIdCuenta());
            detallePagosProveedor1.setNumero(elementoDetallePagosProveedorDto.getNumero().intValue());
            detallePagosProveedor1.setTotal(elementoDetallePagosProveedorDto.getTotal());
            detallePagosProveedor1.setIdpago(elementoDetallePagosProveedorDto.getIdpagoproveedor());
            detallePagosProveedor1.setNumeroCompra(elementoDetallePagosProveedorDto.getNumeroCompra());
            detallePagosProveedor1.setFechaVencimiento(Formatos.StringDateToDate(elementoDetallePagosProveedorDto.getFechaVencimiento()));
            
            detallePagosProveedor.add(detallePagosProveedor1);
        }
        
        return detallePagosProveedor;
    }
    
    public PagosConsolidadoSedeDto porcentajeVentaTopagosConsolidadoSedeDto(PorcentajeVentas porcentajeVentas){
        PagosConsolidadoSedeDto pagosConsolidadoSedeDto = new PagosConsolidadoSedeDto();
        pagosConsolidadoSedeDto.setMes(porcentajeVentas.getMes());
        pagosConsolidadoSedeDto.setTotal(porcentajeVentas.getTotal());
        return pagosConsolidadoSedeDto;
    }
    
    public List<DetallePagosCosolidadoSedeDto> detallePorcentajeVentaToDetallePagosCosolidadoSedeDto(List<DetallePorcentajeVentas> detallePorcentajeVentas){
        List<DetallePagosCosolidadoSedeDto> detalles = new ArrayList<DetallePagosCosolidadoSedeDto>();
        
        if(detallePorcentajeVentas!=null){
            for (DetallePorcentajeVentas detallePorcentaje : detallePorcentajeVentas) {
                DetallePagosCosolidadoSedeDto detallePagosCosolidadoSedeDto = detallePorcentajeVentaToDetallePagosCosolidadoSedeDto(detallePorcentaje);
                detalles.add(detallePagosCosolidadoSedeDto);
            }
        }
        return detalles;
    }
    
    public DetallePagosCosolidadoSedeDto detallePorcentajeVentaToDetallePagosCosolidadoSedeDto(DetallePorcentajeVentas detallePorcentajeVentas){
        DetallePagosCosolidadoSedeDto detallePagosCosolidadoSedeDto = new DetallePagosCosolidadoSedeDto();
        detallePagosCosolidadoSedeDto.setIdporcentajeventa(detallePorcentajeVentas.getConsecutivo());
        detallePagosCosolidadoSedeDto.setIdSede(detallePorcentajeVentas.getIdsede());
        detallePagosCosolidadoSedeDto.setConsecutivo(detallePorcentajeVentas.getConsecutivo());
        detallePagosCosolidadoSedeDto.setPorcentaje(detallePorcentajeVentas.getPorcentajeVenta());
        detallePagosCosolidadoSedeDto.setTotal(detallePorcentajeVentas.getTotal());
        
        return detallePagosCosolidadoSedeDto;
    }
}
