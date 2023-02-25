/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.mycompany.mapper.Cuadre;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface CuadreColombianService {
    
 public List<Cuadre> cuadreDia(Date fi, Date ff);
 public Double getValorVentas();
 public Double getValorGastos();
 public Double getValorConsignaciones();
}
