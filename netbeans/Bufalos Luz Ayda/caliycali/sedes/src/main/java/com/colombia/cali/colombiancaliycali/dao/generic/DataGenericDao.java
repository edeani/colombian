/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao.generic;

import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.LectorPropiedades;
import static com.colombian.cali.colombiancaliycali.controllers.BaseController.getArchivo;
import static com.colombian.cali.colombiancaliycali.controllers.BaseController.getPropiedadPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Jose Efren
 */
public class DataGenericDao {
   private LectorPropiedades propiedades;
   
   @Autowired
   public CaliycaliDao caliycaliDao;
   
   public DataGenericDao(){
       propiedades = new LectorPropiedades();
       propiedades.setArchivo("/bd/basedatos.properties");
   }

    public LectorPropiedades getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(LectorPropiedades propiedades) {
        this.propiedades = propiedades;
    }
   
    public Integer obtenerLongitudNivelCuenta(String longitudes,int nivel){
        String []arreglo = longitudes.split("-");
        Integer longitud = -1;
        if(nivel <= arreglo.length){
            longitud = Integer.parseInt(arreglo[nivel]);
        }
        return longitud;
    }
}
