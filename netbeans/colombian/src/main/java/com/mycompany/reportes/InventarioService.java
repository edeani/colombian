/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.mapper.Inventario;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public  interface InventarioService {
    
    public List<Inventario> traerInventario(Date Ffinal,Date Finicial);
}
