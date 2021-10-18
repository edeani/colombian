/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import java.util.List;
/**
 *
 * @author Jose Efren
 */
public interface SedesDao {
    public void guardarSede(Sedes sede);
    public List<Sedes> listSedes();
    public Sedes findSede(Long idSede);
    Sedes findSedeByName(String nameDataSource,String nombresede);
}
