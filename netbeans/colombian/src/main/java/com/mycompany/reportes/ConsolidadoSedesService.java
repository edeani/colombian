/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.mapper.ConsolidadoMapper;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface ConsolidadoSedesService {
    
    public List<ConsolidadoMapper> consolidadoSede(Date fi,Date ff);
    public String getTotalVentas();
    public String getTotalUnidades();
    public String getTotalConsignacion();
}
