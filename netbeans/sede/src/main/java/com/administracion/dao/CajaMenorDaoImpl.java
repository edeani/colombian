/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.dto.DetallePagosProveedorDto;
import com.administracion.dto.DetallePagosTercerosDto;
import com.administracion.entidad.CajaMenor;
import com.administracion.entidad.DetalleCajaMenor;
import com.administracion.util.Formatos;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class CajaMenorDaoImpl extends GenericDaoImpl<CajaMenor> implements CajaMenorDao{

    
    private JdbcTemplate jdbcTemplate;
    
    private static final Long sede_comprobante_compra = 1L;
    
    @Override
    public void guardarPagosCajaMenor(DataSource nameDataSource, CajaMenor pagosTerceros) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbcTemplate.execute(insertJdbTemplate("idcajamenor,idbeneficiario,total,fecha", "caja_menor",
                    pagosTerceros.getIdcajamenor() + ",'" + pagosTerceros.getIdbeneficiario() + "',"
                    + pagosTerceros.getTotal() + ",'" + Formatos.dateTostring(pagosTerceros.getFecha()) + "'"));
        } catch (DataAccessException e) {
            System.out.println("Error guardarPagosCajaMenor::" + e.getMessage());
        }
    }
    
    @Override
    public void guardarDetallePagosTercerosCajaMenor(DataSource nameDataSource, DetalleCajaMenor detalleCajaMenor) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbcTemplate.execute(insertJdbTemplate("idcajamenor,idcuenta,fecha,descripcion,numero,idsede,total", "detalle_caja_menor",
                    detalleCajaMenor.getIdcajamenor() + "," + detalleCajaMenor.getIdcuenta() + ",'" + Formatos.dateTostring(detalleCajaMenor.getFecha())
                    + "','" + detalleCajaMenor.getDescripcion() + "'," + detalleCajaMenor.getNumero() + ","
                    + detalleCajaMenor.getIdsede() + "," + detalleCajaMenor.getTotal()));

        } catch (DataAccessException e) {
            System.out.println("Error guardarDetallePagosTercerosCajaMenor::" + e.getMessage());
        }
    }

    @Override
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosCajaMenorDtos(DataSource nameDataSource, Long idpagotercero) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<DetallePagosTercerosDto> detalle = null;
        String sql = "select dpt.idcuenta,dpt.total,trim(cp.nombre_cta) as conceptoCuenta,dpt.descripcion as detalle, "
                + "dpt.numero,date_format(dpt.fecha,'%Y-%m-%d') as fecha, "
                + "dpt.idcajamenor as idpagotercero,s.idsedes as idsede, s.sede as nombreSede from detalle_caja_menor dpt "
                + "inner join cuentas_puc cp on cp.cod_cta = dpt.idcuenta "
                + "inner join sedes s on s.idsedes = dpt.idsede "
                + "where dpt.idcajamenor =" + idpagotercero;

        try {
            detalle = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DetallePagosTercerosDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarDetallePagosTercerosCajaMenorDtos::" + e.getMessage());
        }

        return detalle;
    }

    @Override
    public CajaMenor buscarPagoXIdPagoCajaMenor(DataSource nameDataSource, Long idpago) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String sql = "select * from caja_menor where idcajamenor = " + idpago;
        CajaMenor pagos = null;

        try {
            pagos = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(CajaMenor.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarPagoXIdPagoCajaMenor::" + e.getMessage());
        }
        return pagos;
    }
    
    @Override
    public void guardarDetallePagosProveedorCajaMenor(DataSource nameDataSource, DetalleCajaMenor detalleCajaMenor) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbcTemplate.execute(insertJdbTemplate("idcajamenor,idcuenta,fecha,descripcion,numero,total,fecha_vencimiento,numero_compra,idsede", "detalle_caja_menor",
                    detalleCajaMenor.getIdcajamenor() + "," + detalleCajaMenor.getIdcuenta() + ",'" + Formatos.dateTostring(detalleCajaMenor.getFecha())
                    + "','" + detalleCajaMenor.getDescripcion() + "'," + detalleCajaMenor.getNumero()
                    + "," + detalleCajaMenor.getTotal() + ",'" + Formatos.dateTostring(detalleCajaMenor.getFechaVencimiento())
                    + "'," + detalleCajaMenor.getNumeroCompra() + "," + sede_comprobante_compra));
        } catch (DataAccessException e) {
            System.out.println("Error guardarDetallePagosProveedorCajaMenor::" + e.getMessage());
        }
    }

    @Override
    public List<DetallePagosProveedorDto> buscarDetallePagosProveedorCajaMenorDtos(DataSource nameDataSource, Long idpago) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<DetallePagosProveedorDto> detalle = null;
        String sql = "select dpp.idcuenta,dpp.total,trim(cp.nombre_cta) as conceptoCuenta,dpp.descripcion as detalle, "
                + "dpp.numero,date_format(dpp.fecha,'%Y-%m-%d') as fecha, "
                + "dpp.idcajamenor as idpagoproveedor,"
                + "dpp.numero_compra as numeroCompra,dpp.fecha_vencimiento as fechaVencimiento,s.sede as nombreSede from detalle_caja_menor dpp "
                + "inner join sedes s on s.idsedes = dpp.idsede "
                + "inner join cuentas_puc cp on cp.cod_cta = dpp.idcuenta "
                + "where dpp.idcajamenor =" + idpago;

        try {
            detalle = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DetallePagosProveedorDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarDetallePagosProveedorDtos::" + e.getMessage());
        }

        return detalle;
    }

}
