/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dao.ClientesJpaController;
import com.mycompany.dao.ConsignacionesJpaController;
import com.mycompany.entidades.Clientes;
import com.mycompany.entidades.Consignaciones;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.Asynchronous;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author joseefren
 */
public class CierreServiceImpl implements CierreService {

    ConsignacionesJpaController consignacionJPA;
    private UserSessionBean user = UserSessionBean.getInstance();
    private String password = user.getSede().getPassword();
    @Asynchronous
    @Override
    public Double cierreDiario(Date fechaCierre) {
        
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        if(password == null){
            conexion.setPassword("");
        } else{
            conexion.setPassword(password);
        }
        conexion.establecerConexion(user.getSede());
        connection = conexion.getConexion();
        Double caja_inicial =0D;
        if(connection != null){
        
        ResultSet rs = null;
        
        Formatos formato = new Formatos();
        
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        
        String query = "select caja_real  from cierre_diario  where fecha = '"+formato.fechaMenos(dfDefault.format(fechaCierre), 1)+"'";

        Statement st = null;
        
        try {
            st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            
           // ps.setDate(1, d);
     
            rs = ps.executeQuery();
            
            if(rs.next()){
                caja_inicial = rs.getDouble(1);
            }else{
           
            }
            
        } catch (Exception e) {

          System.out.print(e.getMessage());
            
        }
        
        conexion.cerrar(rs);
        conexion.cerrar(st);
        conexion.destruir();
        }else{
            user.setMensaje("NO CONECTA LA BASE DE DATOS "+user.getSede().getSed_nombre());
        }
        return caja_inicial;  
    }
    
    @Asynchronous
    @Override
    public Double cierreVentas(Date fechaCierre) {
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        if(password == null){
            conexion.setPassword("");
        } else{
            conexion.setPassword(password);
        }
        conexion.establecerConexion(user.getSede());
        connection = conexion.getConexion();
        Double ventas =0D;
        if(connection!=null){
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        Formatos formato = new Formatos();
        String f = formato.dateTostring(dfDefault.format(fechaCierre));
        String query = "select (totalventas + totalventas2 + totalventas3) as totalventas from( "+
           " SELECT  case when sum(ll.valor_total) is null then 0 else sum(ll.valor_total) end as totalventas , "+
           " (SELECT case when sum(o.valor_total) is null then 0 else sum(o.valor_total) end  as vo from orden o "+
           " where estado_orden = 'A' and fecha_orden = '"+f+"') as totalventas2,"+
           " (select case when sum(m.valor_total) is null then 0 else sum(m.valor_total) end as totalventas3 from mesa m "+
           " where estado_orden = 'A' and fecha_orden = '"+f+"' "+
           " ) as totalventas3 "+
           " from  llevar ll where estado_orden = 'A' and fecha_orden = '"+f+"' "+
           " )sub ";

        
        ResultSet rs = null;
        
        
        
        //java.sql.Date  d = formato.utilDateTosqlDate(fechaCierre);
        //Ejecutar la consulta
        Statement st = null;
        
        try {
            st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            
            
            
            rs = ps.executeQuery();
            
            rs.next();
            ventas = rs.getDouble(1);
            
        } catch (Exception e) {

          System.out.print(e.getMessage());
            
        }
        
        conexion.cerrar(rs);
        conexion.cerrar(st);
        conexion.destruir();
        }else{
            user.setMensaje("NO CONECTA LA BASE DE DATOS "+user.getSede().getSed_nombre());
        }
        
        return ventas;
    }
    
    @Asynchronous
    @Override
    public Double cierreGastos(Date fechaCierre) {
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        if(password == null){
            conexion.setPassword("");
        } else{
            conexion.setPassword(password);
        }
        conexion.establecerConexion(user.getSede());
        connection = conexion.getConexion();
        
        Double gastos =0D;
        if(connection!=null){
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        Formatos formato = new Formatos();
        String f = formato.dateTostring(dfDefault.format(fechaCierre));
        String query = "select SUM(gas_valor) as gastosTotal  from gastos  where gas_fecha ='"+f+"'";

        
        ResultSet rs = null;
        
        
        
        //java.sql.Date  d = formato.utilDateTosqlDate(fechaCierre);
        //Ejecutar la consulta
        Statement st = null;
        
        try {
            st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            
            
            
            rs = ps.executeQuery();
            
            rs.next();
            gastos = rs.getDouble(1);
            
        } catch (Exception e) {

          System.out.print(e.getMessage());
            
        }
        
        conexion.cerrar(rs);
        conexion.cerrar(st);
        conexion.destruir();
        }else{
            user.setMensaje("NO CONECTA LA BASE DE DATOS "+user.getSede().getSed_nombre());
        }
        return gastos;
    }
    
    
       @Asynchronous
       @Override
       public Double cierreConsignaciones(Date fechaCierre) {
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        if(password == null){
            conexion.setPassword("");
        } else{
            conexion.setPassword(password);
        }
        conexion.establecerConexion(user.getSede());
        connection = conexion.getConexion();
        Double consignaciones =0D;
        if(connection!=null){
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        Formatos formato = new Formatos();
        String f = formato.dateTostring(dfDefault.format(fechaCierre));
        String query = "select SUM(valor_consignacion) as consignacionTotal  from consignaciones  where fecha ='"+f+"'";

        
        ResultSet rs = null;
        
        
        
        //java.sql.Date  d = formato.utilDateTosqlDate(fechaCierre);
        //Ejecutar la consulta
        Statement st = null;
        
        try {
            st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            
            
            
            rs = ps.executeQuery();
            
            rs.next();
           consignaciones = rs.getDouble(1);
            
        } catch (Exception e) {

          System.out.print(e.getMessage());
            
        }
        
        conexion.cerrar(rs);
        conexion.cerrar(st);
        conexion.destruir();
        }else{
            user.setMensaje("NO CONECTA LA BASE DE DATOS "+user.getSede().getSed_nombre());
        }
        return consignaciones;
    }

    @Override
    public Double cierrCajaFinal(Double ventas, Double gastos, Double cajaInicial, Double consignaciones) {
        
        return (ventas + cajaInicial - consignaciones - gastos);
        
    }
    @Asynchronous
    @Override
    public List<Consignaciones> cierreListaConsignaciones(Date fechaCierre) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(user.getSede().getPersistencia());
        List<Consignaciones> listaConsignaciones = null;
        Connection connection=null;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        if(password == null){
            conexion.setPassword("");
        } else{
            conexion.setPassword(password);
        }
        conexion.establecerConexion(user.getSede());
        connection = conexion.getConexion();
        if(connection!=null){
        EntityManager em = factory.createEntityManager();
        
        consignacionJPA = new  ConsignacionesJpaController(null, factory);
        
        try {
            connection.close();
           String consulta = "SELECT c FROM Consignaciones c WHERE c.fecha = :fecha ORDER BY c.fechaConsignacion";
           Query query = em.createQuery(consulta);
           query.setParameter("fecha", fechaCierre, TemporalType.DATE);
           listaConsignaciones =  query.getResultList();
        }catch(Exception e){
            System.out.println("cierreListaConsignaciones::"+e.getMessage());
        } finally {
            em.close();
            
        }
        }else{
            user.setMensaje("NO CONECTA LA BASE DE DATOS "+user.getSede().getSed_nombre());
        }
        
        return listaConsignaciones;
        //return null;
        
      
    }
       
  
      
 
    
    
}
