/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.mycompany.mapper.VentasMapper;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface VentasColombianService {
    public List<VentasMapper> ventasMesa(String nameDataSource,Date fi,Date ff);
    public List<VentasMapper> ventasDomicilio(String nameDataSource,Date fi,Date ff);
    public List<VentasMapper> ventasMostrador(String nameDataSource,Date fi,Date ff);
    public List<VentasMapper> totalVentas(String nameDataSource,Date fi,Date ff);
}
