/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.dto.BalanceDto;
import com.colombian.cali.colombiancaliycali.dto.ComprasProveedorFechaDto;
import com.colombian.cali.colombiancaliycali.dto.ComprobanteConsolidadoSedeDto;
import com.colombian.cali.colombiancaliycali.dto.CuentasPagarProveedoresDto;
import com.colombian.cali.colombiancaliycali.dto.EstadoPerdidaGananciaProvisionalDto;
import com.colombian.cali.colombiancaliycali.dto.ReporteConsolidadoDto;
import com.colombian.cali.colombiancaliycali.entidades.DetallePorcentajeVentas;
import com.colombian.cali.colombiancaliycali.entidades.PorcentajeVentas;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import java.util.ArrayList;
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
public class ReportesDaoImpl implements ReportesDao {

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;

    @Override
    public List<ReporteConsolidadoDto> reporteConsolidado(List<Sedes> sedes, String fechaInicial, String fechaFinal) {

        List<ReporteConsolidadoDto> reporte = new ArrayList<ReporteConsolidadoDto>();
        String queryMesas = "select sum(m.valor_total) as total_mesas "
                + "from mesa m "
                + "where m.fecha_orden between '" + fechaInicial + "' and '" + fechaFinal + "' and m.estado_orden='A'";
        String queryOrdenes = "select sum(o.valor_total) as total_orden "
                + "from orden o "
                + "where o.fecha_orden between '" + fechaInicial + "' and '" + fechaFinal + "' and o.estado_orden='A'";
        String queryllevar = "select sum(ll.valor_total) as total_llevar "
                + "from "
                + "llevar ll "
                + "where ll.fecha_orden between '" + fechaInicial + "' and '" + fechaFinal + "' and ll.estado_orden='A'";
        String queryConsignaciones = "select sum(c.valor_consignacion) as total_consignacion "
                + "from consignaciones c "
                + "where date_format(c.fecha_consignacion,'%Y-%m-%d') between '" + fechaInicial + "' and '" + fechaFinal + "'";
        String queryGastos = "select sum(g.gas_valor) as gastos "
                + "from gastos g "
                + "where g.gas_fecha between '" + fechaInicial + "' and '" + fechaFinal + "'";
        String queryCompras = "select SUM(detalle_factura.valor_producto) as total_compras "
                + "from detalle_factura,   factura,   inventario  "
                + " WHERE ( factura.numero_factura = detalle_factura.numero_factura ) and  "
                + " ( inventario.codigo_producto_inventario = detalle_factura.codigo_producto_inventario ) and "
                + "                        ( ( factura.fecha_factura  between '" + fechaInicial + "' and '" + fechaFinal + "' ) AND  "
                + "                        ( factura.estado_factura = 'A' ) ) ";

        System.out.println("SEDE::" + sedes);
        if (sedes != null) {
            System.out.println("SEDE::" + sedes.size());
        }

        for (Sedes sede : sedes) {
            ReporteConsolidadoDto reporteConsolidadoDto = new ReporteConsolidadoDto();
            System.out.println("SEDE::" + sede.getSede());
            Long llevar = 0L;
            Long mesa = 0L;
            Long orden = 0L;
            Long consignacion = 0L;
            Long gasto = 0L;
            Long compras = 0L;
            if (sede.getIdsedes() != 1L) {
                try {
                    this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(sede.getSede()));

                    llevar = this.jdbctemplate.queryForObject(queryllevar, Long.class);
                    mesa = this.jdbctemplate.queryForObject(queryMesas, Long.class);
                    orden = this.jdbctemplate.queryForObject(queryOrdenes, Long.class);
                    consignacion = this.jdbctemplate.queryForObject(queryConsignaciones, Long.class);
                    gasto = this.jdbctemplate.queryForObject(queryGastos, Long.class);
                    compras = this.jdbctemplate.queryForObject(queryCompras, Long.class);

                    if (llevar == null) {
                        llevar = 0L;
                    }
                    if (mesa == null) {
                        mesa = 0L;
                    }
                    if (orden == null) {
                        orden = 0L;
                    }
                    if (consignacion == null) {
                        consignacion = 0L;
                    }
                    if (gasto == null) {
                        gasto = 0L;
                    }
                    if (compras == null) {
                        compras = 0L;
                    }

                    reporteConsolidadoDto.setCompras(compras);
                    reporteConsolidadoDto.setConsignacion(consignacion);
                    reporteConsolidadoDto.setGastos(gasto);
                    reporteConsolidadoDto.setVentas(mesa + llevar + orden);
                    reporteConsolidadoDto.setSede(sede.getSede());
                    reporte.add(reporteConsolidadoDto);
                } catch (DataAccessException e) {
                    System.out.println("EXCEPTION::" + e.getMessage());
                    reporteConsolidadoDto.setCompras(0L);
                    reporteConsolidadoDto.setConsignacion(0L);

                    reporteConsolidadoDto.setSede(sede.getSede() + ": No conecta ");
                    reporteConsolidadoDto.setVentas(0L);
                    reporteConsolidadoDto.setGastos(0L);
                    reporte.add(reporteConsolidadoDto);
                }
            }
        }

        return reporte;
    }

    @Override
    public Long gastosConsolidadoSede(Sedes sede, String fecha) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(sede.getSede()));
        Long gastos = 0L;
        try {
            String queryGastos = "select sum(g.gas_valor) as gastos "
                    + "from gastos g "
                    + "where g.gas_fecha = '" + fecha + "'";
            gastos = this.jdbctemplate.queryForLong(queryGastos);
        } catch (DataAccessException e) {
            System.out.println("Error gastosConsolidadoSede::" + e.getMessage());
        }
        return gastos;
    }

    @Override
    public Long consignacionesConsolidadoSede(Sedes sede, String fecha) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(sede.getSede()));
        Long consignacion = 0L;
        try {
            String queryConsignaciones = "select sum(c.valor_consignacion) as total_consignacion "
                    + "from consignaciones c "
                    + "where date_format(c.fecha_consignacion,'%Y-%m-%d') = '" + fecha + "'";
            consignacion = this.jdbctemplate.queryForLong(queryConsignaciones);
        } catch (Exception e) {
            System.out.println("Error consignacionesConsolidadoSede::" + e.getMessage());
        }
        return consignacion;
    }

    @Override
    public Long comprasConsolidadoSede(Sedes sede, String fecha) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(sede.getSede()));
        Long compras = 0L;
        try {
            String queryCompras = "select SUM(detalle_factura.valor_producto) as total_compras "
                    + "from detalle_factura,   factura,   inventario  "
                    + " WHERE ( factura.numero_factura = detalle_factura.numero_factura ) and  "
                    + " ( inventario.codigo_producto_inventario = detalle_factura.codigo_producto_inventario ) and "
                    + "                        ( ( factura.fecha_factura  = '" + fecha + "' ) AND  "
                    + "                        ( factura.estado_factura = 'A' ) ) ";
            compras = this.jdbctemplate.queryForLong(queryCompras);
        } catch (Exception e) {
            System.out.println("Error comprasConsolidadoSede::" + e.getMessage());
            compras = -1L;
        }
        return compras;
    }

    @Override
    public Long ordenesConsolidadoSede(Sedes sede, String fecha) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(sede.getSede()));
        Long ordenes = 0L;
        try {
            String queryOrdenes = "select sum(o.valor_total) as total_orden "
                    + "from orden o "
                    + "where o.fecha_orden = '" + fecha + "' and o.estado_orden='A'";
            ordenes = this.jdbctemplate.queryForLong(queryOrdenes);
        } catch (Exception e) {
            System.out.println("Error ordenesConsolidadoSede::" + e.getMessage());
        }
        return ordenes;
    }

    @Override
    public Long mesasConsolidadoSede(Sedes sede, String fecha) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(sede.getSede()));
        Long mesas = 0L;
        try {
            String queryMesas = "select sum(m.valor_total) as total_mesas "
                    + "from mesa m "
                    + "where m.fecha_orden = '" + fecha + "' and m.estado_orden='A'";
            mesas = this.jdbctemplate.queryForLong(queryMesas);
        } catch (Exception e) {
            System.out.println("Error mesasConsolidadoSede::" + e.getMessage());
        }
        return mesas;
    }

    @Override
    public Long llevarConsolidadoSede(Sedes sede, String fecha) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(sede.getSede()));
        Long llevar = 0L;
        try {
            String queryllevar = "select sum(ll.valor_total) as total_llevar "
                    + "from "
                    + "llevar ll "
                    + "where ll.fecha_orden = '" + fecha + "' and ll.estado_orden='A'";
            llevar = this.jdbctemplate.queryForLong(queryllevar);
        } catch (DataAccessException e) {
            System.out.println("Error llevarConsolidadoSede::" + e.getMessage());
        }
        return llevar;
    }

    @Override
    public Long pagosConsolidado(String nameDataSource, String fecha) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        Long pagos = 0L;
        try {
            String queryPagos = "select sum(total) as totalPagos from pagos where fecha = '" + fecha + "'";
            pagos = this.jdbctemplate.queryForLong(queryPagos);
        } catch (DataAccessException e) {
            System.out.println("Error pagosConsolidadoSede::" + e.getMessage());
        }
        return pagos;
    }

    @Override
    public List<ComprobanteConsolidadoSedeDto> buscarGastosXFecha(String nameDataSource, String fecha) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ComprobanteConsolidadoSedeDto> gastos = null;
        try {
            gastos = this.jdbctemplate.query(caliycaliDao.selectJdbTemplate("gas_fecha as fecha,c.con_cod as idConcepto,c.con_nombre as concepto,g.gas_valor as total",
                    "gastos g inner join concepto c on c.con_cod = g.gas_concepto", "g.gas_fecha = '" + fecha + "'"), new BeanPropertyRowMapper<ComprobanteConsolidadoSedeDto>(ComprobanteConsolidadoSedeDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarGastosXFecha::" + e.getMessage());
        }
        return gastos;
    }

    @Override
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMayor(String nameDataSource, String fechaInicio, String fechaFin) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ComprobanteConsolidadoSedeDto> movimientos = null;

        try {
            String sql = "select a.* from(" + caliycaliDao.selectJdbTemplate("consecutivo,idcuenta,concepto,total,fecha, idComprobanteCierre as idComprobante",
                    "detalle_cierre_sedes", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' and idcuenta='11050501'") + " union all "
                    + caliycaliDao.selectJdbTemplate("consecutivo,idcuenta,descripcion as concepto,total,fecha,idpago",
                            "detalle_pagos", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' )a order by fecha,idComprobante");
            movimientos = this.jdbctemplate.query(sql, new BeanPropertyRowMapper<ComprobanteConsolidadoSedeDto>(ComprobanteConsolidadoSedeDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error bucarMovimientoCajaMayor::" + e.getMessage());
        }

        return movimientos;
    }

    @Override
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMenor(String nameDataSource, String fechaInicio, String fechaFin) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ComprobanteConsolidadoSedeDto> movimientos = null;

        try {
            String sql = "select a.* from(" + caliycaliDao.selectJdbTemplate("consecutivo,idcuenta,descripcion as concepto,total,fecha,idcajamenor as idComprobante",
                    "detalle_caja_menor", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' "
                    + " union all "
                    + caliycaliDao.selectJdbTemplate("consecutivo,idcuenta,descripcion as concepto,total,fecha,idpago",
                            "detalle_pagos", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' and idcuenta='11051001'")
                    + ")a order by fecha,idComprobante");
            movimientos = this.jdbctemplate.query(sql, new BeanPropertyRowMapper<ComprobanteConsolidadoSedeDto>(ComprobanteConsolidadoSedeDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error bucarMovimientoCajaMenor::" + e.getMessage());
        }

        return movimientos;
    }

    @Override
    public List<DetallePorcentajeVentas> buscarDetallePagoConsolidadoMes(String nameDataSource, int mes) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<DetallePorcentajeVentas> detallePorcentajeVentas = null;
        String sql = caliycaliDao.selectJdbTemplate("dpv.*", "porcentaje_ventas pv inner join detalle_porcentaje_ventas dpv on pv.consecutivo = dpv.idporcentajeventa",
                "pv.mes = " + mes);
        try {
            detallePorcentajeVentas = this.jdbctemplate.query(sql, new BeanPropertyRowMapper<DetallePorcentajeVentas>(DetallePorcentajeVentas.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarDetallePagoConsolidadoMes::" + e.getMessage());
        }
        return detallePorcentajeVentas;
    }

    @Override
    public PorcentajeVentas buscarPagoConsolidadoMes(String nameDataSource, int mes) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        PorcentajeVentas porcentajeVentas = null;
        String sql = caliycaliDao.selectJdbTemplate("pv.*", "porcentaje_ventas pv",
                "pv.mes = " + mes +"  and year(curdate()) = year(pv.fecha)");
        try {
            porcentajeVentas = this.jdbctemplate.queryForObject(sql, new BeanPropertyRowMapper<PorcentajeVentas>(PorcentajeVentas.class));
        } catch (DataAccessException e) {
            System.out.println("Error buscarPagoConsolidadoMes::" + e.getMessage());
        }
        return porcentajeVentas;
    }

    @Override
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisional(String nameDataSource, String fechaInicial, String fechaFinal) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        String sql = "select 'Ingresos'as nombre,case when sub0.total_ingresos is null then 0 else sub0.total_ingresos end as totalCuenta from( "
                + "select sum(dcs.total) as total_ingresos from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '4%' and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + ")sub0 "
                + "union all "
                + "select 'Pagos',-1*sum(sub1.total) as total from( "
                + "select case when sub0.total is null then 0 else sub0.total end as total from ( "
                + "select sum(dcs.total) as total from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '5%' and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "union all "
                + "select sum(dp.total) from detalle_pagos dp "
                + "where dp.idcuenta like '5%' and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "union all "
		+" select sum(total) as total from detalle_caja_menor where (fecha between '" + fechaInicial + "' and '" + fechaFinal + "') and  idcuenta like '5%' "
                + ")sub0 "
                + ")sub1 "
                + "union all "
                + "select 'Costos',-1*sum(sub1.total) as total from ("
                + "select case when sub0.total is null then 0 else sub0.total end as total from ("
                + "select sum(dcs.total) as total from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '6%' and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "union all "
                + "select sum(fc.total) as total from facturas_compras fc "
                + "where fc.idcuenta like '6%' and fc.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + "union all "
                + "select sum(dp.total) from detalle_pagos dp "
                + "where dp.idcuenta like '6%' and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + ")sub0 "
                + ")sub1 ";
        List<EstadoPerdidaGananciaProvisionalDto> reporte = null;
        try {
            reporte = this.jdbctemplate.query(sql, new BeanPropertyRowMapper<EstadoPerdidaGananciaProvisionalDto>(EstadoPerdidaGananciaProvisionalDto.class));
        } catch (Exception e) {
            System.out.println("Error reporteEstadoPerdidaGananciaProvisional::" + e.getMessage());
        }
        return reporte;
    }

    @Override
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisionalXSede(String nameDataSource, String fechaInicial, String fechaFinal, Long idSede) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        String sql = "select 'Ingresos'as nombre,case when sub0.total_ingresos is null then 0 else sub0.total_ingresos end as totalCuenta from( "
                + "select sum(dcs.total) as total_ingresos from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '4%' and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and dcs.idsede=" + idSede + " "
                + ")sub0 "
                + "union all "
                + "select 'Pagos',-1*sum(sub1.total) as total from( "
                + "select case when sub0.total is null then 0 else sub0.total end as total from ( "
                + "select sum(dcs.total) as total from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '5%' and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and dcs.idsede=" + idSede + " "
                + "union all "
                + "select sum(dp.total) from detalle_pagos dp "
                + "where dp.idcuenta like '5%' and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and dp.idsede=" + idSede + " "
                + "union all "
		+" select sum(total) as total from detalle_caja_menor where (fecha between '" + fechaInicial + "' and '" + fechaFinal + "') and  idcuenta like '5%' and idsede="+ idSede + " "
                + ")sub0 "
                + ")sub1 "
                + "union all "
                + "select 'Costos',-1*sum(sub1.total) as total from ("
                + "select case when sub0.total is null then 0 else sub0.total end as total from ("
                + "select sum(dcs.total) as total from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '6%' and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and dcs.idsede=" + idSede + " "
                + "union all "
                + "select sum(fc.total) as total from facturas_compras fc "
                + "where fc.idcuenta like '6%' and fc.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and fc.idsede=" + idSede + " "
                + "union all "
                + "select sum(dp.total) from detalle_pagos dp "
                + "where dp.idcuenta like '6%' and dp.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and dp.idsede=" + idSede + " "
                + ")sub0 "
                + ")sub1 ";
        List<EstadoPerdidaGananciaProvisionalDto> reporte = null;
        try {
            reporte = this.jdbctemplate.query(sql, new BeanPropertyRowMapper<EstadoPerdidaGananciaProvisionalDto>(EstadoPerdidaGananciaProvisionalDto.class));
        } catch (Exception e) {
            System.out.println("Error reporteEstadoPerdidaGananciaProvisional::" + e.getMessage());
        }
        return reporte;
    }

    @Override
    public List<ComprasProveedorFechaDto> reporteComprasProveedorFechaDto(String nameDataSource, String fechInicial, String fechaFinal) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ComprasProveedorFechaDto> reporte = null;
        String sql = "select d.*,d.valor_total - d.pagado as diferencia from( "
                + "select c.idbeneficiario,c.proveedor,c.pagado, "
                + "case when c.total_compras is null then 0 else c.total_compras end as valor_total "
                + "from( "
                + "select b.idbeneficiario,prov.nombre as proveedor, b.total as pagado, "
                + "(select sum(comp.valor_total) as total from compras comp where comp.estado_compra='A' and comp.fecha_compra between '" + fechInicial + "' and '" + fechaFinal + "' "
                + "and comp.codigo_proveedor = idbeneficiario) as total_compras "
                + "from( select proveedor.idproveedor as idbeneficiario,case when a1.total is null then 0 else a1.total end as total from proveedor "
                + "left join (select a.idbeneficiario,sum(a.total) as total from( "
                + "select distinct p.idpagos,p.idbeneficiario,p.total "
                + "from pagos p "
                + "inner join detalle_pagos dp on dp.idpago = p.idpagos "
                + "where dp.numero_compra is not null and "
                + "p.fecha between '" + fechInicial + "' and '" + fechaFinal + "' "
                + ")a group by idbeneficiario "
                + ")a1 on a1.idbeneficiario = proveedor.idproveedor "
                + ")b inner join proveedor prov on prov.idproveedor=b.idbeneficiario "
                + ")c "
                + ")d where d.pagado <> 0 and d.valor_total <> 0";
        try {
            reporte = this.jdbctemplate.query(sql, new BeanPropertyRowMapper<ComprasProveedorFechaDto>(ComprasProveedorFechaDto.class));
        } catch (Exception e) {
            System.out.println("Error reporteComprasProveedorFechaDto::" + e.getMessage());
        }

        return reporte;
    }

    @Override
    public List<CuentasPagarProveedoresDto> reporteCuentasPagarProveedoresDto(String nameDataSource, String fechInicial, String fechaFinal, Long idProveedor) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        String sql = "select c.id_compra,c.fecha_compra as fecha,c.fecha_vencimiento, "
                + "datediff(c.fecha_vencimiento,c.fecha_compra) as dias_vencido, "
                + "c.valor_total,c.saldo from compras c "
                + "where c.saldo<>0 and c.codigo_proveedor= " + idProveedor
                + "   and c.fecha_compra between '" + fechInicial + "' and '" + fechaFinal + "'";
        List<CuentasPagarProveedoresDto> reporte = null;
        try {
            reporte = this.jdbctemplate.query(sql, new BeanPropertyRowMapper<CuentasPagarProveedoresDto>(CuentasPagarProveedoresDto.class));
        } catch (Exception e) {
            System.out.println("Error reporteCuentasPagarProveedoresDto::"+e.getMessage());
        }
        
        return reporte;
    }

    @Override
    public List<BalanceDto> reporteBalance(String nameDataSource, String fechInicial, String fechaFinal,Long idsede) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        String condicionSede = "";
        if(idsede!=null){
            condicionSede = " and idsede="+idsede;
        }
        String sql = " select sub0.cuenta,cp.nombre_cta as nombre_cuenta,sub0.total,5 as tipo  " +
                    "from( select sub.cuenta,sum(sub.total) as total from( " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_pagos where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '5%') "+condicionSede+" group by idcuenta " +
                    "union " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_cierre_sedes where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '5%') "+condicionSede+" group by idcuenta " +
                    "union " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_caja_menor where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '5%') "+condicionSede+" group by idcuenta " +
                    ")sub group by sub.cuenta " +
                    ")sub0 inner join cuentas_puc cp on cp.cod_cta = sub0.cuenta "
                + "union all "+
                " select sub0.cuenta,cp.nombre_cta as nombre_cuenta,sub0.total,4 as tipo  " +
                    "from( select sub.cuenta,sum(sub.total) as total from( " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_pagos where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '4%') "+condicionSede+" group by idcuenta " +
                    "union " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_cierre_sedes where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '4%') "+condicionSede+" group by idcuenta " +
                    "union " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_caja_menor where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '4%') "+condicionSede+" group by idcuenta " +
                    ")sub group by sub.cuenta " +
                    ")sub0 inner join cuentas_puc cp on cp.cod_cta = sub0.cuenta "
                + "union all "
                +" select sub0.cuenta,cp.nombre_cta as nombre_cuenta,sub0.total,6 as tipo  " +
                    "from( select sub.cuenta,sum(sub.total) as total from( " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_pagos where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '6%')"+condicionSede+" group by idcuenta " +
                    "union " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_cierre_sedes where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '6%') "+condicionSede+" group by idcuenta " +
                    "union " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_caja_menor where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '6%') "+condicionSede+" group by idcuenta " +
                    "union " +
                    "select fc.idcuenta as cuenta,sum(fc.total) as total from facturas_compras fc where fc.idcuenta like '6%' and fc.fecha between '"+fechInicial+"' and '"+fechaFinal+"' "+condicionSede+" group by idcuenta "+
                    ")sub group by sub.cuenta " +
                    ")sub0 inner join cuentas_puc cp on cp.cod_cta = sub0.cuenta";
        
        List<BalanceDto> reporte = null;
        try {
            reporte = this.jdbctemplate.query(sql, new BeanPropertyRowMapper<BalanceDto>(BalanceDto.class));
        } catch (Exception e) {
            System.out.println("Error reporteBalance::"+e.getMessage());
        }
        
        return reporte;
    }

}
