/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dataSource;

import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Eslayfer
 */
@Service
public class ProjectsDaoImpl implements ProjectsDao{
    
    //clase generica para obnetenr la conecion segun un dataSource descrito en el aplicationContext.xml    
   @Override
    @Transactional(readOnly=true)
    public DataSource getDatasource(String nombreDataSource){
        ApplicationContext ds=new ClassPathXmlApplicationContext("applicationContext.xml");     
        DataSource dataSource= (DataSource)ds.getBean(nombreDataSource);
        return dataSource;
    }
   @Override
   @Transactional(readOnly=true)
   public String baseDatos(){
       ApplicationContext ds=new ClassPathXmlApplicationContext("applicationContext.xml");   
       return ds.getBeanDefinitionNames()[0];
   }
  
    
}
