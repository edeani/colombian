/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.entidades.CambioSede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class CambioSedeDaoImpl implements CambioSedeDao{

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;
    
    @Override
    public void guardarCambioSede(String nameDataSource, CambioSede cambioSede) {
       this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbctemplate.execute(caliycaliDao.insertJdbTemplate("numero_factura,sede_origen,sede_destino,fecha_traslado", "cambiosede",
                    cambioSede.getNumeroFactura() + "," + cambioSede.getSedeOrigen() + "," + cambioSede.getSedeDestino() + ",'" + Formatos.dateTostringFechacompleta(cambioSede.getFechaTraslado()) + "'"));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión guardarCambioSede :" + e.getMessage());
        }
    }
    
}
