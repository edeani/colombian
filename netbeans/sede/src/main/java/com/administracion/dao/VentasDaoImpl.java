/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.util.Formatos;
import com.mycompany.mapper.VentasMapper;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class VentasDaoImpl implements VentasDao{

    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<VentasMapper> getVentasMesa(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();
        
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = "SELECT  'MESAS' as tipo, productos.codigo_producto,   productos.descripcion_producto,   detalle_mesa.valor_producto, SUM(detalle_mesa.numero_unidades) as numero_unidades "
                    + " FROM detalle_mesa,    mesa,    productos  "
                    + " WHERE ( mesa.numero_orden = detalle_mesa.numero_orden ) and  ( productos.codigo_producto = detalle_mesa.codigo_producto ) and    ( ( mesa.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  "
                    + " ( mesa.estado_orden = 'A' ) )    GROUP BY productos.codigo_producto,productos.descripcion_producto,   detalle_mesa.valor_producto "
                   ;
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query, new BeanPropertyRowMapper<>(VentasMapper.class));
    }

    @Override
    public List<VentasMapper> getVentasDomicilio(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();
        
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = "SELECT 'DOMICILIOS' as tipo,productos.codigo_producto,   productos.descripcion_producto,   detalle_orden.valor_producto, 	SUM(detalle_orden.numero_unidades) as numero_unidades "
                    + " FROM detalle_orden,   orden,   productos   WHERE ( orden.numero_orden = detalle_orden.numero_orden ) and  ( orden.numero_telefono = detalle_orden.numero_telefono ) and   "
                    + "( productos.codigo_producto = detalle_orden.codigo_producto ) and  ( ( orden.fecha_orden BETWEEN '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND      ( orden.estado_orden = 'A' ) )    "
                    + " GROUP BY productos.codigo_producto, "
                    + " productos.descripcion_producto, "
                    + " detalle_orden.valor_producto ";
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query, new BeanPropertyRowMapper<>(VentasMapper.class));
    }

    @Override
    public List<VentasMapper> getVentasMostrador(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();
        
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = " SELECT  'MOSTRADOR' as tipo ,productos.codigo_producto,   productos.descripcion_producto,   detalle_llevar.valor_producto, SUM(detalle_llevar.numero_unidades) as numero_unidades" +
                        " FROM detalle_llevar,   llevar,   productos   WHERE ( llevar.numero_orden = detalle_llevar.numero_orden ) and  " +
                        " ( productos.codigo_producto = detalle_llevar.codigo_producto ) and  "+
                        " ( ( llevar.fecha_orden between '"+formato.dateTostring(dfDefault.format(fi))+"' and '"+formato.dateTostring(dfDefault.format(ff))+"') AND  ( llevar.estado_orden = 'A' ) )    " +
                        " GROUP BY productos.codigo_producto, productos.descripcion_producto, "+
                        " detalle_llevar.valor_producto  ORDER BY 1, 2, 3";
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query, new BeanPropertyRowMapper<>(VentasMapper.class));
    }

    @Override
    public List<VentasMapper> getTotalVentas(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();
        
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query2 = "select tipo,codigo_producto,descripcion_producto, valor_producto,sum(numero_unidades) numero_unidades from(SELECT 'DOMICILIOS' tipo,productos.codigo_producto,   productos.descripcion_producto,   detalle_orden.valor_producto, 	SUM(detalle_orden.numero_unidades) as numero_unidades "
                    + " FROM detalle_orden,   orden,   productos   WHERE ( orden.numero_orden = detalle_orden.numero_orden ) and  ( orden.numero_telefono = detalle_orden.numero_telefono ) and   "
                    + "( productos.codigo_producto = detalle_orden.codigo_producto ) and  ( ( orden.fecha_orden BETWEEN '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND      ( orden.estado_orden = 'A' ) )    "
                    + " GROUP BY productos.codigo_producto, "
                    + " productos.descripcion_producto, "
                    + " detalle_orden.valor_producto "
                    + " UNION SELECT  'MESAS', productos.codigo_producto,   productos.descripcion_producto,   detalle_mesa.valor_producto, SUM(detalle_mesa.numero_unidades) "
                    + " FROM detalle_mesa,    mesa,    productos  "
                    + " WHERE ( mesa.numero_orden = detalle_mesa.numero_orden ) and  ( productos.codigo_producto = detalle_mesa.codigo_producto ) and    ( ( mesa.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  "
                    + " ( mesa.estado_orden = 'A' ) )    GROUP BY productos.codigo_producto,productos.descripcion_producto,   detalle_mesa.valor_producto UNION "
                    + " SELECT  'MOSTRADOR',productos.codigo_producto,   productos.descripcion_producto,   detalle_llevar.valor_producto, SUM(detalle_llevar.numero_unidades) "
                    + " FROM detalle_llevar,   llevar,   productos   WHERE ( llevar.numero_orden = detalle_llevar.numero_orden ) and  "
                    + " ( productos.codigo_producto = detalle_llevar.codigo_producto ) and  "
                    + " ( ( llevar.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  ( llevar.estado_orden = 'A' ) )    "
                    + " GROUP BY productos.codigo_producto, productos.descripcion_producto, "
                    + " detalle_llevar.valor_producto  ORDER BY 1, 2, 3)a GROUP BY codigo_producto,descripcion_producto";
        return this.jdbcTemplate.query(query2, new BeanPropertyRowMapper<>(VentasMapper.class));
    }
    
}
