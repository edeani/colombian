/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;

import com.administracion.dto.ReporteTotalCuentasXNivelDto;
import com.administracion.entidad.FacturasCompras;
import javax.sql.DataSource;


/**
 *
 * @author Jose Efren
 */

public interface FacturasComprasDao {
    public void guardarFacturaComprasDao(DataSource nameDataSource,FacturasCompras facturasCompras);
    public void actualizarFacturaComprasDao(DataSource nameDataSource,FacturasCompras facturasCompras);
    public ReporteTotalCuentasXNivelDto totalFacturaCompraCuentaXNivel(DataSource nameDataSource,int tipoCuenta,String fechaInicial,String fechaFinal);
    public ReporteTotalCuentasXNivelDto totalFacturaCompraCuentaXNivelSede(DataSource nameDataSource,Long idsede,int tipoCuenta,String fechaInicial,String fechaFinal);
}
