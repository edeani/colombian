/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.entidades.Consignaciones;
import java.util.Date;
import java.util.HashMap;
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
    public Double cierrCajaFinal(Double ventas,Double gastos,Double cajaInicial, Double consignaciones,
            Double pagosTarjeta, Double descuentos, Double nequi, Double Daviplata,Double transacciones);
    public Double cierreDescuentos(Date fechaCierre);
    public HashMap<String,Double> cierrePagosTarjeta(Date fechaCierre);
    public List<Consignaciones> cierreListaConsignaciones(Date  fechaCierre);
    
}
