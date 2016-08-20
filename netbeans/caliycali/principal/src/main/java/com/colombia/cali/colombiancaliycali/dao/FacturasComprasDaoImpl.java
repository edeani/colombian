/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dao.generic.DataGenericDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.ReporteTotalCuentasXNivelDto;
import com.colombian.cali.colombiancaliycali.entidades.FacturasCompras;
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
public class FacturasComprasDaoImpl extends DataGenericDao implements FacturasComprasDao {

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;

    @Override
    public void guardarFacturaComprasDao(String nameDataSource, FacturasCompras facturasCompras) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbctemplate.execute(caliycaliDao.insertJdbTemplate("referencia,fecha,idsede,total,idcuenta", "facturas_compras",
                    facturasCompras.getReferencia() + ",'" + Formatos.dateTostring(facturasCompras.getFecha()) + "',"
                    + facturasCompras.getIdsede() + "," + facturasCompras.getTotal() + ",'"
                    + facturasCompras.getIdcuenta() + "'"));
        } catch (DataAccessException e) {
            System.out.println("Error guardarFacturaComprasDao::" + e.getMessage());
        }
    }
    
    @Override
    public void actualizarFacturaComprasDao(String nameDataSource, FacturasCompras facturasCompras) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbctemplate.execute(caliycaliDao.updateJdbTemplate("fecha='" + Formatos.dateTostring(facturasCompras.getFecha())
                    + "',idsede=" + facturasCompras.getIdsede() + ",total=" + facturasCompras.getTotal()
                    + ",idcuenta='" + facturasCompras.getIdcuenta() + "'", "facturas_compras", "consecutivo=" + facturasCompras.getConsecutivo()));

        } catch (DataAccessException e) {
            System.out.println("Error actualizarFacturaComprasDao::" + e.getMessage());
        }
    }

    @Override
    public ReporteTotalCuentasXNivelDto totalFacturaCompraCuentaXNivel(String nameDataSource, int tipoCuenta, String fechaInicial, String fechaFinal) {
        getPropiedades().setPropiedad("niveles_contabilidad");
        String caracteresxnivel = getPropiedades().leerPropiedad();
        String longitud[] = caracteresxnivel.split("-");
        //Integer longitud = obtenerLongitudNivelCuenta(caracteresxnivel, nivel);
        ReporteTotalCuentasXNivelDto totalNivel = null;
        String sql = "select * "
                + "from ("
                + "select sub8.nivel5, sub8.nivel4, sub8.nivel3 , sub8.nivel2, case when sub8.nivel1 is null then 0 else sub8.nivel1 end as nivel1 "
                + "from("
                + "select sub7.*, (select sum(fc.total) as totalNivel from facturas_compras fc "
                + "where fc.idcuenta like '" + tipoCuenta + "%' and character_length(fc.idcuenta) = " + longitud[0] + "  and fc.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel1 "
                + "from ("
                + "select sub6.nivel5, sub6.nivel4,  sub6.nivel3,case when  sub6.nivel2 is null then 0 else  sub6.nivel2 end as nivel2 "
                + "from("
                + "select sub5.*, (select sum(fc.total) as totalNivel from facturas_compras fc "
                + "where fc.idcuenta like '" + tipoCuenta + "%' and character_length(fc.idcuenta) = " + longitud[1] + "  and fc.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel2 "
                + "from ( "
                + "select sub4.nivel5,sub4.nivel4,case when sub4.nivel3 is null then 0 else sub4.nivel3 end as nivel3 from( "
                + "select sub3.*,(select sum(fc.total) as totalNivel from facturas_compras fc "
                + "where fc.idcuenta like '" + tipoCuenta + "%' and character_length(fc.idcuenta) = " + longitud[2] + "  and fc.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel3 "
                + "from( "
                + "select sub2.nivel5,case when sub2.nivel4 is null then 0 else sub2.nivel4 end as nivel4  "
                + "from( "
                + "select sub1.*,(select sum(fc.total) as totalNivel from facturas_compras fc "
                + "where fc.idcuenta like '" + tipoCuenta + "%' and character_length(fc.idcuenta) = " + longitud[3] + "  and fc.fecha between '" + fechaInicial + "' and '" + fechaFinal + "')  as nivel4 "
                + "from ( "
                + "select case when sub0.nivel5 is null then 0 else sub0.nivel5 end as nivel5 "
                + "from( "
                + "select (select sum(fc.total) as totalNivel from facturas_compras fc "
                + "where fc.idcuenta like '" + tipoCuenta + "%' and character_length(fc.idcuenta) = " + longitud[4] + "  and fc.fecha between '" + fechaInicial + "' and '" + fechaFinal + "') as nivel5 from dual limit 1 "
                + ") sub0 ) sub1 ) sub2 ) sub3 ) sub4 ) sub5 ) sub6 ) sub7 ) sub8 ) sub9 ";

        try {
            this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
            totalNivel = this.jdbctemplate.queryForObject(sql, new BeanPropertyRowMapper<ReporteTotalCuentasXNivelDto>(ReporteTotalCuentasXNivelDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error totalFacturaCompraCuentaXNivel::" + e.getMessage());
        }

        return totalNivel;
    }

    @Override
    public ReporteTotalCuentasXNivelDto totalFacturaCompraCuentaXNivelSede(String nameDataSource, Long idsede, int tipoCuenta, String fechaInicial, String fechaFinal) {
        getPropiedades().setPropiedad("niveles_contabilidad");
        String caracteresxnivel = getPropiedades().leerPropiedad();
        String longitud[] = caracteresxnivel.split("-");
        //Integer longitud = obtenerLongitudNivelCuenta(caracteresxnivel, nivel);
        ReporteTotalCuentasXNivelDto totalNivel = null;
        String sql = "select case when sub8.idsede is null then " + idsede + " else sub8.idsede end as idsede, case when sub8.totalNivel is null then 0 else sub8.totalNivel end as nivel5,sub8.nivel1,sub8.nivel2,sub8.nivel3,sub8.nivel4 "
                + "from( "
                + "select dp4.idsede,sum(dp4.total) as totalNivel, sub7.nivel1,sub7.nivel2,sub7.nivel3,sub7.nivel4 "
                + "from facturas_compras dp4 "
                + "inner join ( "
                + "select case when sub6.idsede is null then " + idsede + " else sub6.idsede end as idsede, case when sub6.totalNivel is null then 0 else sub6.totalNivel end as nivel4,sub6.nivel1,sub6.nivel2,sub6.nivel3 "
                + "from( "
                + "select dp3.idsede,sum(dp3.total) as totalNivel,sub5.nivel1,sub5.nivel2,sub5.nivel3 "
                + "from facturas_compras dp3 "
                + "inner join ( "
                + "select case when sub4.idsede is null then " + idsede + " else sub4.idsede end as idsede, case when sub4.totalNivel is null then 0 else sub4.totalNivel end as nivel3,sub4.nivel1,sub4.nivel2 "
                + "from( "
                + "select dp2.idsede,sum(dp2.total) as totalNivel,sub3.nivel2,sub3.nivel1 "
                + "from facturas_compras dp2 "
                + "inner join ( "
                + "select case when sub2.idsede is null then " + idsede + " else sub2.idsede end as idsede, case when sub2.totalNivel is null then 0 else sub2.totalNivel end as nivel2,sub2.nivel1 "
                + "from( "
                + "select dp1.idsede,sum(dp1.total) as totalNivel,sub1.nivel1 "
                + "from facturas_compras dp1 "
                + "inner join ( "
                + "select case when sub0.idsede is null then " + idsede + " else sub0.idsede end as idsede,case when sub0.totalNivel is null then 0 else sub0.totalNivel  end as nivel1 "
                + "from( "
                + "select fc.idsede,sum(fc.total) as totalNivel from facturas_compras fc "
                + "where fc.idcuenta like '" + tipoCuenta + "%' and character_length(fc.idcuenta) = " + longitud[0] + "  and fc.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "and fc.idsede = " + idsede + " "
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
            this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
            totalNivel = this.jdbctemplate.queryForObject(sql, new BeanPropertyRowMapper<ReporteTotalCuentasXNivelDto>(ReporteTotalCuentasXNivelDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error totalFacturaCompraCuentaXNivelSede::" + e.getMessage());
        }

        return totalNivel;

    }

}
