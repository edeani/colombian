/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;


import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.OrdenesDomiciliosMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 *
 * @author joseefren
 */


public class OrdenesDomiciliosServiceImpl implements OrdenesDomiciliosService {
    
    
    private UserSessionBean user = UserSessionBean.getInstance();
    private String password = user.getSede().getPassword();

    private Double totalvalor;
    @Override
     public List<OrdenesDomiciliosMapper> domiciliosordenes(Date fi, Date ff) {
        
         Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        if(getPassword() == null){
            conexion.setPassword("");
        } else{
            conexion.setPassword(getPassword());
        }
        conexion.setServer(getUser().getSede().getIdentificador()+"/"+getUser().getSede().getBd());
        conexion.establecerConexion();
        connection = conexion.getConexion();
        List<OrdenesDomiciliosMapper> ordenesdomi = new ArrayList<OrdenesDomiciliosMapper>();
        setTotalvalor((Double) 0D);
        if(connection!=null){
        ResultSet rs = null;
        
        Formatos formato = new Formatos();
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        
        String query =" SELECT orden.numero_telefono, "+   
                      " orden.numero_orden, "+   
                      " clientes.descripcion_cliente, "+   
                      " orden.valor_total, "+   
                      " orden.fecha_orden, "+   
                      " barrios.descripcion_barrio "+  
                      " FROM clientes,orden,barrios "+  
                      " WHERE ( orden.numero_telefono = clientes.numero_telefono ) and "+  
                      " ( barrios.codigo_barrio = clientes.codigo_barrio ) and "+  
                      " ( ( orden.fecha_orden between '"+formato.dateTostring(dfDefault.format(fi))+"' and '"+formato.dateTostring(dfDefault.format(ff))+"' ) AND "+  
                      " ( orden.estado_orden = 'A' ) ) "+   
                      " ORDER BY orden.numero_orden ASC ";
       Statement st = null;
        
        try {
            st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            
           // ps.setDate(1, d);
     
            rs = ps.executeQuery();
    
            while(rs.next()){
             OrdenesDomiciliosMapper od= new OrdenesDomiciliosMapper();
             
             od.setOrden(formato.numeroToStringFormato(rs.getLong("orden.numero_orden")));
             od.setTelefono(rs.getString("orden.numero_telefono"));
             od.setBarrio(rs.getString("barrios.descripcion_barrio"));
             od.setCliente(rs.getString("clientes.descripcion_cliente"));
             od.setFecha(rs.getDate("orden.fecha_orden"));
             od.setValor(formato.numeroToStringFormato(rs.getDouble("orden.valor_total")));
             
             totalvalor += rs.getDouble("orden.valor_total");     
             //totalRegistros += totalRegistros ;
             ordenesdomi.add(od);
             
            }
            
        } catch (Exception e) {

          System.out.println(e.getMessage());
           
        }
        
        conexion.cerrar(rs);
        conexion.cerrar(st);
        conexion.destruir();
        }else{
            getUser().setMensaje("NO CONECTA LA BASE DE DATOS "+getUser().getSede().getSed_nombre());
        }
        return ordenesdomi;
           
        
  }        

    /**
     * @return the user
     */
    public UserSessionBean getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserSessionBean user) {
        this.user = user;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the totalvalor
     */
    public Double getTotalvalor() {
        return totalvalor;
    }

    /**
     * @param totalvalor the totalvalor to set
     */
    public void setTotalvalor(Double totalvalor) {
        this.totalvalor = totalvalor;
    }
    
    
    
     
    
    
    
 }   
    
