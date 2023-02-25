/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.mycompany.mapper.Inventario;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author joseefren
 */
public  interface InventarioColombianService {
    
    public List<Inventario> traerInventario(Date Ffinal,Date Finicial);
}
