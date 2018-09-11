/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.service.JobService;
import com.administracion.service.PorcentajeVentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author EderArmando
 */
@Controller
@RequestMapping("/jobs")
public class JobsController {
   
    @Autowired
    private JobService jobService;
    
    @RequestMapping("/{sede:[a-zA-Z]+}/{job}.htm")
    public @ResponseBody String ejecutarJobSede(@PathVariable String sede,@PathVariable String job,
            @RequestParam(required = false) Integer mes){
        try {
            if(job.equals("porcentje-ventas")){
                jobService.jobPorcentajeVentasXSedeXMes(sede,mes);
            }
        } catch (Exception e) {
            return "Error";
        }
        
        return "OK";
    }
    
    @RequestMapping("/{job}.htm")
    public @ResponseBody String ejecutarJob(@PathVariable String job,@RequestParam(required = false) Integer mes){
        try {
            if(job.equals("porcentje-ventas")){
                jobService.jobPorcentajeVentas(mes);
            }
        } catch (Exception e) {
            return "Error";
        }
        
        return "OK";
    }
    
    
    @Scheduled(cron = "0 30 0 1 * *")
    public void jobPorcentajeVentas(){
        jobService.jobPorcentajeVentas(null);
    }
}
