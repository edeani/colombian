/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.DetallePorcentajeVentasDao;
import com.colombia.cali.colombiancaliycali.dao.PorcentajeVentasDao;
import com.colombia.cali.colombiancaliycali.dao.ReportesDao;
import com.colombia.cali.colombiancaliycali.dao.SecuenciasMysqlDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.entidades.DetallePorcentajeVentas;
import com.colombian.cali.colombiancaliycali.entidades.PorcentajeVentas;
import java.text.Normalizer;
import java.util.Date;
import java.util.List;
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

    @Override
    @Transactional
    public void generarDetallePorcentajeVentas(String nameDataSource, Integer mes) {
        detallePorcentajeVentasDao.borrarDetallePorcentajeVentasAll(nameDataSource);
        List<DetallePorcentajeVentas> detallePorcentajesVentas = detallePorcentajeVentasDao.generarDetallePorcentajeVentas(nameDataSource, mes);
        PorcentajeVentas porcentajeVentas = reportesDao.buscarPagoConsolidadoMes(nameDataSource, mes);

        if (detallePorcentajesVentas != null) {
            Double total = porcentajeVentas.getTotal();
            for (DetallePorcentajeVentas elementoDPV : detallePorcentajesVentas) {
                elementoDPV.setIdporcentajeventa(porcentajeVentas.getConsecutivo());
                elementoDPV.setPorcentajeVenta(elementoDPV.getTotal() / total);
                detallePorcentajeVentasDao.insertarDetallePorcentajeVenta(nameDataSource, elementoDPV);
            }
        }
    }

    @Override
    @Transactional
    public void generarPorcentajeVentas(String nameDataSource, Integer mes) {
        porcentajeVentasDao.borrarPorcentajeVentas(nameDataSource, mes);
        Long consecutivo = secuenciasMysqlDao.secuenceTable(nameDataSource, "porcentaje_ventas");
        
        PorcentajeVentas porcentajeVentas = new PorcentajeVentas();
        porcentajeVentas.setTotal(porcentajeVentasDao.PorcentajeVentasDaoImpl(nameDataSource, mes));
        porcentajeVentas.setConsecutivo(consecutivo);
        porcentajeVentas.setMes(mes);
        porcentajeVentas.setFecha(new Date());
        
        porcentajeVentasDao.insertarPorcetajeVentas(nameDataSource, porcentajeVentas);
        
    }

}
