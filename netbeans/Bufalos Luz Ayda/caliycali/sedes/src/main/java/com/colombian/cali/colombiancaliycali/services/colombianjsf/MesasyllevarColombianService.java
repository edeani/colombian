/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.mycompany.mapper.Mesasyllevar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface MesasyllevarColombianService {
    
    public List<Mesasyllevar> mesas(Date fi, Date ff);
    public Double getTotalvalor();
}
