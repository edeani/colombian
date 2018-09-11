/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.mycompany.mapper.Cuadre;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface CuadreColombianService {
    
 public List<Cuadre> cuadreDia(Date fi, Date ff,String subsede);
 public Double getValorVentas();
 public Double getValorGastos();
 public Double getValorConsignaciones();
}
