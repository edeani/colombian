/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dao.FacturaJpaController;
import com.mycompany.entidades.Factura;
import com.mycompany.mapper.Compras;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author joseefren
 */
public class ComprasServiceImpl implements ComprasService{

    private UserSessionBean user = UserSessionBean.getInstance();
    //private FacturaJpaController facturaJPA;
    private String password = user.getSede().getPassword();
    
    private Double totalCompras;
    
    @Override
    public List<Compras> listadoCompras(Date Finicial, Date Ffinal) {
       Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        conexion.setUser(user.getSede().getUsuario());
        if(password == null){
            conexion.setPassword("");
        } else{
            conexion.setPassword(password);
        }
        
        conexion.setServer(user.getSede().getIdentificador()+"/"+user.getSede().getBd());
        conexion.establecerConexion();
        connection = conexion.getConexion();
        List<Compras> compras = new ArrayList<Compras>();
        totalCompras = 0D;
        if(connection!=null){
        ResultSet rs = null;
        
        Formatos formato = new Formatos();
        
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = " SELECT inventario.codigo_producto_inventario, "+   
		       " inventario.descripcion_producto,   SUM(detalle_factura.valor_producto) detalle_factura_valor_producto, "+
                       " sum(detalle_factura.numero_unidades) numero_unidades "+
                       " FROM detalle_factura,   factura,   inventario  "+
                       " WHERE ( factura.numero_factura = detalle_factura.numero_factura ) and  " +
                       " ( inventario.codigo_producto_inventario = detalle_factura.codigo_producto_inventario ) and " +  
                       " ( ( factura.fecha_factura between '"+formato.dateTostring(dfDefault.format(Finicial))+"' and '"+formato.dateTostring(dfDefault.format(Ffinal))+"' ) AND  "+
                       " ( factura.estado_factura = 'A' ) )  "+
	               " group by inventario.codigo_producto_inventario,inventario.descripcion_producto ";
        
        Statement st = null;
        
       
        try {
            st = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);
            
           // ps.setDate(1, d);
     
            rs = ps.executeQuery();
            
            while(rs.next()){
                Compras compra = new Compras();
                compra.setCodigo_producto_inventario(rs.getLong("codigo_producto_inventario"));
                compra.setDescripcion_producto(rs.getString("descripcion_producto"));
                compra.setDetalle_factura_valor_producto(""+rs.getDouble("detalle_factura_valor_producto"));
                compra.setNumero_unidades(""+rs.getFloat("numero_unidades"));
                //promedio
                Double promedio;
                promedio = Double.parseDouble(compra.getDetalle_factura_valor_producto())/Double.parseDouble(compra.getNumero_unidades());
                compra.setPromedio(formato.numeroToStringFormato(promedio));
                //valor_producto
                Double valor_producto;
                valor_producto = Double.parseDouble(compra.getDetalle_factura_valor_producto());
                totalCompras+=valor_producto;
                compra.setDetalle_factura_valor_producto(formato.numeroToStringFormato(valor_producto));
                compras.add(compra);
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
        return compras;
    }

    
    
    /**
     * @return the totalCompras
     */
    public Double getTotalCompras() {
        return totalCompras;
    }

    /**
     * @param totalCompras the totalCompras to set
     */
    public void setTotalCompras(Double totalCompras) {
        this.totalCompras = totalCompras;
    }
    
    
    
    
}
