/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dto;

import java.util.List;

/**
 *
 * @author EderArmando
 */
public class TiempoRealDto {
    private Double cajaInicial;
    private Double ventas;
    private Double gastos;
    private Double consignaciones;
    private Double cajaFinal;
    private Double pagosTarjetas;
    private Double descuentos;
    private Double pagosNequi;
    private Double pagosDaviplata;
    private Double pagosTransferencias;
    private Double propinas;
    
    private List<Consignaciones> listaConsignaciones;

    public Double getCajaInicial() {
        return cajaInicial;
    }

    public void setCajaInicial(Double cajaInicial) {
        this.cajaInicial = cajaInicial;
    }

    public Double getVentas() {
        return ventas;
    }

    public void setVentas(Double ventas) {
        this.ventas = ventas;
    }

    public Double getGastos() {
        return gastos;
    }

    public void setGastos(Double gastos) {
        this.gastos = gastos;
    }

    public Double getConsignaciones() {
        return consignaciones;
    }

    public void setConsignaciones(Double consignaciones) {
        this.consignaciones = consignaciones;
    }

    public Double getCajaFinal() {
        return cajaFinal;
    }

    public void setCajaFinal(Double cajaFinal) {
        this.cajaFinal = cajaFinal;
    }

    public List<Consignaciones> getListaConsignaciones() {
        return listaConsignaciones;
    }

    public void setListaConsignaciones(List<Consignaciones> listaConsignaciones) {
        this.listaConsignaciones = listaConsignaciones;
    }

    public Double getPagosTarjetas() {
        return pagosTarjetas;
    }

    public void setPagosTarjetas(Double pagosTarjetas) {
        this.pagosTarjetas = pagosTarjetas;
    }

    public Double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(Double descuentos) {
        this.descuentos = descuentos;
    }

    public Double getPagosNequi() {
        return pagosNequi;
    }

    public void setPagosNequi(Double pagosNequi) {
        this.pagosNequi = pagosNequi;
    }

    public Double getPagosDaviplata() {
        return pagosDaviplata;
    }

    public void setPagosDaviplata(Double pagosDaviplata) {
        this.pagosDaviplata = pagosDaviplata;
    }

    public Double getPagosTransferencias() {
        return pagosTransferencias;
    }

    public void setPagosTransferencias(Double pagosTransferencias) {
        this.pagosTransferencias = pagosTransferencias;
    }

    public Double getPropinas() {
        return propinas;
    }

    public void setPropinas(Double propinas) {
        this.propinas = propinas;
    }

    
}
