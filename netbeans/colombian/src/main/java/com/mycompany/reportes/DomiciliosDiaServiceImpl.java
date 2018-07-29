/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Ordenes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class DomiciliosDiaServiceImpl implements DomiciliosDiaService {

    private UserSessionBean user = UserSessionBean.getInstance();
    private String password = user.getSede().getPassword();
    private Double totalDomicilios;
    private Long totalRegistros;

    @Override
    public List<Ordenes> domicilioDia(Date fi, Date ff) {
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        conexion.setUser(user.getSede().getUsuario());
        if (password == null) {
            conexion.setPassword("");
        } else {
            conexion.setPassword(password);
        }
        conexion.setServer(user.getSede().getIdentificador() + "/" + user.getSede().getBd());
        conexion.establecerConexion();
        connection = conexion.getConexion();

        totalDomicilios = 0D;
        totalRegistros = 0L;
        Double caja_inicial = 0D;
        List<Ordenes> ordenes = new ArrayList<Ordenes>();
        if (connection != null) {
            ResultSet rs = null;

            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

            String query = " SELECT orden.fecha_orden as Fecha, case when (DAYNAME(orden.fecha_orden))='Monday' then 'Lunes' else (case when "
                    + " (DAYNAME(orden.fecha_orden))='Tuesday' then 'Martes' else (case when "
                    + " (DAYNAME(orden.fecha_orden))='Wednesday' then 'Miercoles' else (case when "
                    + " (DAYNAME(orden.fecha_orden))='Thursday' then 'Jueves' else (case when "
                    + " (DAYNAME(orden.fecha_orden))='Friday' then 'Viernes' else (case when  "
                    + " (DAYNAME(orden.fecha_orden))='Saturday' then 'Sabado' else (case when   (DAYNAME(orden.fecha_orden))='Sunday' then 'Domingo' else (DAYNAME(orden.fecha_orden) )end)end)end)end)end)end)end  as Dia, "
                    + " count(*)Domicilios, sum(orden.valor_total) Valor_Total   FROM        orden   "
                    + " WHERE  ( ( orden.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "'  and '" + formato.dateTostring(dfDefault.format(ff)) + "' ) AND  "
                    + " ( orden.estado_orden = 'A' ) )    "
                    + " group by orden.fecha_orden";

            Statement st = null;

            try {
                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);

                // ps.setDate(1, d);

                rs = ps.executeQuery();

                while (rs.next()) {
                    Ordenes o = new Ordenes();

                    o.setDia(rs.getString("Dia"));
                    o.setFecha(rs.getDate("Fecha"));
                    o.setRegistros(formato.numeroToStringFormato(rs.getLong("Domicilios")));
                    o.setValorTotal(formato.numeroToStringFormato(rs.getDouble("Valor_Total")));

                    totalDomicilios += rs.getDouble("Valor_Total");
                    totalRegistros += rs.getLong("Domicilios");
                    ordenes.add(o);

                }

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

            conexion.cerrar(rs);
            conexion.cerrar(st);
            conexion.destruir();
        } else {
            user.setMensaje("NO CONECTA LA BASE DE DATOS " + user.getSede().getSed_nombre());
        }
        return ordenes;
    }
    
    @Override
    public void anularDomicilio(Long idDomicilio) {
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        conexion.setUser(user.getSede().getUsuario());
        if (password == null) {
            conexion.setPassword("");
        } else {
            conexion.setPassword(password);
        }
        conexion.setServer(user.getSede().getIdentificador() + "/" + user.getSede().getBd());
        conexion.establecerConexion();
        connection = conexion.getConexion();
        
        if (connection != null) {
        
            String query ="update orden set estado_orden='I' where numero_orden="+idDomicilio;
            Statement st = null;

            try {
                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);
                
                ps.executeUpdate();
                
            }catch(SQLException e){
                user.setMensaje("NO CONECTA LA BASE DE DATOS " + user.getSede().getSed_nombre());
            }
        }
    }
    /**
     * @return the totalDomicilios
     */
    public Double getTotalDomicilios() {
        return totalDomicilios;
    }

    /**
     * @return the totalRegistros
     */
    public Long getTotalRegistros() {
        return totalRegistros;
    }
}
