/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.entidad.DetallePorcentajeVentas;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class DetallePorcentajeVentasDaoImpl extends GenericDaoImpl<DetallePorcentajeVentas> implements DetallePorcentajeVentasDao{
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<DetallePorcentajeVentas> generarDetallePorcentajeVentas(DataSource nameDataSource, int mes) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String sql = selectJdbTemplate("f.idsede,sum(f.valor_total) as total", "factura f", "month(f.fecha_factura) ="+mes+"  and year(curdate()) = year(f.fecha_factura) group by f.idsede");
        List<DetallePorcentajeVentas> porcentajeSedes = null;
        try {
           porcentajeSedes = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DetallePorcentajeVentas.class));
        } catch (DataAccessException e) {
            System.out.println("Error generarDetallePorcentajeVentas::"+e.getMessage());
        }
        return porcentajeSedes;
    }

    /**
     * Borra los detalles de porcentaje si le pasan un mes - sino se le pasa al mes se borra todo.
     * @param nameDataSource
     */
    @Override
    public void borrarDetallePorcentajeVentasAll(DataSource nameDataSource) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String sql = "delete from detalle_porcentaje_ventas";

        try {
           this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("Error borrarDetallePorcentajeVentasAll::"+e.getMessage());
        }
    }

    @Override
    public void insertarDetallePorcentajeVenta(DataSource nameDataSource, DetallePorcentajeVentas detallePorcentajeVentas) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String sql = insertJdbTemplate("idsede,total,porcentaje_venta,idporcentajeventa", "detalle_porcentaje_ventas", detallePorcentajeVentas.getIdsede()+","+detallePorcentajeVentas.getTotal()
        +","+detallePorcentajeVentas.getPorcentajeVenta()+","+detallePorcentajeVentas.getIdporcentajeventa());
        List<DetallePorcentajeVentas> porcentajeSedes = null;
        try {
           this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("Error insertarDetallePorcentajeVenta::"+e.getMessage());
        }
    }
    
}
