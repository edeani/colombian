/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.administracion.dto.GastosDto;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface GastosColombianService {
    
    public  List<GastosDto> gastos(String fi,String ff,String subsede);
}
