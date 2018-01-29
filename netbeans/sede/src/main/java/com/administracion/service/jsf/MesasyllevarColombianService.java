/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.mycompany.mapper.Mesasyllevar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface MesasyllevarColombianService {
    
    public List<Mesasyllevar> mesas(Date fi, Date ff,String subsede);
    public Double getTotalvalor();
}
