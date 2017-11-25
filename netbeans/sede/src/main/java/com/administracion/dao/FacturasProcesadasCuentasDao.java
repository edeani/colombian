/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.entidad.FacturasProcesadasCuentas;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Jose Efren
 */
public interface FacturasProcesadasCuentasDao {
    public void guardarFacturaProcesada(DataSource nameDataSource,FacturasProcesadasCuentas facturasProcesadasCuentas);
    public List<FacturasProcesadasCuentas> buscarFacturaProcesada(DataSource nameDataSource,Long idFactura);
    public FacturasProcesadasCuentas buscarFacturaProcesadaSedes(DataSource nameDataSource,Long idFactura);
}
