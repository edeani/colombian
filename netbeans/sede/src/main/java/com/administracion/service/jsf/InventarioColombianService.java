/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.mycompany.mapper.Inventario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public  interface InventarioColombianService {
    
    public List<Inventario> traerInventario(Date Ffinal,Date Finicial);
}
