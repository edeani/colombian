/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author user
 */
@Controller
public class BaseController {
    
    private static final String PROPIEDADES_COLOMBIAN="colombian.properties";
    
    private String getEntityKey(Object entity) {
        return StringUtils.uncapitalize(entity.getClass().getSimpleName());
    }

    protected void setBasicModel(ModelAndView mav, Object entity) {
        String entityKey = this.getEntityKey(entity);
        mav.addObject(entityKey, entity);
        mav.addObject("commandName", entityKey);
    }
    
    public static String getPROPIEDADES_COLOMBIAN() {
        return PROPIEDADES_COLOMBIAN;
    }
    
}
