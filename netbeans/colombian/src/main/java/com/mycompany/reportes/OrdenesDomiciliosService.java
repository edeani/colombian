/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.mapper.OrdenesDomiciliosMapper;
import java.util.Date;
import java.util.List;




/**
 *
 * @author joseefren
 */
public interface OrdenesDomiciliosService {
    
        public List<OrdenesDomiciliosMapper> domiciliosordenes(Date fi, Date ff);
        public Double getTotalvalor();
    
}
