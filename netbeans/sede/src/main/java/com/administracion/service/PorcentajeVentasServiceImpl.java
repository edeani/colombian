/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;


import com.administracion.dao.DetallePorcentajeVentasDao;
import com.administracion.dao.PorcentajeVentasDao;
import com.administracion.dao.ReportesDao;
import com.administracion.dao.SecuenciasMysqlDao;
import com.administracion.entidad.DetallePorcentajeVentas;
import com.administracion.entidad.PorcentajeVentas;
import com.administracion.service.autorizacion.ConnectsAuth;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EderArmando
 */
@Service
public class PorcentajeVentasServiceImpl implements PorcentajeVentasService {

    @Autowired
    private DetallePorcentajeVentasDao detallePorcentajeVentasDao;
    @Autowired
    private PorcentajeVentasDao porcentajeVentasDao;
    @Autowired
    private ReportesDao reportesDao;
    @Autowired
    private SecuenciasMysqlDao secuenciasMysqlDao;
    @Autowired
    private ConnectsAuth connectsAuth;

    @Override
    @Transactional
    public void generarDetallePorcentajeVentas(String nameDataSource, Integer mes) {
        DataSource ds = connectsAuth.getDataSourceSedeConnect(nameDataSource);
        detallePorcentajeVentasDao.borrarDetallePorcentajeVentasAll(ds);
        List<DetallePorcentajeVentas> detallePorcentajesVentas = detallePorcentajeVentasDao.generarDetallePorcentajeVentas(ds, mes);
        PorcentajeVentas porcentajeVentas = reportesDao.buscarPagoConsolidadoMes(ds, mes);

        if (detallePorcentajesVentas != null) {
            Double total = porcentajeVentas.getTotal();
            detallePorcentajesVentas.stream().map((elementoDPV) -> {
                elementoDPV.setIdporcentajeventa(porcentajeVentas.getConsecutivo());
                return elementoDPV;
            }).map((DetallePorcentajeVentas elementoDPV) -> {
                elementoDPV.setPorcentajeVenta(elementoDPV.getTotal() / total);
                return elementoDPV;
            }).forEachOrdered((DetallePorcentajeVentas elementoDPV) -> {
                detallePorcentajeVentasDao.insertarDetallePorcentajeVenta(ds, elementoDPV);
            });
        }
    }

    @Override
    @Transactional
    public void generarPorcentajeVentas(String nameDataSource, Integer mes) {
        DataSource ds = connectsAuth.getDataSourceSedeConnect(nameDataSource);
        porcentajeVentasDao.borrarPorcentajeVentas(ds, mes);
        Long consecutivo = secuenciasMysqlDao.secuenceTable(ds, "porcentaje_ventas");
        
        PorcentajeVentas porcentajeVentas = new PorcentajeVentas();
        porcentajeVentas.setTotal(porcentajeVentasDao.PorcentajeVentasDaoImpl(ds, mes));
        porcentajeVentas.setConsecutivo(consecutivo);
        porcentajeVentas.setMes(mes);
        porcentajeVentas.setFecha(new Date());
        
        porcentajeVentasDao.insertarPorcetajeVentas(ds, porcentajeVentas);
        
    }

}
