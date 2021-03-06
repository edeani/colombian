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
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public void jobPorcentajeVentas(Integer mes) {
        Integer mes_=0;
        if (mes == null) {
            //Fecha actual
            //Pedimos reporte del mes anterior
            Date fechaActual = new Date();
            mes_ = Formatos.obtenerMes(fechaActual);
            mes_ = mes_ - 1 < 0 ? 11 : mes_ - 1;
        }else{
            mes_=mes;
        }
        final Integer mesFinal = mes_;
        //Ejecucion del JOB
        List<SedesDto> sedesConn = sedesService.traerSedesDtos();
        sedesConn.forEach((SedesDto sedesDto) -> {
            try {      
                porcentajeVentasService.generarPorcentajeVentas(sedesDto.getSede(), mesFinal);
            } catch (Exception e) {
                System.out.println("ERROR::jobPorcentajeVentas::" + sedesDto.getSede() + "::" + e.getMessage());
            }
        });
    }

    @Override
    public void jobPorcentajeVentasXSedeXMes(String nameSede, Integer mes) {
        try {
            //Fecha actual
            Date fechaActual = new Date();
            if (mes == null) {
                //Pedimos reporte del mes anterior
                mes = Formatos.obtenerMes(fechaActual);
                mes = mes - 1 < 0 ? 11 : mes - 1;
            }

            porcentajeVentasService.generarPorcentajeVentas(nameSede, mes);
        } catch (Exception e) {
            System.out.println("ERROR::jobPorcentajeVentas::" + nameSede + "::" + e.getMessage());
        }
    }

}
