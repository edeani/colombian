/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.jobs;

import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombia.cali.colombiancaliycali.util.LectorPropiedades;
import com.colombian.cali.colombiancaliycali.services.PorcentajeVentasService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 *
 * @author EderArmando
 */
@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private PorcentajeVentasService porcentajeVentasService;
    
    private static final String archivo="/bd/basedatos.properties";
    private static final String PROPIEDAD_BD="basedatos";
   
    
    //
    /**
     * Se ejecuta los primeros de cada mes a las 2 AM
     */
    @Override
    //@Scheduled(cron = "0 54 19 * * ?")
    @Scheduled(cron = "0 0 2 1 * *")
    public void jobPorcentajeVentas() {
        //Archivo con credenciales de la bd principal
        LectorPropiedades lectorPropiedades = new LectorPropiedades();
        lectorPropiedades.setArchivo(archivo);
        
        //Fecha actual
        Date fechaActual = new Date();
        int mes = Formatos.obtenerMes(fechaActual);
        
        //Ejecucion del JOB
        try {
            String base_datos = lectorPropiedades.leerPropiedad(PROPIEDAD_BD);
            //Pedimos reporte del mes anterior
            System.out.println("BASEDATOS jobPorcentajeVentas::"+base_datos);
            porcentajeVentasService.generarPorcentajeVentas(base_datos, mes-1);
            porcentajeVentasService.generarDetallePorcentajeVentas(base_datos, mes-1);
        } catch (Exception e) {
            System.out.println("ERROR::jobPorcentajeVentas"+e.getMessage());
        }
    }
    
}
