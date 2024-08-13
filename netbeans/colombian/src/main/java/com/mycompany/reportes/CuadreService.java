/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.entidades.CierreDiario;
import com.mycompany.mapper.Cuadre;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface CuadreService {
    
 public List<Cuadre> cuadreDia(Date fi, Date ff);
 public Double getValorVentas();
 public Double getValorGastos();
 public Double getValorConsignaciones();
 public Double getValorPagosTarjeta();
 public Double getValorDescuentos();
 public Double getValorPagoNequi();
 public Double getValorPagoDaviplata();
 public Double getValorPagoTransferencia();
 
 
}
