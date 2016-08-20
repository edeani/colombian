/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.DataGenericDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosProveedorDto;
import com.colombian.cali.colombiancaliycali.dto.DetallePagosTercerosDto;
import com.colombian.cali.colombiancaliycali.dto.PagosCabeceraDto;
import com.colombian.cali.colombiancaliycali.dto.ReportePagosDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteTotalCuentasXNivelDto;
import com.colombian.cali.colombiancaliycali.entidades.DetallePagos;
import com.colombian.cali.colombiancaliycali.entidades.Pagos;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class PagosDaoImpl extends DataGenericDao implements PagosDao {

    @Autowired
    private ProjectsDao projectsDao;
    
    private static final Long sede_comprobante_compra = 1L;

    private JdbcTemplate jdbcTemplate;

    @Override
    public Long secuenciaPagos(String nameDataSource) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        Long secuencia = 0L;
        try {
            secuencia = this.jdbcTemplate.queryForLong("SELECT Auto_increment FROM information_schema.tables WHERE table_name='pagos' "
                    + "and table_schema = '" + projectsDao.getDatasource(nameDataSource).getConnection().getCatalog() + "'");
        } catch (DataAccessException e) {
            System.out.println("Error secuenciaPagosTerceros::" + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(PagosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return secuencia;
    }

    @Override
    public void guardarPagos(String nameDataSource, Pagos pagosTerceros) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbcTemplate.execute(caliycaliDao.insertJdbTemplate("idpagos,idbeneficiario,total,fecha", "pagos",
                    pagosTerceros.getIdpagos() + ",'" + pagosTerceros.getIdbeneficiario() + "',"
                    + pagosTerceros.getTotal() + ",'" + Formatos.dateTostring(pagosTerceros.getFecha()) + "'"));
        } catch (DataAccessException e) {
            System.out.println("Error guardarPagosTerceros::" + e.getMessage());
        }
    }

    @Override
    public void guardarDetallePagosTerceros(String nameDataSource, DetallePagos detallePagosTerceros) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbcTemplate.execute(caliycaliDao.insertJdbTemplate("idpago,idcuenta,fecha,descripcion,numero,idsede,total", "detalle_pagos",
                    detallePagosTerceros.getIdpago() + "," + detallePagosTerceros.getIdcuenta() + ",'" + Formatos.dateTostring(detallePagosTerceros.getFecha())
                    + "','" + detallePagosTerceros.getDescripcion() + "'," + detallePagosTerceros.getNumero() + ","
                    + detallePagosTerceros.getIdSede() + "," + detallePagosTerceros.getTotal()));

        } catch (DataAccessException e) {
            System.out.println("Error guardarDetallePagosTerceros::" + e.getMessage());
        }
    }

    @Override
    public List<DetallePagosTercerosDto> buscarDetallePagosTercerosDtos(String nameDataSource, Long idpagotercero) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<DetallePagosTercerosDto> detalle = null;
        String sql = "select dpt.idcuenta,dpt.total,trim(cp.nombre_cta) as conceptoCuenta,dpt.descripcion as detalle, "
                + "dpt.numero,date_format(dpt.fecha,'%Y-%m-%d') as fecha, "
                + "dpt.idpago as idpagotercero,s.idsedes as idsede, s.sede as nombreSede from detalle_pagos dpt "
                + "inner join cuentas_puc cp on cp.cod_cta = dpt.idcuenta "
                + "inner join sedes s on s.idsedes = dpt.idsede "
                + "where dpt.idpago =" + idpagotercero;

        try {
            detalle = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<DetallePagosTercerosDto>(DetallePagosTercerosDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarDetallePagosTercerosDtos::" + e.getMessage());
        }

        return detalle;
    }

    @Override
    public Pagos buscarPagoXIdPago(String nameDataSource, Long idpagotercero) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        String sql = "select * from pagos where idpagos = " + idpagotercero;
        Pagos pagosTerceros = null;

        try {
            pagosTerceros = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Pagos>(Pagos.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarPagoXIdPago::" + e.getMessage());
        }
        return pagosTerceros;
    }

    @Override
    public List<DetallePagosProveedorDto> buscarDetallePagosDtos(String nameDataSource, Long idpagoproveedor) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<DetallePagosProveedorDto> detalle = null;
        String sql = "select dpp.idcuenta,dpp.total,trim(cp.nombre_cta) as conceptoCuenta,dpp.descripcion as detalle, "
                + "dpp.numero,date_format(dpp.fecha,'%Y-%m-%d') as fecha, "
                + "dpp.idpago as idpagoproveedor,"
                + "dpp.numero_compra as numeroCompra,dpp.fecha_vencimiento as fechaVencimiento,s.sede as nombreSede from detalle_pagos dpp "
                + "inner join sedes s on s.idsedes = dpp.idsede "
                + "inner join cuentas_puc cp on cp.cod_cta = dpp.idcuenta "
                + "where dpp.idpago =" + idpagoproveedor;

        try {
            detalle = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<DetallePagosProveedorDto>(DetallePagosProveedorDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarDetallePagosProveedorDtos::" + e.getMessage());
        }

        return detalle;
    }

    @Override
    public void guardarDetallePagosProveedor(String nameDataSource, DetallePagos detallePagosProveedor) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbcTemplate.execute(caliycaliDao.insertJdbTemplate("idpago,idcuenta,fecha,descripcion,numero,total,fecha_vencimiento,numero_compra,idsede", "detalle_pagos",
                    detallePagosProveedor.getIdpago() + "," + detallePagosProveedor.getIdcuenta() + ",'" + Formatos.dateTostring(detallePagosProveedor.getFecha())
                    + "','" + detallePagosProveedor.getDescripcion() + "'," + detallePagosProveedor.getNumero()
                    + "," + detallePagosProveedor.getTotal() + ",'" + Formatos.dateTostring(detallePagosProveedor.getFechaVencimiento())
                    + "'," + detallePagosProveedor.getNumeroCompra() + "," + sede_comprobante_compra));
        } catch (DataAccessException e) {
            System.out.println("Error guardarDetallePagosProveedor::" + e.getMessage());
        }
    }

    @Override
    public List<ComprobanteConsolidadoSedeDto> buscarPagosXFecha(String nameDataSource, String fechaInicial, String fechaFinal) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ComprobanteConsolidadoSedeDto> pagos = null;
        try {
            pagos = this.jdbcTemplate.query(caliycaliDao.selectJdbTemplate("*,descripcion as concepto",
                    "detalle_pagos", "fecha between '" + fechaInicial + "' and '" + fechaFinal + "'"), new BeanPropertyRowMapper<ComprobanteConsolidadoSedeDto>(ComprobanteConsolidadoSedeDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarPagosXFecha::" + e.getMessage());
        }
        return pagos;
    }

    @Override
    public ReporteTotalCuentasXNivelDto totalPagoCuentaXNivel(String nameDataSource, int tipoCuenta, String fechaInicial, String fechaFinal) {

        getPropiedades().setPropiedad("niveles_contabilidad");
        String caracteresxnivel = getPropiedades().leerPropiedad();
        String longitud[] = caracteresxnivel.split("-");
        //Integer longitud = obtenerLongitudNivelCuenta(caracteresxnivel, nivel);
        ReporteTotalCuentasXNivelDto totalNivel = null;
        String sql = "select * "
                + "from ("
                + "select sub8.nivel5, sub8.nivel4, sub8.nivel3 , sub8.nivel2, case when sub8.nivel1 is null then 0 else sub8.nivel1 end as nivel1 "
                + "from("
                + "select sub7.*, (select sum(dp.total) as totalNivel from detalle_pagos dp "
                + "where dp.idcuenta like '" + tipoCuenta + "%' and character_length(dp.idcuenta) = " + longitud[0] + "  and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel1 "
                + "from ("
                + "select sub6.nivel5, sub6.nivel4,  sub6.nivel3,case when  sub6.nivel2 is null then 0 else  sub6.nivel2 end as nivel2 "
                + "from("
                + "select sub5.*, (select sum(dp.total) as totalNivel from detalle_pagos dp "
                + "where dp.idcuenta like '" + tipoCuenta + "%' and character_length(dp.idcuenta) = " + longitud[1] + "  and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel2 "
                + "from ( "
                + "select sub4.nivel5,sub4.nivel4,case when sub4.nivel3 is null then 0 else sub4.nivel3 end as nivel3 from( "
                + "select sub3.*,(select sum(dp.total) as totalNivel from detalle_pagos dp "
                + "where dp.idcuenta like '" + tipoCuenta + "%' and character_length(dp.idcuenta) = " + longitud[2] + "  and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel3 "
                + "from( "
                + "select sub2.nivel5,case when sub2.nivel4 is null then 0 else sub2.nivel4 end as nivel4  "
                + "from( "
                + "select sub1.*,(select sum(dp.total) as totalNivel from detalle_pagos dp "
                + "where dp.idcuenta like '" + tipoCuenta + "%' and character_length(dp.idcuenta) = " + longitud[3] + "  and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "')  as nivel4 "
                + "from ( "
                + "select case when sub0.nivel5 is null then 0 else sub0.nivel5 end as nivel5 "
                + "from( "
                + "select (select sum(dp.total) as totalNivel from detalle_pagos dp "
                + "where dp.idcuenta like '" + tipoCuenta + "%' and character_length(dp.idcuenta) = " + longitud[4] + "  and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel5 from dual limit 1 "
                + ") sub0 ) sub1 ) sub2 ) sub3 ) sub4 ) sub5 ) sub6 ) sub7 ) sub8 ) sub9 ";

        try {
            this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
            totalNivel = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReporteTotalCuentasXNivelDto>(ReporteTotalCuentasXNivelDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error totalPagoCuentaXNivel::" + e.getMessage());
        }

        return totalNivel;
    }

    @Override
    public ReporteTotalCuentasXNivelDto totalPagoCuentaXNivelSede(String nameDataSource, Long idsede, int tipoCuenta, String fechaInicial, String fechaFinal) {
        getPropiedades().setPropiedad("niveles_contabilidad");
        String caracteresxnivel = getPropiedades().leerPropiedad();
        String longitud[] = caracteresxnivel.split("-");
        //Integer longitud = obtenerLongitudNivelCuenta(caracteresxnivel, nivel);
        ReporteTotalCuentasXNivelDto totalNivel = null;
        String sql = "select case when sub8.idsede is null then " + idsede + " else sub8.idsede end as idsede, case when sub8.totalNivel is null then 0 else sub8.totalNivel end as nivel5,sub8.nivel1,sub8.nivel2,sub8.nivel3,sub8.nivel4 "
                + "from( "
                + "select dp4.idsede,sum(dp4.total) as totalNivel, sub7.nivel1,sub7.nivel2,sub7.nivel3,sub7.nivel4 "
                + "from detalle_pagos dp4 "
                + "inner join ( "
                + "select case when sub6.idsede is null then " + idsede + " else sub6.idsede end as idsede, case when sub6.totalNivel is null then 0 else sub6.totalNivel end as nivel4,sub6.nivel1,sub6.nivel2,sub6.nivel3 "
                + "from( "
                + "select dp3.idsede,sum(dp3.total) as totalNivel,sub5.nivel1,sub5.nivel2,sub5.nivel3 "
                + "from detalle_pagos dp3 "
                + "inner join ( "
                + "select case when sub4.idsede is null then " + idsede + " else sub4.idsede end as idsede, case when sub4.totalNivel is null then 0 else sub4.totalNivel end as nivel3,sub4.nivel1,sub4.nivel2 "
                + "from( "
                + "select dp2.idsede,sum(dp2.total) as totalNivel,sub3.nivel2,sub3.nivel1 "
                + "from detalle_pagos dp2 "
                + "inner join ( "
                + "select case when sub2.idsede is null then " + idsede + " else sub2.idsede end as idsede, case when sub2.totalNivel is null then 0 else sub2.totalNivel end as nivel2,sub2.nivel1 "
                + "from( "
                + "select dp1.idsede,sum(dp1.total) as totalNivel,sub1.nivel1 "
                + "from detalle_pagos dp1 "
                + "inner join ( "
                + "select case when sub0.idsede is null then " + idsede + " else sub0.idsede end as idsede,case when sub0.totalNivel is null then 0 else sub0.totalNivel  end as nivel1 "
                + "from( "
                + "select dp.idsede,sum(dp.total) as totalNivel from detalle_pagos dp "
                + "where dp.idcuenta like '" + tipoCuenta + "%' and character_length(dp.idcuenta) = " + longitud[0] + "  and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "and dp.idsede = " + idsede + " "
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

        try {
            this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
            totalNivel = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<ReporteTotalCuentasXNivelDto>(ReporteTotalCuentasXNivelDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error totalPagoCuentaXNivelSede::" + e.getMessage());
        }

        return totalNivel;
    }

    @Override
    public List<PagosCabeceraDto> buscarPagosXFecha(String nameDataSource, String fecha) {
        
        String sql = "select distinct p.idpagos ,p.idbeneficiario as idproveedor, "
                + "case when b.nombre is null then prov.nombre else b.nombre end  as nombreProveedor,p.fecha as fecha, "
                + "p.total from detalle_pagos dpp "
                + "inner join sedes s on s.idsedes = dpp.idsede "
                + "inner join cuentas_puc cp on cp.cod_cta = dpp.idcuenta "
                + "inner join pagos p on p.idpagos  = dpp.idpago "
                + "left join beneficiarios b on b.id = p.idbeneficiario "
                + "left join proveedor prov on prov.idproveedor = p.idbeneficiario "
                + "where date_format(p.fecha,'%Y-%m-%d') = '"+fecha+"' order by p.idpagos";
        List<PagosCabeceraDto> pagos = null;
        try {
            this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
            pagos =  this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<PagosCabeceraDto>(PagosCabeceraDto.class));
        } catch (Exception e) {
            System.out.println("Error buscarPagosXFecha::"+e.getMessage());
        }
        
        return pagos;
    }

    @Override
    public List<ReportePagosDto> reportePagos(String nameDataSource, String fechaInicial, String fechaFinal, Long idsede) {
        String sql = "select pro.nombre,dpa.idcuenta,dpa.total,pa.fecha from pagos pa " +
                "inner join proveedor pro on pro.idproveedor = pa.idbeneficiario " +
                "inner join detalle_pagos dpa on dpa.idpago = pa.idpagos " +
                "where pa.fecha between '"+fechaInicial+"' and '"+fechaFinal+"' and idsede= "+idsede+
                " union  " +
                "select ben.nombre,dpa.idcuenta,dpa.total,pa.fecha from pagos pa " +
                "inner join beneficiarios ben on ben.id = pa.idbeneficiario " +
                "inner join detalle_pagos dpa on dpa.idpago = pa.idpagos " +
                "where pa.fecha between '"+fechaInicial+"' and '"+fechaFinal+"' and idsede="+idsede;
        List<ReportePagosDto> pagos = null;
        try {
            this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
            pagos =  this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<ReportePagosDto>(ReportePagosDto.class));
        } catch (Exception e) {
            System.out.println("Error reportePagos::"+e.getMessage());
        }
        
        return pagos;
    }

}
