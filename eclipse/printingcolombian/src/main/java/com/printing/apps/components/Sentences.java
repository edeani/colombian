package com.printing.apps.components;

public interface Sentences {

	String selectJdbTemplate(String parametros, String tabla, String condiciones);
    String updateJdbTemplate(String parametros, String tabla, String condiciones);
    String insertJdbTemplate(String parametros, String tabla, String condiciones);
    String deleteJdbTemplate(String tabla, String condiciones);   
    String addInsertJdtbTemplate(String values1,String values2,int iteracion);
    String getComa();
}
