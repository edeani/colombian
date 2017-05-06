/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servicios.util;

import com.mycompany.entidades.Sedes;
import java.util.List;

/**
 *
 * @author joseefren
 */
public interface SedesService {
    
    public List<Sedes> listaSedes();
    public Sedes cambiarSede(Long sede,List<Sedes> sedes);
}
