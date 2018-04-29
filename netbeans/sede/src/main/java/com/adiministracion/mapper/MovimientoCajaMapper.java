/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;


import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.MovimientoCajaDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public class MovimientoCajaMapper {

    private static final String cuenta_deber_caja_mayor = "11050501";
    private static final String cuenta_deber_caja_menor = "11051001";
    private static final String cuenta_deber_pagos_t = "11201010";
    public MovimientoCajaDto comprobanteConsolidadoSedeDtoToMovimietoCajaMayorDto(ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDto) {
        MovimientoCajaDto movimientoCajaMayorDto = new MovimientoCajaDto();
        movimientoCajaMayorDto.setConcepto(comprobanteConsolidadoSedeDto.getConcepto());
        if (comprobanteConsolidadoSedeDto.getIdCuenta().equals(cuenta_deber_caja_mayor)) {
            movimientoCajaMayorDto.setHaber(comprobanteConsolidadoSedeDto.getTotal().doubleValue());
        } else {
            movimientoCajaMayorDto.setDeber(comprobanteConsolidadoSedeDto.getTotal().doubleValue());
        }
        movimientoCajaMayorDto.setFecha(comprobanteConsolidadoSedeDto.getFecha());
        return movimientoCajaMayorDto;
    }

    public MovimientoCajaDto comprobanteConsolidadoSedeDtoToMovimietoCajaMenorDto(ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDto) {
        MovimientoCajaDto movimientoCajaMayorDto = new MovimientoCajaDto();
        movimientoCajaMayorDto.setConcepto(comprobanteConsolidadoSedeDto.getConcepto());
        if (comprobanteConsolidadoSedeDto.getIdCuenta().equals(cuenta_deber_caja_menor)
               || comprobanteConsolidadoSedeDto.getIdCuenta().equals(cuenta_deber_pagos_t)) {
            movimientoCajaMayorDto.setHaber(comprobanteConsolidadoSedeDto.getTotal().doubleValue());
        } else {
            movimientoCajaMayorDto.setDeber(comprobanteConsolidadoSedeDto.getTotal().doubleValue());
        }
        movimientoCajaMayorDto.setFecha(comprobanteConsolidadoSedeDto.getFecha());
        return movimientoCajaMayorDto;
    }
    
    public List<MovimientoCajaDto> comprobanteConsolidadoSedeDtoToMovimietoCajaMayorDto(List<ComprobanteConsolidadoSedeDto> comprobanteConsolidadoSedeDto) {
        List<MovimientoCajaDto> movimientos = new ArrayList<>();
        Double saldoAcumulado = 0D;
        if (comprobanteConsolidadoSedeDto != null) {
            for (ComprobanteConsolidadoSedeDto comprobante : comprobanteConsolidadoSedeDto) {
                MovimientoCajaDto movimientoCajaMayorDto = comprobanteConsolidadoSedeDtoToMovimietoCajaMayorDto(comprobante);
                movimientoCajaMayorDto.setIdcomprobante(comprobante.getIdComprobante());
                if (movimientoCajaMayorDto.getDeber() == null) {
                    movimientoCajaMayorDto.setDeber(0D);
                }
                if (movimientoCajaMayorDto.getHaber() == null) {
                    movimientoCajaMayorDto.setHaber(0D);
                }
                saldoAcumulado = saldoAcumulado + movimientoCajaMayorDto.getHaber() - movimientoCajaMayorDto.getDeber();
                movimientoCajaMayorDto.setSaldo(saldoAcumulado);
                movimientos.add(movimientoCajaMayorDto);

            }
        }
        return movimientos;
    }
    
    public List<MovimientoCajaDto> comprobanteConsolidadoSedeDtoToMovimietoCajaMenorDto(List<ComprobanteConsolidadoSedeDto> comprobanteConsolidadoSedeDto) {
        List<MovimientoCajaDto> movimientos = new ArrayList<>();
        Double saldoAcumulado = 0D;
        if (comprobanteConsolidadoSedeDto != null) {
            for (ComprobanteConsolidadoSedeDto comprobante : comprobanteConsolidadoSedeDto) {
                MovimientoCajaDto movimientoCajaMenorDto = comprobanteConsolidadoSedeDtoToMovimietoCajaMenorDto(comprobante);
                movimientoCajaMenorDto.setIdcomprobante(comprobante.getIdComprobante());
                if (movimientoCajaMenorDto.getDeber() == null) {
                    movimientoCajaMenorDto.setDeber(0D);
                }
                if (movimientoCajaMenorDto.getHaber() == null) {
                    movimientoCajaMenorDto.setHaber(0D);
                }
                saldoAcumulado = saldoAcumulado + movimientoCajaMenorDto.getHaber() - movimientoCajaMenorDto.getDeber();
                movimientoCajaMenorDto.setSaldo(saldoAcumulado);
                movimientos.add(movimientoCajaMenorDto);

            }
        }
        return movimientos;
    }
}
