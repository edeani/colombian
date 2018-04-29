/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;


import com.administracion.entidad.DetallePorcentajeVentas;
import com.administracion.entidad.PorcentajeVentas;
import com.administracion.util.Formatos;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class PorcentajeVentasDaoImpl extends GenericDaoImpl<PorcentajeVentas> implements PorcentajeVentasDao {

    private JdbcTemplate jdbcTemplate;
 

    @Override
    public Double PorcentajeVentasDaoImpl(DataSource nameDataSource, int mes) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String sql = selectJdbTemplate("sum(f.valor_total) as total", "factura f", "month(f.fecha_factura) =" + mes + "  and year(curdate()) = year(f.fecha_factura)");
        PorcentajeVentas porcentajeSedes = null;
        Double total = 0D;

        try {
            total = this.jdbcTemplate.queryForObject(sql, Double.class);
        } catch (DataAccessException e) {
            System.out.println("ERROR::PorcentajeVentasDaoImpl::" + e.getMessage());
        }

        return total;
    }

    @Override
    public void insertarPorcetajeVentas(DataSource nameDataSource, PorcentajeVentas porcentajeVentas) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String sql = insertJdbTemplate("consecutivo,total,mes,fecha", "porcentaje_ventas ", porcentajeVentas.getConsecutivo() + "," + porcentajeVentas.getTotal()
                + "," + porcentajeVentas.getMes() + ",'" + Formatos.dateTostring(porcentajeVentas.getFecha()) + "'");
        List<DetallePorcentajeVentas> porcentajeSedes = null;
        try {
            this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("Error insertarPorcetajeVentas::" + e.getMessage());
        }
    }

    @Override
    public void borrarPorcentajeVentas(DataSource nameDataSource, Integer mes) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String sql = "delete from porcentaje_ventas";
        if(mes != null){
            if(mes != 0){
                sql += " where mes = "+mes; 
            }
        }
        try {
            this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("Error borrarPorcentajeVentas::" + e.getMessage());
        }
    }
}
