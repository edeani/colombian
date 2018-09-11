/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Mesasyllevar;
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
public class MesasyllevarServiceImpl implements MesasyllevarService {

    private UserSessionBean user = UserSessionBean.getInstance();
    private String password = user.getSede().getPassword();

    private Double totalvalor;

    @Override
    public List<Mesasyllevar> mesas(Date fi, Date ff) {
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        conexion.setUser(user.getSede().getUsuario());
        if (getPassword() == null) {
            conexion.setPassword("");
        } else {
            conexion.setPassword(getPassword());
        }
        conexion.setServer(getUser().getSede().getIdentificador() + "/" + getUser().getSede().getBd());
        conexion.establecerConexion();
        connection = conexion.getConexion();
        List<Mesasyllevar> llevar = new ArrayList<Mesasyllevar>();
        totalvalor = 0D;
        if (connection != null) {
            ResultSet rs = null;

            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

            String query = " SELECT 'Mesa' tipo, "
                    + " mesa.numero_orden, "
                    + " mesa.fecha_orden, "
                    + " mesa.valor_total, "
                    + " mesa.numero_mesa, "
                    + " mesa.codigo_mesera "
                    + " FROM mesa "
                    + " WHERE ( mesa.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "' ) AND ( mesa.estado_orden = 'A' ) "
                    + " UNION "
                    + " SELECT 'Llevar' tipo, "
                    + " llevar.numero_orden, "
                    + " llevar.fecha_orden, "
                    + " llevar.valor_total, "
                    + " llevar.numero_mesa, "
                    + " llevar.codigo_mesera "
                    + " FROM llevar "
                    + " WHERE ( llevar.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "' ) AND ( llevar.estado_orden = 'A' ) "
                    + " ORDER BY 2";

            Statement st = null;

            try {
                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);

                // ps.setDate(1, d);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Mesasyllevar ll = new Mesasyllevar();

                    ll.setFecha(rs.getDate("fecha_orden"));
                    ll.setOrden(formato.numeroToStringFormato(rs.getLong("numero_orden")));
                    ll.setTipo(rs.getString("tipo"));
                    ll.setValor(formato.numeroToStringFormato(rs.getDouble("valor_total")));
                    ll.setMesa(formato.numeroToStringFormato(rs.getLong("numero_mesa")));
                    ll.setCodMesera(formato.numeroToStringFormato(rs.getLong("codigo_mesera")));

                    totalvalor += rs.getDouble("valor_total");
                    //totalRegistros += totalRegistros ;
                    llevar.add(ll);

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
        return llevar;

    }

    @Override
    public void anularMesa(Long idMesa) {
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

            String query = "update mesa set estado_orden='I' where numero_orden=" + idMesa;
            Statement st = null;

            try {
                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);

                ps.executeUpdate();

            } catch (SQLException e) {
                user.setMensaje("NO CONECTA LA BASE DE DATOS " + user.getSede().getSed_nombre());
            }
        }
    }

    @Override
    public void anularLlevar(Long idLlevar) {
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

            String query = "update llevar set estado_orden='I' where numero_orden=" + idLlevar;
            Statement st = null;

            try {
                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);

                ps.executeUpdate();

            } catch (SQLException e) {
                user.setMensaje("NO CONECTA LA BASE DE DATOS " + user.getSede().getSed_nombre());
            }
        }
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
