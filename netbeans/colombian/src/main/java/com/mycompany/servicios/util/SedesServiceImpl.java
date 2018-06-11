/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servicios.util;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dao.SedesJpaController;
import com.mycompany.entidades.Sedes;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseefren
 */
public class SedesServiceImpl implements SedesService,Serializable{
    
    private static final String ARCHIVO_CONEXIONES ="/conexiones/conexiones.properties";
    private SedesJpaController  sedesJpa;
    private UserSessionBean user;
    
    public SedesServiceImpl()
    {
        user = UserSessionBean.getInstance();
    }
    
    @Override
    public List<Sedes> listaSedes(){
        List<Sedes> sedes  = new ArrayList<Sedes>();
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        conexion.setBd("principal");
        conexion.setPassword("YI15102206j.");
        conexion.setServer("192.168.0.22:3306"+"/principal");
        conexion.setUser("llmdvi");
        conexion.establecerConexion();
        connection = conexion.getConexion();
        
        if(connection!=null){
            ResultSet rs = null;
        
        Formatos formato = new Formatos();
        
        
        String query = "select s.* from usuario u "+
                        " inner join perfil_sede ps on ps.perfil = u.perfil "+
                        " inner join sede s on s.sed_cod=ps.sed_cod " +
                        " where u.usuario='"+user.getUsername()+"'";
        
          Statement st = null;
        
        try {
            st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            
           // ps.setDate(1, d);
     
            rs = ps.executeQuery();
            while(rs.next()){
             Sedes sede = new Sedes();
             sede.setBd(rs.getString("bd"));
             /*System.out.println("identificador");
             System.out.println("password");
             System.out.println("persistencia");
             System.out.println("sed_cod");
             System.out.println("sed_direccion");*/
             sede.setPassword(rs.getString("password"));
             sede.setIdentificador(rs.getString("identificador"));
             sede.setPersistencia(rs.getString("persistencia"));
             sede.setSed_direccion(rs.getString("sed_direccion"));
             sede.setSed_nombre(rs.getString("sed_nombre"));
             sede.setSed_telefono(rs.getString("sed_telefono"));
             sede.setSed_cod(rs.getLong("sed_cod"));
             sede.setUsuario(rs.getString("usuario"));
             sedes.add(sede);
            }
        } catch (Exception e) {

          
           
        }
        
        conexion.cerrar(rs);
        conexion.cerrar(st);
        conexion.destruir();
        }
        /*       
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(user.getNombrePersistencia());
        EntityManager em = factory.createEntityManager();
        
        sedesJpa = new SedesJpaController(null, factory);
        
        try {
            sedes = sedesJpa.findSedesEntities();
        } finally {
            em.close();
        }
        */
        conexion.setUser("root");
        return sedes;
        
    }

    @Override
    public Sedes cambiarSede(Long sede,List<Sedes> sedes) { 
       for (int i = 0; i < sedes.size(); i++) {
            if(sedes.get(i).getSed_cod() == sede){
                return sedes.get(i);
            }
        } 
       return null;
       /*LectorPropiedades lector = new LectorPropiedades();
       lector.setArchivo(ARCHIVO_CONEXIONES);
       lector.setPropiedad(sede.getSed_nombre());
       
       String propiedad = lector.leerPropiedad();
       System.out.print(propiedad);
       
       user.setNombrePersistencia(lector.getPersitencia(propiedad));
       user.setBaseDatos(lector.getBaseDatos(propiedad));*/
    }
}
