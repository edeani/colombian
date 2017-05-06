/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.util;

import com.mycompany.entidades.Sedes;
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
    private String bd = "colombian_2012";
    private String user = "root";
    private String password = "";
    private String server = "jdbc:mysql://192.168.0.23:3306/" + bd;
    private static final String PREFIJO_CONEXION = "jdbc:mysql://";
    private static final String SLASH = "/";
    public int estado;

    /**
     * Creates a new instance of BasedeDatos
     */
    public Conexion() {
        estado = 0;
    }
    
    /**
     * Metodo para una conexion general
     */
    public void establecerConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(getServer(), getUser(), getPassword());
        } catch (Exception e) {
            System.out.println("establecerConexion::Imposible realizar conexion con la BD " + e.getMessage());
        }
    }

    /**
     * Metodo que conecta a una sede con los datos de BD de la tabla sede
     *
     * @param sede
     */
    public void establecerConexion(Sedes sede) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(PREFIJO_CONEXION + sede.getIdentificador() + SLASH + sede.getBd(), sede.getUsuario(), sede.getPassword());
        } catch (ClassNotFoundException e) {
            System.out.println("establecerConexionSede::ClassNotFoundException::Imposible realizar conexion con la BD " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("establecerConexionSede::SQLException::Imposible realizar conexion con la BD " + e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.print("No es posible cerrar la Conexion");
            }
        }
    }

    public void cerrar(java.sql.Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            }
        }
    }

    public void destruir() {

        if (conexion != null) {

            try {
                conexion.close();
            } catch (Exception e) {
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
        this.server = PREFIJO_CONEXION + server;
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
