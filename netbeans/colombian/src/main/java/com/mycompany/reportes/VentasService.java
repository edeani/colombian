/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.mapper.VentasMapper;
import java.util.Date;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface VentasService {
    public void ventas(Date fi,Date ff);
    public Double getTotalVenta();
    public Double getTotalDomicilios();
    public Double getTotalMesa();
    public Double getTotalMostrador();
    public List<VentasMapper> getVentasMesa();
    public List<VentasMapper> getVentasDomicilio();
    public List<VentasMapper> getVentasMostrador();
    public List<VentasMapper> getVentas();
}
