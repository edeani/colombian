/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author edeani
 */
public interface JasperService {
    
    public void exportReport(String type,String jasperFile,Map<String,Object> parameters,Collection<?> list,String exportFile,HttpServletRequest request,HttpServletResponse response)throws JRException, IOException;
    
}
