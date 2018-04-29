/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.entidad.Averias;
import com.administracion.util.Formatos;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class AveriasDaoImpl extends GenericDaoImpl<Averias> implements AveriasDao {

    private JdbcTemplate jdbctemplate;

    
    private static final String ESTADO_AVERIA = "A";

    @Override
    public void guardarAveria(DataSource nameDataSource, String valorTotal, String usuario) {
        Formatos formato = new Formatos();
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbctemplate.execute(insertJdbTemplate("fecha_averia,estado_averia,pago_orden,valor_total,id_usuario", "averias", "'" + formato.dateTostring(new Date()) + "','"+ESTADO_AVERIA+"',1," + valorTotal + ",'" + usuario + "'"));
        } catch (DataAccessException e) {
            System.out.println("guardarAveria:" + e.getMessage());
        }

    }

    @Override
    public void guardarDetalleAveria(DataSource nameDataSource, String detalleAveria, String valorTotal, String usuario, Long numeroAveria) {
        String[] fila = detalleAveria.split("@");
        try {
            for (int i = 0; i < fila.length; i++) {
                String[] datosFila = fila[i].split(",");
                this.jdbctemplate.execute(insertJdbTemplate("secuencial_averia,numero_averia,codigo_producto,valor_producto,numero_unidades", "detalle_averias", (i + 1) + "," + numeroAveria + "," + datosFila[0] + "," + datosFila[4] + "," + datosFila[2]));
            }
        } catch (DataAccessException e) {
            System.out.println("guardarDetalleAveria:" + e.getMessage());
        }

    }

    @Override
    public Long secuenciaAveria(DataSource nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        Long secuencia = 0L;
        try {
            secuencia = this.jdbctemplate.queryForObject(selectJdbTemplate("max(numero_averia)", "averias", ""),Long.class);
        } catch (DataAccessException e) {
            System.out.println("secuenciaAveria:" + e.getMessage());
        }
        return secuencia;
    }

}
