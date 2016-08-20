/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.entidades.DetallePorcentajeVentas;
import java.util.List;
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
public class DetallePorcentajeVentasDaoImpl implements DetallePorcentajeVentasDao{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CaliycaliDao caliyCaliDao;
    @Autowired
    private ProjectsDao projectsDao;
    @Override
    public List<DetallePorcentajeVentas> generarDetallePorcentajeVentas(String nameDataSource, int mes) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        String sql = caliyCaliDao.selectJdbTemplate("f.idsede,sum(f.valor_total) as total", "factura f", "month(f.fecha_factura) ="+mes+"  and year(curdate()) = year(f.fecha_factura) group by f.idsede");
        List<DetallePorcentajeVentas> porcentajeSedes = null;
        try {
           porcentajeSedes = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<DetallePorcentajeVentas>(DetallePorcentajeVentas.class));
        } catch (DataAccessException e) {
            System.out.println("Error generarDetallePorcentajeVentas::"+e.getMessage());
        }
        return porcentajeSedes;
    }

    /**
     * Borra los detalles de porcentaje si le pasan un mes - sino se le pasa al mes se borra todo.
     * @param nameDatasource
     * @param mes 
     */
    @Override
    public void borrarDetallePorcentajeVentasAll(String nameDatasource) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        String sql = "delete from detalle_porcentaje_ventas";

        try {
           this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("Error borrarDetallePorcentajeVentasAll::"+e.getMessage());
        }
    }

    @Override
    public void insertarDetallePorcentajeVenta(String nameDataSource, DetallePorcentajeVentas detallePorcentajeVentas) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        String sql = caliyCaliDao.insertJdbTemplate("idsede,total,porcentaje_venta,idporcentajeventa", "detalle_porcentaje_ventas", detallePorcentajeVentas.getIdsede()+","+detallePorcentajeVentas.getTotal()
        +","+detallePorcentajeVentas.getPorcentajeVenta()+","+detallePorcentajeVentas.getIdporcentajeventa());
        List<DetallePorcentajeVentas> porcentajeSedes = null;
        try {
           this.jdbcTemplate.execute(sql);
        } catch (DataAccessException e) {
            System.out.println("Error insertarDetallePorcentajeVenta::"+e.getMessage());
        }
    }
    
}
