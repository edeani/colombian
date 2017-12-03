/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.mycompany.dto.Consignaciones;
import java.util.Date;
import java.util.List;



/**
 *
 * @author joseefren
 */
public interface CierreColombianService {
    
    public Double cierreDiario(Date fechaCierre);
    public Double cierreVentas(Date  fechaCierre);
    public Double cierreGastos(Date  fechaCierre);
    public Double cierreConsignaciones(Date  fechaCierre);
    public Double cierrePagosConTarjetas(Date fechaCierre);
    public Double cierreDescuentos(Date fechaCierre);
    public List<Consignaciones> cierreListaConsignaciones(Date  fechaCierre);
    
}
