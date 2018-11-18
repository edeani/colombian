/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dao.ClasePagoJpaController;
import com.mycompany.entidades.ClasePago;
import com.mycompany.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author EderArmando
 */
public class ClasePagoServiceImpl implements ClasePagoService {

    private ClasePagoJpaController clasePagoJpa;
    private final UserSessionBean user = UserSessionBean.getInstance();
    private final String password = user.getSede().getPassword();

    @Override
    public boolean pagoServiceXSede(String tipoPago) {

        ClasePago clasePago = null;
        Connection connection;
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
            ResultSet rs = null;
            Statement st = null;
            try {

                String consulta = "SELECT c.* FROM clase_pago c WHERE c.idsede = " + user.getSede().getSed_cod() + " and c.descripcion = '" + tipoPago + "'";

                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(consulta);

                // ps.setDate(1, d);
                rs = ps.executeQuery();

                while (rs.next()) {
                    clasePago = new ClasePago();
                    clasePago.setId(rs.getInt("id"));
                    clasePago.setDescripcion("descripcion");
                    clasePago.setIdsede(rs.getInt("idsede"));
                }
               
            } catch (Exception e) {
                System.out.println("ERROR pagoServiceXSede::" + e.getMessage());
            } finally {
                conexion.cerrar(rs);
                conexion.cerrar(st);
                conexion.destruir();
            }
        }
        return clasePago != null;
    }

}
