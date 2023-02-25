/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.mycompany.mapper.OrdenesDomiciliosMapper;
import java.util.Date;
import java.util.List;




/**
 *
 * @author joseefren
 */
public interface OrdenesDomiciliosColombianService {
    
        public List<OrdenesDomiciliosMapper> domiciliosordenes(Date fi, Date ff);
        public Double getTotalvalor();
    
}
