/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.security;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dao.UsuarioJpaController;
import com.mycompany.entidades.Usuario;
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

        EntityManagerFactory factory = Persistence.createEntityManagerFactory(user.getNombrePersistencia());
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
        }
        return usuario;
    }
}
