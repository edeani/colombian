/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.administracion.dto.ItemsHashDTO;
import com.administracion.dto.TiempoRealSedeDto;
import com.mycompany.dto.Consignaciones;
import java.util.Date;
import java.util.List;



/**
 *
 * @author joseefren
 */
public interface CierreColombianService {
    
    public Double cierreDiario(Date fechaCierre,String subsede);
    public TiempoRealSedeDto TiempoRealData(Date fechaCierre,String subsede);
    public Double cierreVentas(Date  fechaCierre,String subsede);
    public Double cierreGastos(Date  fechaCierre,String subsede);
    public Double cierreConsignaciones(Date  fechaCierre,String subsede);
    public Double cierrePagosConTarjetas(Date fechaCierre,String subsede);
    public Double cierreDescuentos(Date fechaCierre,String subsede);
    public List<Consignaciones> cierreListaConsignaciones(Date  fechaCierre,String subsede);
    public Double propinasDiario(Date fechaCierre,String subsede);
    public Double cierreDiarioPagosConTarjetas(Date fechaCierre,String subsede);
    public  List<ItemsHashDTO> cierreRealData(Date fechaCierre,String subsede);
    
}
