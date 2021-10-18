    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package com.colombia.cali.colombiancaliycali.dao.generic;

    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    /**
     *
     * @author Eslayfer
     */
    @Service
    public class CaliycaliDaoImpl implements CaliycaliDao{

         @Override
         @Transactional
            public String selectJdbTemplate(String parametros, String tabla, String condiciones){            
                StringBuffer sql = new StringBuffer("");            
                sql.append("SELECT "+parametros+" FROM "+tabla);
                    if(condiciones!=null && condiciones!=""){
                    sql.append(" WHERE "+condiciones);
            }                
                System.out.println(sql.toString());    
                return sql.toString();
            }

          @Override
         @Transactional
            public String updateJdbTemplate(String parametros, String tabla, String condiciones){            
                StringBuffer sql = new StringBuffer("");            
                sql.append("UPDATE "+tabla+" SET "+parametros);
                    if(condiciones!=null && condiciones!=""){
                    sql.append(" WHERE "+condiciones);
            }                
                return sql.toString();
            }

           @Override
         @Transactional
            public String insertJdbTemplate(String parametros, String tabla, String condiciones){            
                StringBuffer sql = new StringBuffer("");            
                sql.append("INSERT INTO "+tabla+" ("+parametros+") VALUES ("+condiciones+")"); 

                return sql.toString();
            }

          @Override
         @Transactional
            public String deleteJdbTemplate(String tabla, String condiciones){            
                StringBuffer sql = new StringBuffer("");            
                sql.append("DELETE FROM "+tabla);
                    if(condiciones!=null && condiciones!=""){
                    sql.append(" WHERE "+condiciones);
            }                
                return sql.toString();
            }

        @Override
        public String addInsertJdtbTemplate(String values1, String values2, int iteracion) {
            if(iteracion == 0){
                return values2;
            }
            return values1+",("+values2+")";
        }
    }
