/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;


import com.administracion.dao.VentasDao;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.mycompany.mapper.VentasMapper;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author joseefren
 */
@Service
public class VentasColombianServiceImpl implements VentasColombianService {

    @Autowired
    private ConnectsAuth connectsAuth;
    @Autowired
    private VentasDao ventasDao;

    @Override
    public List<VentasMapper> ventasMesa(String nameDataSource,Date fi, Date ff) {
        
        return  ventasDao.getVentasMesa(connectsAuth.getDataSourceSubSede(nameDataSource), fi, ff);
       
    }

    /**
     * @return the ventasDomicilio
     */
    @Override
    public List<VentasMapper> ventasDomicilio(String nameDataSource,Date fi, Date ff) {
        return  ventasDao.getVentasDomicilio(connectsAuth.getDataSourceSubSede(nameDataSource), fi, ff);
    }

    /**
     * @param nameDataSource
     * @param fi
     * @param ff
     * @return the ventasMostrador
     */
    @Override
    public List<VentasMapper> ventasMostrador(String nameDataSource,Date fi, Date ff) {
        return  ventasDao.getVentasMostrador(connectsAuth.getDataSourceSubSede(nameDataSource), fi, ff);
    }

    /**
     * @param nameDataSource
     * @param fi
     * @param ff
     * @return the ventas
     */
    @Override
    public List<VentasMapper> totalVentas(String nameDataSource,Date fi, Date ff) {
        return  ventasDao.getTotalVentas(connectsAuth.getDataSourceSubSede(nameDataSource), fi, ff);
    }

}
