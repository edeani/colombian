/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dao.BarriosJpaController;
import com.mycompany.entidades.Barrios;
import com.mycompany.util.Conexion;
import java.sql.Connection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author joseefren
 */
public class BarriosServiceImpl implements BarriosService{

    BarriosJpaController barrioController;
    private UserSessionBean user = UserSessionBean.getInstance();
    @Override
    public List<Barrios> traerBarrios() {
        
       EntityManagerFactory factory = Persistence.createEntityManagerFactory(user.getSede().getPersistencia());
       
       Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        conexion.setUser(user.getSede().getUsuario());
        if(user.getSede().getPassword() == null){
            conexion.setPassword("");
        } else{
            conexion.setPassword(user.getSede().getPassword());
        }
        
        conexion.setServer(user.getSede().getIdentificador()+"/"+user.getSede().getBd());
        conexion.establecerConexion();
        connection = conexion.getConexion();
       List<Barrios> barrios = null;
       if(connection != null){
           EntityManager em = factory.createEntityManager();
        try {
            connection.close();
            String consulta = "SELECT b from Barrios b ORDER BY b.descripcionBarrio";
            Query query = em.createQuery(consulta);
            barrios = query.getResultList();
        } catch (Exception e) {
        }finally
        {
            em.close();
        }
       }else{
            user.setMensaje("NO CONECTA LA BASE DE DATOS "+user.getSede().getSed_nombre());
        }
        return barrios;
    }
    
}
