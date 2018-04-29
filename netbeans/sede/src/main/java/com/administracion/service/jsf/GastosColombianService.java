/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.administracion.dto.ReporteGastosDto;
import com.mycompany.mapper.GastosMapper;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface GastosColombianService {
    
    public  List<ReporteGastosDto> gastos(String fi,String ff,String subsede);
}
