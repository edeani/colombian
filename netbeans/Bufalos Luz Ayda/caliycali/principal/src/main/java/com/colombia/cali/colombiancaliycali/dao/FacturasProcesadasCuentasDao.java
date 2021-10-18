/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.entidades.FacturasProcesadasCuentas;
import java.util.List;

/**
 *
 * @author Jose Efren
 */
public interface FacturasProcesadasCuentasDao {
    public void guardarFacturaProcesada(String nameDataSource,FacturasProcesadasCuentas facturasProcesadasCuentas);
    public List<FacturasProcesadasCuentas> buscarFacturaProcesada(String nameDataSource,Long idFactura);
    public FacturasProcesadasCuentas buscarFacturaProcesadaSedes(String nameDataSource,Long idFactura);
}
