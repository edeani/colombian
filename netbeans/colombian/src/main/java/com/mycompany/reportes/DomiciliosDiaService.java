/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.entidades.Orden;
import com.mycompany.mapper.Ordenes;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface DomiciliosDiaService {
    
    public List<Ordenes> domicilioDia(Date fi, Date ff);
    public Double getTotalDomicilios();
    public Long getTotalRegistros();
    public void anularDomicilio(Long idDomicilio);
}
