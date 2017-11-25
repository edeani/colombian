/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.entidad.CambioSede;
import com.administracion.util.Formatos;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class CambioSedeDaoImpl extends GenericDaoImpl<CambioSede> implements CambioSedeDao{

    private JdbcTemplate jdbctemplate;

    
    @Override
    public void guardarCambioSede(DataSource nameDataSource, CambioSede cambioSede) {
       this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbctemplate.execute(insertJdbTemplate("numero_factura,sede_origen,sede_destino,fecha_traslado", "cambiosede",
                    cambioSede.getNumeroFactura() + "," + cambioSede.getSedeOrigen() + "," + cambioSede.getSedeDestino() + ",'" + Formatos.dateTostringFechacompleta(cambioSede.getFechaTraslado()) + "'"));
        } catch (DataAccessException e) {
            System.out.println("Error de conexión guardarCambioSede :" + e.getMessage());
        }
    }
    
}
