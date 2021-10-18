/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import com.colombian.cali.colombiancaliycali.entidades.Subprincipal;
import java.util.List;

/**
 *
 * @author edeani
 */

public interface SedesService {
    
    public List<ItemsDTO> listaSedesOptions(String nameDatasource);
    public List<Sedes> traerSedes(String nameDatasource);
    public Sedes buscarSede(String nameDatasource,Long idSede);
    public Sedes buscarSedeXNombre(String nameDatasource,String nombresede);
    Subprincipal findSubPrincipalByIdsede(String nameDatasource,Integer idSede);
}
