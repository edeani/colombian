/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.mycompany.dto.ReporteComprasSedeDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface ComprasColombianService {
    
    public List<ReporteComprasSedeDto> listadoCompras(Date Finicial, Date Ffinal);
}
