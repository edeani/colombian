/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.enumeration.ExtencionesEnum;
import com.administracion.service.autorizacion.AccesosSubsedes;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author edeani
 */
@Controller
@RequestMapping("/{sede:[a-zA-Z]+}/logo")
public class LogoEmpresaController {
    
    @Autowired
    private AccesosSubsedes accesosSubsedes;
    @Value("${path.img}")
    private String PATH_IMG;
    
    @RequestMapping("/admin.htm")
    public ModelAndView indexLogo(){
        return new ModelAndView("logos/imagenes");
    }
    
    @RequestMapping(value = "/guardar.htm",method = RequestMethod.POST)
    public ModelAndView guardarCaracteristicas(@RequestParam(value = "imagen", required = false) MultipartFile image,
            @PathVariable String sede) throws Exception{
        if(image!=null){
            try {
                    String extension = "";
                    String nombreCompleto = sede;
                    if (image.getContentType().contains("jpeg")) {
                        nombreCompleto += ExtencionesEnum.JPG.getExt();
                    } else if (image.getContentType().contains("png")) {
                        nombreCompleto += ExtencionesEnum.PNG.getExt();
                    } else if (image.getContentType().contains("gif")) {
                        nombreCompleto += ExtencionesEnum.GIF.getExt();
                    } else {
                        nombreCompleto = image.getOriginalFilename();
                    }
                    FileCopyUtils.copy(image.getBytes(), new File(PATH_IMG+nombreCompleto));
                } catch (IOException ex) {
                   
                    throw new Exception("Falló carga imagen");
                }
        }
        return new ModelAndView("logos/imagenes");
    } 
}
