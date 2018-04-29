/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.mycompany.mapper.GastosMapper;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface GastosColombianService {
    
    public void gastos(Date fi,Date ff,String subsede);
    public List<GastosMapper> getGastosNivel1();
    public List<GastosMapper> getGastosNivel2();
    public List<GastosMapper> getGastosNivel3();
    public Double getTotal();
}
