/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dao.ClientesJpaController;
import com.mycompany.entidades.Clientes;
import com.mycompany.util.Conexion;
import java.sql.Connection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class ClientesServiceImpl implements ClientesService {

    ClientesJpaController clienteJpa;
    private UserSessionBean user = UserSessionBean.getInstance();

    @Override
    public List<Clientes> listarClientes() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(user.getSede().getPersistencia());
       
        Connection connection;
        //Me conecto a la base de datos
        Conexion conexion = new Conexion();
        if (user.getSede().getPassword() == null) {
            conexion.setPassword("");
        } else {
            conexion.setPassword(user.getSede().getPassword());
        }
        conexion.establecerConexion(user.getSede());
        connection = conexion.getConexion();

        List<Clientes> listaClientes = null;
        if (connection != null) {
             EntityManager em = factory.createEntityManager();
            clienteJpa = new ClientesJpaController(null, factory);
            try {
                connection.close();
                listaClientes = clienteJpa.findClientesEntities();
            } catch (Exception e) {
            } finally {
                em.close();
            }
        } else {
            user.setMensaje("NO CONECTA LA BASE DE DATOS " + user.getSede().getSed_nombre());
        }

        return listaClientes;
        //return null;
    }

    @Override
    public List<Clientes> listaClienteBarrio(Long barrio) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(user.getSede().getPersistencia());
        EntityManager em = factory.createEntityManager();

        clienteJpa = new ClientesJpaController(null, factory);
        List<Clientes> listaClientes = null;

        try {

            String consulta = "SELECT c FROM Clientes c where c.barrio.codigoBarrio = :street ORDER BY c.telefono";
            Query query = em.createQuery(consulta);
            query.setParameter("street", barrio);
            listaClientes = query.getResultList();

        } finally {
            em.close();
        }

        return listaClientes;
    }
}
