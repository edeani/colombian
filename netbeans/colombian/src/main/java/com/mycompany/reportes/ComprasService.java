/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.mapper.Compras;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface ComprasService {
    
    public List<Compras> listadoCompras(Date Finicial, Date Ffinal);
    public Double getTotalCompras();
}
