/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.mycompany.dto.OrdenesColombianDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface DomiciliosDiaColombianService {
    
    public List<OrdenesColombianDto> domicilioDia(Date fi, Date ff);
    public Double getTotalDomicilios();
    public Long getTotalRegistros();
}
