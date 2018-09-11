/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.mycompany.mapper.VentasMapper;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author EderArmando
 */
public interface VentasDao {
    public List<VentasMapper> getVentasMesa(DataSource dataSource,Date fi, Date ff);
    public List<VentasMapper> getVentasDomicilio(DataSource dataSource,Date fi, Date ff);
    public List<VentasMapper> getVentasMostrador(DataSource dataSource,Date fi, Date ff);
    public List<VentasMapper> getTotalVentas(DataSource dataSource,Date fi, Date ff);
}
