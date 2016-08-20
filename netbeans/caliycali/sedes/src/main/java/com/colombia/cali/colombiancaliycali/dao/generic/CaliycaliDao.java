/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao.generic;

/**
 *
 * @author Eslayfer
 */
public interface CaliycaliDao {
    
    public String selectJdbTemplate(String parametros, String tabla, String condiciones);
    
    public String updateJdbTemplate(String parametros, String tabla, String condiciones);
    
    public String insertJdbTemplate(String parametros, String tabla, String condiciones);
    
    public String deleteJdbTemplate(String tabla, String condiciones);
    
    public String addInsertJdtbTemplate(String values1,String values2,int iteracion);
    
}
