/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.SedesDto;
import com.administracion.util.Formatos;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author EderArmando
 */
@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private PorcentajeVentasService porcentajeVentasService;

    @Autowired
    private SedesService sedesService;

    //
    /**
     * Se ejecuta los primeros de cada mes a las 2 AM
     */
    @Override
    //@Scheduled(cron = "0 54 19 * * ?")
    @Scheduled(cron = "0 30 0 1 * *")
    public void jobPorcentajeVentas() {

        //Fecha actual
        Date fechaActual = new Date();
        int mes = Formatos.obtenerMes(fechaActual);

        //Ejecucion del JOB
        List<SedesDto> sedesConn = sedesService.traerSedesDtos();
        sedesConn.forEach((sedesDto) -> {
            try {
                //Pedimos reporte del mes anterior
                porcentajeVentasService.generarPorcentajeVentas(sedesDto.getSede(), mes - 1);
                porcentajeVentasService.generarDetallePorcentajeVentas(sedesDto.getSede(), mes - 1);
            } catch (Exception e) {
                System.out.println("ERROR::jobPorcentajeVentas::"+sedesDto.getSede()+"::" + e.getMessage());
            }
        });
    }

}
