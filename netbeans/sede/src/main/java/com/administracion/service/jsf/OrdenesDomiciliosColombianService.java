/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.mycompany.mapper.OrdenesDomiciliosMapper;
import java.util.Date;
import java.util.List;




/**
 *
 * @author joseefren
 */
public interface OrdenesDomiciliosColombianService {
    
        public List<OrdenesDomiciliosMapper> domiciliosordenes(Date fi, Date ff,String subsede);
        public Double getTotalvalor();
    
}
