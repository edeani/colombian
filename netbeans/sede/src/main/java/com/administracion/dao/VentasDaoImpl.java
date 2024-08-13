/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.adiministracion.rowmapper.VentasMapperRowMapper;
import com.administracion.util.Formatos;
import com.mycompany.mapper.VentasMapper;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class VentasDaoImpl implements VentasDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<VentasMapper> getVentasMesa(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();

        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = "select * from(SELECT  'MESAS' as tipo, productos.codigo_producto,   productos.descripcion_producto,   detalle_mesa.valor_producto, SUM(detalle_mesa.numero_unidades) as numero_unidades "
                + " FROM detalle_mesa,    mesa,    productos  "
                + " WHERE ( mesa.numero_orden = detalle_mesa.numero_orden ) and  ( productos.codigo_producto = detalle_mesa.codigo_producto ) and    ( ( mesa.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  "
                + " ( mesa.estado_orden = 'A' ) )    GROUP BY productos.codigo_producto,productos.descripcion_producto,   detalle_mesa.valor_producto)subc order by subc.codigo_producto";

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query,new VentasMapperRowMapper());
    }

    @Override
    public List<VentasMapper> getVentasDomicilio(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();

        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = "select * from (SELECT 'DOMICILIOS' as tipo,productos.codigo_producto,   productos.descripcion_producto,   detalle_orden.valor_producto, 	SUM(detalle_orden.numero_unidades) as numero_unidades "
                + " FROM detalle_orden,   orden,   productos   WHERE ( orden.numero_orden = detalle_orden.numero_orden ) and  ( orden.numero_telefono = detalle_orden.numero_telefono ) and   "
                + "( productos.codigo_producto = detalle_orden.codigo_producto ) and  ( ( orden.fecha_orden BETWEEN '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND      ( orden.estado_orden = 'A' ) )    "
                + " GROUP BY productos.codigo_producto, "
                + " productos.descripcion_producto, "
                + " detalle_orden.valor_producto)subdDom order by  subdDom.codigo_producto";

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query,new VentasMapperRowMapper());
    }

    @Override
    public List<VentasMapper> getVentasMostrador(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();

        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = "select * from (select a.codigo_producto,a.descripcion_producto,a.valor_producto , sum(a.numero_unidades) as numero_unidades "
                + " from( "
                + " select  'MOSTRADOR' as tipo ,p.codigo_producto,   p.descripcion_producto,    "
                + " dl.valor_producto, dl.numero_unidades "
                + " from llevar ll "
                + " inner join detalle_llevar dl on dl.numero_orden = ll.numero_orden "
                + " inner join productos p on p.codigo_producto=dl.codigo_producto "
                + " where ll.fecha_orden between '"+formato.dateTostring(dfDefault.format(fi))+"' and '"+formato.dateTostring(dfDefault.format(ff))+"' "
                + " and ll.estado_orden = 'A' "
                + " )a "
                + " group by a.codigo_producto,a.descripcion_producto,a.valor_producto) subcMostrador order by  subcMostrador.codigo_producto";
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query, new VentasMapperRowMapper());
    }

    @Override
    public List<VentasMapper> getTotalVentas(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();

        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query2 = "select * from( select 'totales' as tipo, codigo_producto,descripcion_producto, valor_producto,sum(numero_unidades) as numero_unidades,sum(total) as total_producto from(SELECT 'DOMICILIOS' as tipo,productos.codigo_producto,   productos.descripcion_producto,   detalle_orden.valor_producto,detalle_orden.numero_unidades, detalle_orden.numero_unidades * detalle_orden.valor_producto as total "
                + " FROM detalle_orden,   orden,   productos   WHERE ( orden.numero_orden = detalle_orden.numero_orden ) and  ( orden.numero_telefono = detalle_orden.numero_telefono ) and   "
                + "( productos.codigo_producto = detalle_orden.codigo_producto ) and  ( ( orden.fecha_orden BETWEEN '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND      ( orden.estado_orden = 'A' ) )    "
                + " UNION ALL SELECT  'MESAS' as tipo, productos.codigo_producto,   productos.descripcion_producto,   detalle_mesa.valor_producto, detalle_mesa.numero_unidades ,detalle_mesa.numero_unidades * detalle_mesa.valor_producto as total "
                + " FROM detalle_mesa,    mesa,    productos  "
                + " WHERE ( mesa.numero_orden = detalle_mesa.numero_orden ) and  ( productos.codigo_producto = detalle_mesa.codigo_producto ) and    ( ( mesa.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  "
                + " ( mesa.estado_orden = 'A' ) ) "
                + " UNION ALL "
                + " SELECT  'MOSTRADOR' as tipo,productos.codigo_producto,   productos.descripcion_producto,   detalle_llevar.valor_producto, detalle_llevar.numero_unidades,detalle_llevar.numero_unidades * detalle_llevar.valor_producto as total "
                + " FROM detalle_llevar,   llevar,   productos   WHERE ( llevar.numero_orden = detalle_llevar.numero_orden ) and  "
                + " ( productos.codigo_producto = detalle_llevar.codigo_producto ) and  "
                + " ( ( llevar.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  ( llevar.estado_orden = 'A' ) )    "
                + " )a GROUP BY codigo_producto,descripcion_producto,valor_producto) subcTotal order by subcTotal.codigo_producto";
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query2, new VentasMapperRowMapper());
    }

    @Override
    public List<VentasMapper> getVentasMesaTotalCalc(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();

        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = "select *,subc.numero_unidades*subc.valor_producto as total_producto from(SELECT  'MESAS' as tipo, productos.codigo_producto,   productos.descripcion_producto,   detalle_mesa.valor_producto, SUM(detalle_mesa.numero_unidades) as numero_unidades "
                + " FROM detalle_mesa,    mesa,    productos  "
                + " WHERE ( mesa.numero_orden = detalle_mesa.numero_orden ) and  ( productos.codigo_producto = detalle_mesa.codigo_producto ) and    ( ( mesa.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND  "
                + " ( mesa.estado_orden = 'A' ) )    GROUP BY productos.codigo_producto,productos.descripcion_producto,   detalle_mesa.valor_producto)subc order by subc.codigo_producto";

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query,new VentasMapperRowMapper());
    }

    @Override
    public List<VentasMapper> getVentasDomicilioTotalCalc(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();

        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = "select *,subdDom.numero_unidades*subdDom.valor_producto as total_producto from (SELECT 'DOMICILIOS' as tipo,productos.codigo_producto,   productos.descripcion_producto,   detalle_orden.valor_producto, 	SUM(detalle_orden.numero_unidades) as numero_unidades "
                + " FROM detalle_orden,   orden,   productos   WHERE ( orden.numero_orden = detalle_orden.numero_orden ) and  ( orden.numero_telefono = detalle_orden.numero_telefono ) and   "
                + "( productos.codigo_producto = detalle_orden.codigo_producto ) and  ( ( orden.fecha_orden BETWEEN '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "') AND      ( orden.estado_orden = 'A' ) )    "
                + " GROUP BY productos.codigo_producto, "
                + " productos.descripcion_producto, "
                + " detalle_orden.valor_producto)subdDom order by  subdDom.codigo_producto";

        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query,new VentasMapperRowMapper());
    }

    @Override
    public List<VentasMapper> getVentasMostradorTotalCalc(DataSource dataSource, Date fi, Date ff) {
        Formatos formato = new Formatos();

        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = "select *,subcMostrador.numero_unidades*subcMostrador.valor_producto as total_producto from (select a.codigo_producto,a.descripcion_producto,a.valor_producto , sum(a.numero_unidades) as numero_unidades "
                + " from( "
                + " select  'MOSTRADOR' as tipo ,p.codigo_producto,   p.descripcion_producto,    "
                + " dl.valor_producto, dl.numero_unidades "
                + " from llevar ll "
                + " inner join detalle_llevar dl on dl.numero_orden = ll.numero_orden "
                + " inner join productos p on p.codigo_producto=dl.codigo_producto "
                + " where ll.fecha_orden between '"+formato.dateTostring(dfDefault.format(fi))+"' and '"+formato.dateTostring(dfDefault.format(ff))+"' "
                + " and ll.estado_orden = 'A' "
                + " )a "
                + " group by a.codigo_producto,a.descripcion_producto,a.valor_producto) subcMostrador order by  subcMostrador.codigo_producto";
        
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        return this.jdbcTemplate.query(query, new VentasMapperRowMapper());
    }

}
