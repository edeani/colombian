/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import java.util.List;

/**
 *
 * @author edeani
 */

public interface SedesService {
    
    public List<ItemsDTO> listaSedesOptions(String nameDatasource);
    public List<Sedes> traerSedes(String nameDatasource);
    public Sedes buscarSede(String nameDatasource,Long idSede);
}
