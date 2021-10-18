/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.security;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dao.UsuarioJpaController;
import com.mycompany.entidades.Usuario;
import com.mycompany.util.Conexion;
import com.mycompany.util.Formatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.resource.spi.AuthenticationMechanism;
import javax.swing.JOptionPane;

/**
 *
 * @author eder
 */
public class UsuarioServiceImpl implements UsuarioService {

    UsuarioJpaController usuarioJpa;
    private UserSessionBean user = UserSessionBean.getInstance();

    @Override
    public Usuario encontraUsuario(String nombre, String pwd) {

        /* EntityManagerFactory factory = Persistence.createEntityManagerFactory(user.getNombrePersistencia());
        EntityManager em = factory.createEntityManager();
        usuarioJpa = new UsuarioJpaController(null, factory);
        Usuario usuario = null;
        try {
            List<Usuario> lista = usuarioJpa.findUsuarioEntities();

            if (!lista.isEmpty()) {
                for (int i = 0; i < lista.size(); i++) {

                    if (lista.get(i).getUsuario().equals(nombre) && lista.get(i).getPwd().equals(pwd)) {
                       usuario = lista.get(i);
                    }
                }
            }

        } finally {
            em.close();
        }*/
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        conexion.setBd("principal");
        conexion.setPassword("YI15102206j");
        conexion.setServer("localhost:3306" + "/principal");
        conexion.setUser("llmdvi");
        conexion.establecerConexion();
        connection = conexion.getConexion();
        Usuario usuario = null;
        if (connection != null) {
            ResultSet rs = null;

            String query = "select * from usuario u "
                    + " where u.usuario='" + nombre + "' and u.pwd='" + pwd + "'";

            Statement st = null;
            try {
                st = connection.createStatement();
                PreparedStatement ps = connection.prepareStatement(query);

                // ps.setDate(1, d);
                rs = ps.executeQuery();
                 while(rs.next()){
                     usuario= new Usuario();
                     usuario.setCedula_usuario(rs.getLong("cedula_usuario"));
                     usuario.setEstado(rs.getString("estado"));
                     usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                     usuario.setPwd(rs.getString("pwd"));
                     usuario.setTelefono_usuario(rs.getLong("telefono_usuario"));
                     usuario.setUsuario(rs.getString("usuario"));
                     usuario.setAnulaciones(rs.getString("anulaciones"));
                 }
                
            } catch (Exception e) {
                System.out.println("Error "+e.getMessage());
            }
        }
        return usuario;
    }
}
