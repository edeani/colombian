/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.controller;

import com.administracion.util.LectorPropiedades;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author user
 */
@Controller
public class BaseController {
    
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    private static String propiedadPrincipal="basededatos";
    private static String archivo="colombian.properties";
    @Value("${basededatos}")
    private String dataPrincipal;
    private LectorPropiedades propiedades;
    
    public BaseController(){
        propiedades = new LectorPropiedades();
        propiedades.setArchivo(getArchivo());
        propiedades.setPropiedad(getPropiedadPrincipal());
    }
    /**
     * @return the propiedadPrincipal
     */
    public static String getPropiedadPrincipal() {
        return propiedadPrincipal;
    }

    /**
     * @param aPropiedadPrincipal the propiedadPrincipal to set
     */
    public static void setPropiedadPrincipal(String aPropiedadPrincipal) {
        propiedadPrincipal = aPropiedadPrincipal;
    }

    /**
     * @return the archivo
     */
    public static String getArchivo() {
        return archivo;
    }

    /**
     * @param aArchivo the archivo to set
     */
    public static void setArchivo(String aArchivo) {
        archivo = aArchivo;
    }

    //TODO: agregar manejo de excepciones particulares (no RuntimeException)

    private String getEntityKey(Object entity) {
        return StringUtils.uncapitalize(entity.getClass().getSimpleName());
    }

    protected void setBasicModel(ModelAndView mav, Object entity) {
        String entityKey = this.getEntityKey(entity);
        mav.addObject(entityKey, entity);
        mav.addObject("commandName", entityKey);
    }

    /**
     * @return the propiedades
     */
    public LectorPropiedades getPropiedades() {
        return propiedades;
    }

    /**
     * @param propiedades the propiedades to set
     */
    public void setPropiedades(LectorPropiedades propiedades) {
        this.propiedades = propiedades;
    }

    /**
     * @return Retorna la base 
     */
    public String getDataPrincipal() {
        return dataPrincipal;
    }
    
    
}
