/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

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
    public List<Consignaciones> cierreListaConsignaciones(Date  fechaCierre);
    
}
