/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.entidades.DetallePorcentajeVentas;
import com.colombian.cali.colombiancaliycali.entidades.PorcentajeVentas;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class PorcentajeVentasDaoImpl implements PorcentajeVentasDao {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CaliycaliDao caliyCaliDao;
    @Autowired
    private ProjectsDao projectsDao;

    @Override
    public Double PorcentajeVentasDaoImpl(String nameDataSource, int mes) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        String sql = caliyCaliDao.selectJdbTemplate("sum(f.valor_total) as total", "factura f", "month(f.fecha_factura) =" + mes + "  and year(curdate()) = year(f.fecha_factura)");
        PorcentajeVentas porcentajeSedes = null;
        Double total = 0D;

        try {
            total = this.jdbcTemplate.queryForObject(sql, Double.class);
        } catch (Exception e) {
            System.out.println("ERROR::PorcentajeVentasDaoImpl::" + e.getMessage());
        }

        return total;
    }

    @Override
    public void insertarPorcetajeVentas(String nameDataSource, PorcentajeVentas porcentajeVentas) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        String sql = caliyCaliDao.insertJdbTemplate("consecutivo,total,mes,fecha", "porcentaje_ventas ", porcentajeVentas.getConsecutivo() + "," + porcentajeVentas.getTotal()
                + "," + porcentajeVentas.getMes() + ",'" + Formatos.dateTostring(porcentajeVentas.getFecha()) + "'");
        List<DetallePorcentajeVentas> porcentajeSedes = null;
        try {
            this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("Error insertarPorcetajeVentas::" + e.getMessage());
        }
    }

    @Override
    public void borrarPorcentajeVentas(String nameDataSource, Integer mes) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
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
