/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.entidades.Consignaciones;
import java.util.Date;
import java.util.List;



/**
 *
 * @author joseefren
 */
public interface CierreService {
    
    public Double cierreDiario(Date fechaCierre);
    public Double cierreVentas(Date  fechaCierre);
    public Double cierreGastos(Date  fechaCierre);
    public Double cierreConsignaciones(Date  fechaCierre);
    public Double cierrCajaFinal(Double ventas,Double gastos,Double cajaInicial, Double consignaciones);
    public Double cierreDescuentos(Date fechaCierre);
    public Double cierrePagosTarjeta(Date fechaCierre);
    public List<Consignaciones> cierreListaConsignaciones(Date  fechaCierre);
    
}
