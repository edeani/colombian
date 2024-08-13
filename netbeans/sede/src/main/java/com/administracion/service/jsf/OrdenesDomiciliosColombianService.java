/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.mycompany.dto.OrdenesDomiciliosDto;
import java.util.List;




/**
 *
 * @author joseefren
 */
public interface OrdenesDomiciliosColombianService {
    
        public List<OrdenesDomiciliosDto> domiciliosordenes(String fi, String ff,String subsede);
        public Double getTotalvalor();
    
}
