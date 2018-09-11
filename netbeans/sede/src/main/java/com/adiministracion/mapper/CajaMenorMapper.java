/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.adiministracion.mapper;


import com.administracion.dto.DetallePagosCosolidadoSedeDto;
import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.dto.PagosConsolidadoSedeDto;
import com.administracion.dto.PagosProveedorDto;
import com.administracion.dto.PagosTercerosDto;
import com.administracion.entidad.CajaMenor;
import com.administracion.entidad.DetalleCajaMenor;
import com.administracion.util.Formatos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public class CajaMenorMapper {
    public CajaMenor pagosTercerosDtoToPagoCabecera(PagosTercerosDto pagosTercerosDto){
    
        CajaMenor pagosTerceros = new CajaMenor();
        
        if(pagosTercerosDto.getSecuencia()!= null){
            pagosTerceros.setIdcajamenor(pagosTercerosDto.getSecuencia());
        }
        
        Date fechaPago = Formatos.StringDateToDate(pagosTercerosDto.getFechaPago());
        pagosTerceros.setFecha(fechaPago);
        
        pagosTerceros.setIdbeneficiario(pagosTercerosDto.getIdBeneficiario());
        Double totalPagoTercerDto = Double.parseDouble(""+pagosTercerosDto.getTotalPago());
        pagosTerceros.setTotal(totalPagoTercerDto);
        return  pagosTerceros;
    }
    
    public List<DetalleCajaMenor>  detallePagosTercerosDtoToDetalleCajaMenor(List<DetallePagosTercerosDto> detallePagosTercerosDtos){
        
        List<DetalleCajaMenor> detallePagosTerceros = new ArrayList<>();
        
        detallePagosTercerosDtos.stream().map((elementoDetallePagosTercerosDto) -> {
            DetalleCajaMenor detallePagosTerceros1 = new DetalleCajaMenor();
            detallePagosTerceros1.setConsecutivo(elementoDetallePagosTercerosDto.getConsecutivo());
            detallePagosTerceros1.setDescripcion(elementoDetallePagosTercerosDto.getDetalle());
            detallePagosTerceros1.setFecha(Formatos.StringDateToDate(elementoDetallePagosTercerosDto.getFecha()));
            detallePagosTerceros1.setIdsede(elementoDetallePagosTercerosDto.getIdSede());
            detallePagosTerceros1.setIdcuenta(elementoDetallePagosTercerosDto.getIdCuenta());
            detallePagosTerceros1.setNumero(elementoDetallePagosTercerosDto.getNumero());
            detallePagosTerceros1.setTotal(elementoDetallePagosTercerosDto.getTotal());
            detallePagosTerceros1.setIdcajamenor(elementoDetallePagosTercerosDto.getIdpagotercero());
            return detallePagosTerceros1;            
        }).forEachOrdered((detallePagosTerceros1) -> {
            detallePagosTerceros.add(detallePagosTerceros1);
        });
        
        return detallePagosTerceros;
    }
    
    public CajaMenor pagoProveedorDtoToCajaMenorCabecera(PagosProveedorDto pagosProveedorDto){
    
        CajaMenor pagosCajaMenor = new CajaMenor();
        
        if(pagosProveedorDto.getSecuencia()!= null){
            pagosCajaMenor.setIdcajamenor(pagosProveedorDto.getSecuencia());
        }
        
        Date fechaPago = Formatos.StringDateToDate(pagosProveedorDto.getFechaPago());
        pagosCajaMenor.setFecha(fechaPago);
        
        pagosCajaMenor.setIdbeneficiario(pagosProveedorDto.getIdProveedor());
        pagosCajaMenor.setTotal(pagosProveedorDto.getTotalPago());
        return  pagosCajaMenor;
    }
    
    public List<DetalleCajaMenor>  detallePagosProveedorDtoTodetallePagosCajaMenor(List<DetallePagosProveedorDto> detallePagosProveedorDtos){
        
        List<DetalleCajaMenor> detallePagosCajaMenor = new ArrayList<>();
        
        detallePagosProveedorDtos.stream().map((elementoDetallePagosProveedorDto) -> {
            DetalleCajaMenor detallePagosProveedor1 = new DetalleCajaMenor();
            detallePagosProveedor1.setConsecutivo(elementoDetallePagosProveedorDto.getConsecutivo());
            detallePagosProveedor1.setDescripcion(elementoDetallePagosProveedorDto.getDetalle());
            detallePagosProveedor1.setFecha(Formatos.StringDateToDate(elementoDetallePagosProveedorDto.getFecha()));
            detallePagosProveedor1.setIdcuenta(elementoDetallePagosProveedorDto.getIdCuenta());
            detallePagosProveedor1.setNumero(elementoDetallePagosProveedorDto.getNumero().intValue());
            detallePagosProveedor1.setTotal(elementoDetallePagosProveedorDto.getTotal());
            detallePagosProveedor1.setIdcajamenor(elementoDetallePagosProveedorDto.getIdpagoproveedor());
            detallePagosProveedor1.setNumeroCompra(elementoDetallePagosProveedorDto.getNumeroCompra());
            detallePagosProveedor1.setFechaVencimiento(Formatos.StringDateToDate(elementoDetallePagosProveedorDto.getFechaVencimiento()));
            return detallePagosProveedor1;            
        }).forEachOrdered((detallePagosProveedor1) -> {
            detallePagosCajaMenor.add(detallePagosProveedor1);
        });
        
        return detallePagosCajaMenor;
    }
    
    
    public CajaMenor pagoConsolidadoSedeDtoToCajaMenorCabecera(PagosConsolidadoSedeDto pagosConsolidadoSedeDto){
        
        CajaMenor cajaMenor = new CajaMenor();
        
        cajaMenor.setFecha(Formatos.StringDateToDate(pagosConsolidadoSedeDto.getFechaPago()));
        cajaMenor.setIdbeneficiario(pagosConsolidadoSedeDto.getIdBeneficiario());
        cajaMenor.setIdcajamenor(pagosConsolidadoSedeDto.getSecuencia());
        cajaMenor.setTotal(pagosConsolidadoSedeDto.getTotal());
        
        return cajaMenor;
    }
    
    public List<DetalleCajaMenor> detallePagosCosolidadoSedeDtosToDetalleCajaMenor(List<DetallePagosCosolidadoSedeDto> detallePagosCosolidadoSedeDtos){
        List<DetalleCajaMenor> detalle = new ArrayList<>();
        
        detallePagosCosolidadoSedeDtos.stream().map((detallePagosCosolidadoSedeDto) -> {
            DetalleCajaMenor detalleCajaMenor = new DetalleCajaMenor();
            detalleCajaMenor.setDescripcion(detallePagosCosolidadoSedeDto.getDetalle());
            detalleCajaMenor.setFecha(Formatos.StringDateToDate(detallePagosCosolidadoSedeDto.getFecha()));
            detalleCajaMenor.setIdsede(detallePagosCosolidadoSedeDto.getIdSede());
            detalleCajaMenor.setNumero(detallePagosCosolidadoSedeDto.getNumero());
            detalleCajaMenor.setIdcuenta(detallePagosCosolidadoSedeDto.getIdCuenta());
            detalleCajaMenor.setTotal(detallePagosCosolidadoSedeDto.getTotal());
            detalleCajaMenor.setIdcajamenor(detallePagosCosolidadoSedeDto.getIdPago());
            return detalleCajaMenor;
        }).forEachOrdered((detalleCajaMenor) -> {
            detalle.add(detalleCajaMenor);
        });
        
        return detalle;
    }
}
