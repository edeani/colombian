/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author joseefren
 */
public class Conexion {


private Connection conexion;
    private String bd = "x022vm01_caliycali";
    private String user = "x022vm01";
    private String password = "taYzLzk4hqx";
    private String server = "jdbc:mysql://200.58.112.133:3306/" + bd;
    public int estado;

    /**
     * Creates a new instance of BasedeDatos
     */
    public Conexion() {
        estado=0;
    }

    public void establecerConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
           
            conexion = DriverManager.getConnection(getServer(), getUser(), getPassword());
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Imposible realizar conexion con la BD "+e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.print("No es posible cerrar la Conexion");
            }
        }
    }

    public void cerrar(java.sql.Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }
    }

    public void destruir() {

        if (conexion != null) {

            try {
                conexion.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * @return the bd
     */
    public String getBd() {
        return bd;
    }

    /**
     * @param bd the bd to set
     */
    public void setBd(String bd) {
        this.bd = bd;
    }

    /**
     * @return the server
     */
    public String getServer() {
        return server;
    }

    /**
     * @param server the server to set
     */
    public void setServer(String server) {
        this.server = "jdbc:mysql://"+server;
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
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
}
