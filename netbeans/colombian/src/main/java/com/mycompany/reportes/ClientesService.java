/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompany.dao.ClientesJpaController;
import com.mycompany.entidades.Barrios;
import com.mycompany.entidades.Clientes;
import java.util.List;

/**
 *
 * @author user
 */
public interface ClientesService {
    public List<Clientes> listarClientes();
    public List<Clientes> listaClienteBarrio(Long barrio);
}
