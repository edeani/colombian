/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.dto.ReporteTotalCuentasXNivelDto;
import com.colombian.cali.colombiancaliycali.entidades.FacturasCompras;

/**
 *
 * @author Jose Efren
 */

public interface FacturasComprasDao {
    public void guardarFacturaComprasDao(String nameDataSource,FacturasCompras facturasCompras);
    public void actualizarFacturaComprasDao(String nameDataSource,FacturasCompras facturasCompras);
    public ReporteTotalCuentasXNivelDto totalFacturaCompraCuentaXNivel(String nameDataSource,int tipoCuenta,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalFacturaCompraCuentaXNivelSede(String nameDataSource,Long idsede,int tipoCuenta,String fechaInicial,String fechaFinal);
}
