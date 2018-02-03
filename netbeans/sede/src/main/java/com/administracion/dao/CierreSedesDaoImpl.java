/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;


import com.administracion.dto.CierreSedesDto;
import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.ReporteComprobanteCierreDto;
import com.administracion.dto.ReporteTotalCuentasXNivelDto;
import com.administracion.entidad.CierreSedes;
import com.administracion.util.Formatos;
import com.administracion.util.LectorPropiedades;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class CierreSedesDaoImpl extends GenericDaoImpl<CierreSedes> implements CierreSedesDao {

    @Autowired
    private LectorPropiedades lectorPropiedades;

    private JdbcTemplate jdbcTemplate;

    @Override
    public void guardarDetalleComprobanteCierre(DataSource nameDataSource, ComprobanteConsolidadoSedeDto comprobanteConsolidadoSedeDto) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        try {
            this.jdbcTemplate.execute(insertJdbTemplate("idsede,idcuenta,total,concepto,fecha,fecha_comprobante,idComprobanteCierre", "detalle_cierre_sedes", comprobanteConsolidadoSedeDto.getIdSede() + ",'" + comprobanteConsolidadoSedeDto.getIdCuenta() + "',"
                    + comprobanteConsolidadoSedeDto.getTotal() + ",'" + comprobanteConsolidadoSedeDto.getConcepto() + "','"
                    + comprobanteConsolidadoSedeDto.getFecha() + "','" + comprobanteConsolidadoSedeDto.getFechaComprobante() + "',"
                    + comprobanteConsolidadoSedeDto.getIdComprobante()));
        } catch (DataAccessException e) {
            System.out.println("Error guardarDetalleComprobanteCierre::" + e.getMessage());
        }
    }

    @Override
    public void guardarComprobanteCierre(DataSource nameDataSource, CierreSedes cierreSedes) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        try {
            if (cierreSedes.getConsecutivo() == null) {
                this.jdbcTemplate.execute(insertJdbTemplate("idsede,totalhaber,totaldeber,fecha,fecha_comprobante", "cierre_sedes", cierreSedes.getIdsede() + "," + cierreSedes.getTotalhaber() + ","
                        + cierreSedes.getTotaldeber() + ",'" + Formatos.dateTostring(cierreSedes.getFecha()) + "','"
                        + Formatos.dateTostring(cierreSedes.getFechaComprobante()) + "'"));
            }else{
                this.jdbcTemplate.execute(insertJdbTemplate("consecutivo,idsede,totalhaber,totaldeber,fecha,fecha_comprobante", "cierre_sedes",cierreSedes.getConsecutivo()+","+cierreSedes.getIdsede() + "," + cierreSedes.getTotalhaber() + ","
                        + cierreSedes.getTotaldeber() + ",'" + Formatos.dateTostring(cierreSedes.getFecha()) + "','"
                        + Formatos.dateTostring(cierreSedes.getFechaComprobante()) + "'"));
            }
        } catch (DataAccessException e) {
            System.out.println("Error guardarComprobanteCierre::" + e.getMessage());
        }
    }

    @Override
    public List<ReporteComprobanteCierreDto> buscarDetalleComprobanteCierreSedesView(DataSource nameDataSource, Long idComprobanteCierre) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<ReporteComprobanteCierreDto> reporte = null;
        try {
            String sql = "select idcuenta,concepto,case when idcuenta = '414015' then 0 else total end as deber,case when idcuenta = '414015' "
                    + " then total else 0 end as haber from detalle_cierre_sedes where idcomprobantecierre = " + idComprobanteCierre;
            reporte = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ReporteComprobanteCierreDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarDetalleComprobanteView::" + e.getMessage());
        }

        return reporte;
    }

    @Override
    public CierreSedesDto buscarComprobanteCierreDto(DataSource nameDataSource, Long idComprobanteCierre) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        CierreSedesDto cierreSedesDto = new CierreSedesDto();
        try {
            String query = "select cs.*,s.sede "
                    + "from cierre_sedes cs "
                    + "inner join sedes s on s.idsedes = cs.idsede "
                    + "where consecutivo =" + idComprobanteCierre;
            cierreSedesDto = this.jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(CierreSedesDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarComprobanteCierreDto::" + e.getMessage());
        }
        return cierreSedesDto;
    }

    @Override
    public List<CierreSedesDto> buscarComprobanteCierreDtoXFecha(DataSource nameDataSource, String fechaInicial, String fechaFinal) {
            this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<CierreSedesDto> cierreSedesDto = null;
        try {
            String query = "select cs.*,s.sede "
                    + "from cierre_sedes cs "
                    + "inner join sedes s on s.idsedes = cs.idsede "
                    + "where fecha between '" + fechaInicial + "' and '" + fechaFinal + "'";
            cierreSedesDto = this.jdbcTemplate.query(query, new BeanPropertyRowMapper<>(CierreSedesDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarComprobanteCierreDto::" + e.getMessage());
        }
        return cierreSedesDto;
    }

    @Override
    public List<ComprobanteConsolidadoSedeDto> buscarConsignacionesXFecha(DataSource nameDataSource, String fechaInicial, String fechaFinal) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<ComprobanteConsolidadoSedeDto> consignaciones = null;
        try {
            consignaciones = this.jdbcTemplate.query(selectJdbTemplate("*",
                    "detalle_cierre_sedes", "fecha_comprobante between '" + fechaInicial + "' and '" + fechaFinal + "' and idcuenta='11050501'"), new BeanPropertyRowMapper<>(ComprobanteConsolidadoSedeDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarConsignacionesXFecha::" + e.getMessage());
        }
        return consignaciones;
    }

    @Override
    public ReporteTotalCuentasXNivelDto totalCierreCuentaXNivel(DataSource nameDataSource, int tipoCuenta
            , String fechaInicial, String fechaFinal) {
        
        lectorPropiedades.setPropiedad("niveles_contabilidad");
        String caracteresxnivel = lectorPropiedades.leerPropiedad();
        String longitud[] = caracteresxnivel.split("-");
        ReporteTotalCuentasXNivelDto totalNivel = null;
        String sql = "select * "
                + "from ("
                + "select sub8.nivel5, sub8.nivel4, sub8.nivel3 , sub8.nivel2, case when sub8.nivel1 is null then 0 else sub8.nivel1 end as nivel1 "
                + "from("
                + "select sub7.*, (select sum(dcs.total) as totalNivel from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '" + tipoCuenta + "%' and character_length(dcs.idcuenta) = " + longitud[0] + "  and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel1 "
                + "from ("
                + "select sub6.nivel5, sub6.nivel4,  sub6.nivel3,case when  sub6.nivel2 is null then 0 else  sub6.nivel2 end as nivel2 "
                + "from("
                + "select sub5.*, (select sum(dcs.total) as totalNivel from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '" + tipoCuenta + "%' and character_length(dcs.idcuenta) = " + longitud[1] + "  and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel2 "
                + "from ( "
                + "select sub4.nivel5,sub4.nivel4,case when sub4.nivel3 is null then 0 else sub4.nivel3 end as nivel3 from( "
                + "select sub3.*,(select sum(dcs.total) as totalNivel from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '" + tipoCuenta + "%' and character_length(dcs.idcuenta) = " + longitud[2] + "  and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel3 "
                + "from( "
                + "select sub2.nivel5,case when sub2.nivel4 is null then 0 else sub2.nivel4 end as nivel4  "
                + "from( "
                + "select sub1.*,(select sum(dcs.total) as totalNivel from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '" + tipoCuenta + "%' and character_length(dcs.idcuenta) = " + longitud[3] + "  and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "')  as nivel4 "
                + "from ( "
                + "select case when sub0.nivel5 is null then 0 else sub0.nivel5 end as nivel5 "
                + "from( "
                + "select (select sum(dcs.total) as totalNivel from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '" + tipoCuenta + "%' and character_length(dcs.idcuenta) = " + longitud[4] + "  and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel5 from dual limit 1 "
                + ") sub0 ) sub1 ) sub2 ) sub3 ) sub4 ) sub5 ) sub6 ) sub7 ) sub8 ) sub9 ";
        try {
            this.jdbcTemplate = new JdbcTemplate(nameDataSource);
            totalNivel = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ReporteTotalCuentasXNivelDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error totalCierreCuentaXNivel::" + e.getMessage());
        }

        return totalNivel;
    }

    @Override
    public ReporteTotalCuentasXNivelDto totalCierreCuentaXNivelSede(DataSource nameDataSource, Long idsede, int tipoCuenta, String fechaInicial, String fechaFinal) {
        lectorPropiedades.setPropiedad("niveles_contabilidad");
        String caracteresxnivel = lectorPropiedades.leerPropiedad();
        String longitud[] = caracteresxnivel.split("-");
        //Integer longitud = obtenerLongitudNivelCuenta(caracteresxnivel, nivel);
        ReporteTotalCuentasXNivelDto totalNivel = null;

        String sql = "select case when sub8.idsede is null then " + idsede + " else sub8.idsede end as idsede, case when sub8.totalNivel is null then 0 else sub8.totalNivel end as nivel5,sub8.nivel1,sub8.nivel2,sub8.nivel3,sub8.nivel4 "
                + "from( "
                + "select dp4.idsede,sum(dp4.total) as totalNivel, sub7.nivel1,sub7.nivel2,sub7.nivel3,sub7.nivel4 "
                + "from detalle_cierre_sedes dp4 "
                + "inner join ( "
                + "select case when sub6.idsede is null then " + idsede + " else sub6.idsede end as idsede, case when sub6.totalNivel is null then 0 else sub6.totalNivel end as nivel4,sub6.nivel1,sub6.nivel2,sub6.nivel3 "
                + "from( "
                + "select dp3.idsede,sum(dp3.total) as totalNivel,sub5.nivel1,sub5.nivel2,sub5.nivel3 "
                + "from detalle_cierre_sedes dp3 "
                + "inner join ( "
                + "select case when sub4.idsede is null then " + idsede + " else sub4.idsede end as idsede, case when sub4.totalNivel is null then 0 else sub4.totalNivel end as nivel3,sub4.nivel1,sub4.nivel2 "
                + "from( "
                + "select dp2.idsede,sum(dp2.total) as totalNivel,sub3.nivel2,sub3.nivel1 "
                + "from detalle_cierre_sedes dp2 "
                + "inner join ( "
                + "select case when sub2.idsede is null then " + idsede + " else sub2.idsede end as idsede, case when sub2.totalNivel is null then 0 else sub2.totalNivel end as nivel2,sub2.nivel1 "
                + "from( "
                + "select dp1.idsede,sum(dp1.total) as totalNivel,sub1.nivel1 "
                + "from detalle_cierre_sedes dp1 "
                + "inner join ( "
                + "select case when sub0.idsede is null then " + idsede + " else sub0.idsede end as idsede,case when sub0.totalNivel is null then 0 else sub0.totalNivel  end as nivel1 "
                + "from( "
                + "select dcs.idsede,sum(dcs.total) as totalNivel from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '" + tipoCuenta + "%' and character_length(dcs.idcuenta) = " + longitud[0] + "  and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "and dcs.idsede = " + idsede + " "
                + ") sub0 "
                + ") sub1 on sub1.idsede = dp1.idsede "
                + "where dp1.idcuenta like '" + tipoCuenta + "%' and character_length(dp1.idcuenta) = " + longitud[1] + "  and dp1.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "and dp1.idsede = " + idsede + " "
                + ") sub2 "
                + ") sub3 on sub3.idsede = dp2.idsede "
                + "where dp2.idcuenta like '" + tipoCuenta + "%' and character_length(dp2.idcuenta) = " + longitud[2] + "  and dp2.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "and dp2.idsede = " + idsede + ""
                + ") sub4 "
                + ") sub5 on sub5.idsede = dp3.idsede "
                + "where dp3.idcuenta like '" + tipoCuenta + "%' and character_length(dp3.idcuenta) = " + longitud[3] + "  and dp3.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "and dp3.idsede = " + idsede + " "
                + ") sub6 "
                + ") sub7 on sub7.idsede = dp4.idsede "
                + "where dp4.idcuenta like '" + tipoCuenta + "%' and character_length(dp4.idcuenta) = " + longitud[4] + "  and dp4.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "and dp4.idsede = " + idsede + " "
                + ") sub8 ";
        System.out.println("CONSULTA PRINT::" + sql);
        try {
            this.jdbcTemplate = new JdbcTemplate(nameDataSource);
            totalNivel = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ReporteTotalCuentasXNivelDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error totalCierreCuentaXNivel::" + e.getMessage());
        }

        return totalNivel;
    }

    @Override
    public void borrarComprobanteCierre(DataSource nameDataSource, Long idComprobanteCierre) {
        try {
            this.jdbcTemplate = new JdbcTemplate(nameDataSource);
            this.jdbcTemplate.execute(deleteJdbTemplate("cierre_sedes", "consecutivo=" + idComprobanteCierre));
        } catch (DataAccessException e) {
            System.out.println("Error borrarComprobanteCierre::" + e.getMessage());
        }
    }

    @Override
    public void borrarDetalleComprobanteCierre(DataSource nameDataSource, Long idComprobanteCierre) {
        try {
            this.jdbcTemplate = new JdbcTemplate(nameDataSource);
            this.jdbcTemplate.execute(deleteJdbTemplate("detalle_cierre_sedes", "idcomprobantecierre=" + idComprobanteCierre));
        } catch (DataAccessException e) {
            System.out.println("Error borrarDetalleComprobanteCierre::" + e.getMessage());
        }
    }

    @Override
    public CierreSedes buscarCabeceraComprobanteCierreXFechaXSede(DataSource nameDataSource, String fechaInicial, String fechaFinal, Long idSede) {
        CierreSedes cierreSedes = null;

        try {
            this.jdbcTemplate = new JdbcTemplate(nameDataSource);
            cierreSedes = this.jdbcTemplate.queryForObject(selectJdbTemplate("*", "cierre_sedes", "idsede=" + idSede + " and fecha between '" + fechaInicial + "' and '" + fechaFinal + "'"), new BeanPropertyRowMapper<>(CierreSedes.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarCabeceraComprobanteCierreXFecha::" + e.getMessage());
        }

        return cierreSedes;
    }

    @Override
    public List<CierreSedesDto> reporteComprobanteCierreSedesView(DataSource nameDataSource, String fechaInicial, String fechaFinal, Long idsede) {
       this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<CierreSedesDto> reporte = null;
        try {
            String sql = "select cs.consecutivo,dcs.idcuenta,dcs.concepto,case when dcs.idcuenta = '414015' then 0 else dcs.total end as totaldeber,case when dcs.idcuenta = '414015' " +
             "then dcs.total else 0 end as totalhaber,cs.fecha from " +
            "cierre_sedes cs " +
            "inner join detalle_cierre_sedes dcs on dcs.idcomprobantecierre = cs.consecutivo " +
            "where cs.fecha between '"+fechaInicial+"' and '"+fechaFinal+"'   " +
            "and cs.idsede = "+idsede+" order by cs.fecha,consecutivo";
            reporte = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CierreSedesDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarDetalleComprobanteView::" + e.getMessage());
        }

        return reporte;  
    }
    
 
}
