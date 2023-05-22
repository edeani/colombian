/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.datasources.GenericDataSource;
import com.administracion.dto.BalanceDto;
import com.administracion.dto.ComprasProveedorFechaDto;
import com.administracion.dto.ComprobanteConsolidadoSedeDto;
import com.administracion.dto.CuentasPagarProveedoresDto;
import com.administracion.dto.EstadoPerdidaGananciaProvisionalDto;
import com.administracion.dto.ReporteConsolidadoDto;
import com.administracion.dto.ReporteInventarioDTO;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.DetallePorcentajeVentas;
import com.administracion.entidad.PorcentajeVentas;
import com.administracion.entidad.Sedes;
import com.administracion.entidad.SubSedes;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.util.LeerXml;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class ReportesDaoImpl extends GenericDaoImpl<Object> implements ReportesDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportesDaoImpl.class);
    private final String UNION = " union all ";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final  String cuentaDescuento = "421040";
    /**
     * Servicio que me permite cambiar de conexión
     */
    @Autowired
    private GenericDataSource genericDataSource;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ConnectsAuth connectsAuth;

    /**
     * Inicialización de la conexión para este servicio
     *
     * @param dataSourceSede
     */
    @Autowired
    private void init(DataSource dataSourceSede) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSourceSede);
    }

    @Autowired
    private LeerXml leerXml;

    @Override
    public List<ReporteConsolidadoDto> reporteConsolidado(List<SubSedesDto> subSedes, String fechaInicial, String fechaFinal) {
        /**
         * Construcción de la query
         */

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(leerXml.getQuery("MesasSql.totalMesasXFecha"));
        queryBuilder.append(UNION);
        queryBuilder.append(leerXml.getQuery("OrdenesSql.totalOrdenesXFecha"));
        queryBuilder.append(UNION);
        queryBuilder.append(leerXml.getQuery("LlevarSql.totalLlevarXFecha"));
        queryBuilder.append(UNION);
        queryBuilder.append(leerXml.getQuery("ConsignacionesSql.totalConsigsXFecha"));
        queryBuilder.append(UNION);
        queryBuilder.append(leerXml.getQuery("GastosSql.totalGastosXFecha"));
        queryBuilder.append(UNION);
        queryBuilder.append(leerXml.getQuery("ComprasSql.totalComprasXFecha"));

        MapSqlParameterSource params = new MapSqlParameterSource("fechaInicial", fechaInicial);
        params.addValue("fechaFinal", fechaFinal);
        /**
         * La consulta debe haerse a cada una de las sedes
         */
        List<ReporteConsolidadoDto> reporte = new ArrayList<>();

        subSedes.stream().filter((subSede) -> (subSede.getId() > 0)).map((SubSedesDto subSede) -> {
            /**
             * Cambio de conexión
             */
            
            this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(connectsAuth.getDataSourceSubSede(subSede.getSede()));
            /**
             * Ejecución de la consulta
             */
            ReporteConsolidadoDto reporteConsolidadoDto = new ReporteConsolidadoDto();
            try {
                List<Long> totalesCaja = namedParameterJdbcTemplate.queryForList(queryBuilder.toString(), params, Long.class);
                reporteConsolidadoDto.setCompras(totalesCaja.get(5));
                reporteConsolidadoDto.setConsignacion(totalesCaja.get(3));
                reporteConsolidadoDto.setGastos(totalesCaja.get(4));
                reporteConsolidadoDto.setVentas(totalesCaja.get(0) + totalesCaja.get(1) + totalesCaja.get(2));
                reporteConsolidadoDto.setSede(subSede.getSede());
            } catch (DataAccessException e) {
                reporteConsolidadoDto.setCompras(0L);
                reporteConsolidadoDto.setConsignacion(0L);
                reporteConsolidadoDto.setSede(subSede.getSede() + ": No conecta ");
                reporteConsolidadoDto.setVentas(0L);
                reporteConsolidadoDto.setGastos(0L);
            }
            return reporteConsolidadoDto;
        }).forEachOrdered((reporteConsolidadoDto) -> {
            reporte.add(reporteConsolidadoDto);
        });

        return reporte;

    }

    @Override
    public List<ReporteInventarioDTO> findInventarioXFechaFinal(DataSource nameSede, String fecha) {
        this.jdbcTemplate = new JdbcTemplate(nameSede);
        return this.jdbcTemplate.query(selectJdbTemplate("codigo_producto_inventario as codigoProducto,descripcion_producto as descripcionProducto,stock_real as stockFinal",
                "inventario", "fecha_final = ? ") + " order by codigo_producto_inventario", new Object[]{fecha}, new BeanPropertyRowMapper(ReporteInventarioDTO.class));
    }

    @Override
    public Long gastosConsolidadoSede(Sedes sede, String fecha) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(connectsAuth.getDataSourceSubSede(sede.getSede()));
        Long gastos = 0L;
        MapSqlParameterSource params = new MapSqlParameterSource("fechaInicial", fecha);
        params.addValue("fechaFinal", fecha);
        try {
            gastos = this.namedParameterJdbcTemplate.queryForObject(leerXml.getQuery("GastosSql.totalGastosXFecha"), params, Long.class);
        } catch (DataAccessException e) {
            LOGGER.error("Error gastosConsolidadoSede::" + e.getMessage());
        }
        return gastos;
    }

    @Override
    public Long consignacionesConsolidadoSede(SubSedes subSedes, String fecha) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(connectsAuth.getDataSourceSubSede(subSedes.getSede()));
        Long consignacion = 0L;
        MapSqlParameterSource params = new MapSqlParameterSource("fechaInicial", fecha);
        params.addValue("fechaFinal", fecha);
        try {
            consignacion = this.namedParameterJdbcTemplate.queryForObject(leerXml.getQuery("ConsignacionesSql.totalConsigsXFecha"), params, Long.class);
        } catch (DataAccessException e) {
            LOGGER.error("Error gastosConsolidadoSede::" + e.getMessage());
        }
        return consignacion;
    }

    @Override
    public Long comprasConsolidadoSede(Sedes sede, String fecha) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(connectsAuth.getDataSourceSubSede(sede.getSede()));
        Long compras = 0L;
        MapSqlParameterSource params = new MapSqlParameterSource("fechaInicial", fecha);
        params.addValue("fechaFinal", fecha);
        try {
            compras = this.namedParameterJdbcTemplate.queryForObject(leerXml.getQuery("ComprasSql.totalComprasXFecha"), params, Long.class);
        } catch (DataAccessException e) {
            LOGGER.error("Error gastosConsolidadoSede::" + e.getMessage());
        }
        return compras;
    }

    @Override
    public Long totalConsolidadoSede(SubSedes subSede, String fecha) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(leerXml.getQuery("MesasSql.totalMesasXFecha"));
        queryBuilder.append(UNION);
        queryBuilder.append(leerXml.getQuery("OrdenesSql.totalOrdenesXFecha"));
        queryBuilder.append(UNION);
        queryBuilder.append(leerXml.getQuery("LlevarSql.totalLlevarXFecha"));

        MapSqlParameterSource params = new MapSqlParameterSource("fechaInicial", fecha);
        params.addValue("fechaFinal", fecha);

        try {
            this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(connectsAuth.getDataSourceSubSede(subSede.getSede()));
            List<Long> totales = this.namedParameterJdbcTemplate.queryForList(queryBuilder.toString(), params, Long.class);
            Long total = 0L;
            if (totales != null) {
                total = totales.stream().map((totale) -> totale).reduce(total, (accumulator, _item) -> accumulator + _item);
            }
            return total;
        } catch (DataAccessException e) {
            LOGGER.error("Error totalConsolidadoSede::" + e.getMessage());
            return 0L;
        }

    }

    @Override
    public Long pagosConsolidado(DataSource nameDataSource, String fecha) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        Long pagos = 0L;
        try {
            String queryPagos = "select sum(total) as totalPagos from pagos where fecha = '" + fecha + "'";
            pagos = this.jdbcTemplate.queryForObject(queryPagos,Long.class);
        } catch (DataAccessException e) {
            LOGGER.error("Error pagosConsolidadoSede::" + e.getMessage());
        }
        return pagos;
    }

    @Override
    public List<ComprobanteConsolidadoSedeDto> buscarGastosXFecha(DataSource nameDataSource, String fecha) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<ComprobanteConsolidadoSedeDto> gastos = null;
        try {
            gastos = this.jdbcTemplate.query(selectJdbTemplate("gas_fecha as fecha,c.con_cod as idConcepto,c.con_nombre as concepto,g.gas_valor as total",
                    "gastos g inner join concepto c on c.con_cod = g.gas_concepto", "g.gas_fecha = '" + fecha + "'"), new BeanPropertyRowMapper<>(ComprobanteConsolidadoSedeDto.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error buscarGastosXFecha::" + e.getMessage());
        }
        return gastos;
    }

    @Override
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMayor(DataSource nameDataSource, String fechaInicio, String fechaFin) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<ComprobanteConsolidadoSedeDto> movimientos = null;

        try {
            String sql = "select a.* from(" + selectJdbTemplate("consecutivo,idcuenta,concepto,total,fecha, idComprobanteCierre as idComprobante",
                    "detalle_cierre_sedes", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' and idcuenta='11050501'") + " union all "
                    + selectJdbTemplate("consecutivo,idcuenta,descripcion as concepto,total,fecha,idpago",
                            "detalle_pagos", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' )a order by fecha,idComprobante");
            movimientos = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ComprobanteConsolidadoSedeDto.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error bucarMovimientoCajaMayor::" + e.getMessage());
        }

        return movimientos;
    }
    
    @Override
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMayorSubsede(DataSource nameDataSource, String fechaInicio, String fechaFin, Integer idSubsede) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<ComprobanteConsolidadoSedeDto> movimientos = null;

        try {
            String sql = "select a.* from(" + selectJdbTemplate("consecutivo,idcuenta,concepto,total,fecha, idComprobanteCierre as idComprobante",
                    "detalle_cierre_sedes", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' and idcuenta='11050501' AND idsede = "+idSubsede+"") + " union all "
                    + selectJdbTemplate("consecutivo,idcuenta,descripcion as concepto,total,fecha,idpago",
                            "detalle_pagos", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' AND idsede = "+idSubsede+" )a order by fecha,idComprobante");
            movimientos = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ComprobanteConsolidadoSedeDto.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error bucarMovimientoCajaMayor::" + e.getMessage());
        }

        return movimientos;
    }

    @Override
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMenor(DataSource nameDataSource, String fechaInicio, String fechaFin) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<ComprobanteConsolidadoSedeDto> movimientos = null;

        try {
            String sql = "select a.* from(" + selectJdbTemplate("consecutivo,idcuenta,descripcion as concepto,total,fecha,idcajamenor as idComprobante,1 as tipoComprobante ",
                    "detalle_caja_menor", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' "
                    + " union "
                    + selectJdbTemplate("consecutivo,idcuenta,descripcion as concepto,total,fecha,idpago,2 as tipoComprobante ",
                            "detalle_pagos", "fecha between '" + fechaInicio + "' and '" + fechaFin + "' and idcuenta='11051001'")
                    + " union "
                    + " select consecutivo,idcuenta,concepto,total,fecha,idcomprobantecierre,3 as tipoComprobante "
                    + " from detalle_cierre_sedes where fecha between '" + fechaInicio + "' and '" + fechaFin + "' and idcuenta='11201010'"
                    + " union "
                    + " select cons as consecutivo,'11201010' as idcuenta,concepto,total,fecha,cons,4 as tipoComprobante "
                    + " from notas_credito where fecha between '" + fechaInicio + "' and '" + fechaFin + "' "
                    + " union "
                    + " select cons as consecutivo,cuenta as idcuenta,concepto,total,fecha,cons,5 as tipoComprobante "
                    + " from notas_debito where fecha between '" + fechaInicio + "' and '" + fechaFin + "' "
                    + ")a order by fecha,idComprobante");
            movimientos = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ComprobanteConsolidadoSedeDto.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error bucarMovimientoCajaMenor::" + e.getMessage());
        }

        return movimientos;
    }

    @Override
    public List<DetallePorcentajeVentas> buscarDetallePagoConsolidadoMes(DataSource nameDataSource, int mes) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        List<DetallePorcentajeVentas> detallePorcentajeVentas = null;
        String sql = selectJdbTemplate("dpv.*", "porcentaje_ventas pv inner join detalle_porcentaje_ventas dpv on pv.consecutivo = dpv.idporcentajeventa",
                "pv.mes = " + mes);
        try {
            detallePorcentajeVentas = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DetallePorcentajeVentas.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error buscarDetallePagoConsolidadoMes::" + e.getMessage());
        }
        return detallePorcentajeVentas;
    }

    @Override
    public PorcentajeVentas buscarPagoConsolidadoMes(DataSource nameDataSource, int mes) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        PorcentajeVentas porcentajeVentas = null;
        String sql = selectJdbTemplate("pv.*", "porcentaje_ventas pv",
                "pv.mes = " + mes +"  and year(curdate()) = year(pv.fecha)");
        try {
            porcentajeVentas = this.jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(PorcentajeVentas.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error buscarPagoConsolidadoMes::" + e.getMessage());
        }
        return porcentajeVentas;
    }

    @Override
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisional(DataSource nameDataSource, String fechaInicial, String fechaFinal) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
       
        String sql = "select 'Ingresos'as nombre,case when sub0.total_ingresos is null then 0 else sub0.total_ingresos end as totalCuenta from( "
                + "select sum(si2.total) as total_ingresos from( "
                + "select si.nombre,case when si.idcuenta = '"+cuentaDescuento+"' then  si.total*-1 else si.total end as total from ("
                + "select 'Ingresos' as nombre, dcs.total as total,dcs.idcuenta  from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '4%' and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' "
                + " union all "
                + " select 'Notas Debito' as nombre, total , cuenta from notas_debito "
                + " where fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and cuenta like '4%' "
                + ")si )si2"
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
                + "union all "
                +" select sum(total) as total from notas_credito where (fecha between '" + fechaInicial + "' and '" + fechaFinal + "') and  cuenta like '5%' "
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
                + "union all "
                +" select sum(total) as total from notas_credito where (fecha between '" + fechaInicial + "' and '" + fechaFinal + "') and  cuenta like '6%' "
                + ")sub0 "
                + ")sub1 ";
        List<EstadoPerdidaGananciaProvisionalDto> reporte = null;
        try {
            reporte = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EstadoPerdidaGananciaProvisionalDto.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error reporteEstadoPerdidaGananciaProvisional::" + e.getMessage());
        }
        return reporte;
    }

    @Override
    public List<EstadoPerdidaGananciaProvisionalDto> reporteEstadoPerdidaGananciaProvisionalXSede(DataSource nameDataSource, String fechaInicial, String fechaFinal, Long idSede) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String sql = "select 'Ingresos'as nombre,case when sub0.total_ingresos is null then 0 else sub0.total_ingresos end as totalCuenta from( "
                + "select sum(si2.total) as total_ingresos from( "
                + "select si.nombre,case when si.idcuenta = '"+cuentaDescuento+"' then  si.total*-1 else si.total end as total from ("
                + "select 'Ingresos' as nombre, dcs.total as total,dcs.idcuenta  from detalle_cierre_sedes dcs "
                + "where dcs.idcuenta like '4%' and dcs.fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and dcs.idsede=" + idSede
                + " union all "
                + " select 'Notas Debito' as nombre, total,cuenta from notas_debito "
                + " where fecha between '" + fechaInicial + "' and '" + fechaFinal + "' and idsede=" + idSede + " and cuenta like '4%' "
                + ")si )si2"
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
                + "union all "
		+" select sum(total) as total from notas_credito where (fecha between '" + fechaInicial + "' and '" + fechaFinal + "') and  cuenta like '5%' and idsede="+ idSede + " "
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
                + "union all "
		+" select sum(total) as total from notas_credito where (fecha between '" + fechaInicial + "' and '" + fechaFinal + "') and  cuenta like '6%' and idsede="+ idSede + " "
                + ")sub0 "
                + ")sub1 ";
        List<EstadoPerdidaGananciaProvisionalDto> reporte = null;
        try {
            reporte = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EstadoPerdidaGananciaProvisionalDto.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error reporteEstadoPerdidaGananciaProvisional::" + e.getMessage());
        }
        return reporte;
    }

    @Override
    public List<ComprasProveedorFechaDto> reporteComprasProveedorFechaDto(DataSource nameDataSource, String fechInicial, String fechaFinal) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
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
            reporte = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ComprasProveedorFechaDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error reporteComprasProveedorFechaDto::" + e.getMessage());
        }

        return reporte;
    }

    @Override
    public List<CuentasPagarProveedoresDto> reporteCuentasPagarProveedoresDto(DataSource nameDataSource, String fechInicial, String fechaFinal, Long idProveedor) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String sql = "select c.id_compra,c.fecha_compra as fecha,c.fecha_vencimiento, "
                + "datediff(c.fecha_vencimiento,c.fecha_compra) as dias_vencido, "
                + "c.valor_total,c.saldo from compras c "
                + "where c.saldo<>0 and c.codigo_proveedor= " + idProveedor
                + "   and c.fecha_compra between '" + fechInicial + "' and '" + fechaFinal + "'";
        List<CuentasPagarProveedoresDto> reporte = null;
        try {
            reporte = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CuentasPagarProveedoresDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error reporteCuentasPagarProveedoresDto::"+e.getMessage());
        }
        
        return reporte;
    }

    @Override
    public List<BalanceDto> reporteBalance(DataSource nameDataSource, String fechInicial, String fechaFinal,Long idsede) {
        this.jdbcTemplate = new JdbcTemplate(nameDataSource);
        String condicionSede = "";
        if(idsede!=null){
            condicionSede = " and idsede="+idsede;
        }
        String sql = "select sub1.*,SUBSTRING(sub1.cuenta, 1, 1) as tipo from( "
                + " select sub0.cuenta,cp.nombre_cta as nombre_cuenta,sub0.total  " +
                    "from( select sub.cuenta,sum(sub.total) as total from( " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_pagos where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '5%') "+condicionSede+" group by idcuenta " +
                    "union all " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_cierre_sedes where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '5%') "+condicionSede+" group by idcuenta " +
                    "union all " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_caja_menor where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '5%') "+condicionSede+" group by idcuenta " +
                    //Sumamos notas credito a los gastos o pagos
                    " union all " +
                    "select cuenta,sum(total) as total from notas_credito where (fecha between  '"+fechInicial+"' and '"+fechaFinal+"') "+condicionSede+"  group by cuenta  "+
                    ")sub group by sub.cuenta " +
                    ")sub0 inner join cuentas_puc cp on cp.cod_cta = sub0.cuenta "
                + "union all "+
                " select sub0.cuenta,cp.nombre_cta as nombre_cuenta,sub0.total  " +
                    "from( select sub.cuenta,sum(sub.total) as total from( " +
                    "select idcuenta as cuenta ,case when idcuenta = '"+cuentaDescuento+"' then total*-1 else total end  as total from detalle_pagos where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '4%') "+condicionSede+"  " +
                    "union all " +
                    "select idcuenta as cuenta ,case when idcuenta = '"+cuentaDescuento+"' then total*-1 else total end as total from detalle_cierre_sedes where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '4%') "+condicionSede+"  " +
                    "union all " +
                    "select idcuenta as cuenta ,case when idcuenta = '"+cuentaDescuento+"' then total*-1 else total end as total from detalle_caja_menor where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '4%') "+condicionSede+"  " +
                    //Sumamos notas debito a los ingresos 
                    " union all " +
                    "select cuenta,case when cuenta = '"+cuentaDescuento+"' then total*-1 else total end as total from notas_debito where (fecha between  '"+fechInicial+"' and '"+fechaFinal+"') "+condicionSede+" and cuenta like '4%'    "
                + ")sub group by sub.cuenta " +
                    ")sub0 inner join cuentas_puc cp on cp.cod_cta = sub0.cuenta "
                + "union all "
                +" select sub0.cuenta,cp.nombre_cta as nombre_cuenta,sub0.total  " +
                    "from( select sub.cuenta,sum(sub.total) as total from( " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_pagos where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '6%')"+condicionSede+" group by idcuenta " +
                    "union all " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_cierre_sedes where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '6%') "+condicionSede+" group by idcuenta " +
                    "union all " +
                    "select idcuenta as cuenta ,sum(total) as total from detalle_caja_menor where (fecha between '"+fechInicial+"' and '"+fechaFinal+"') and ( idcuenta like '6%') "+condicionSede+" group by idcuenta " +
                    "union all " +
                    "select fc.idcuenta as cuenta,sum(fc.total) as total from facturas_compras fc where fc.idcuenta like '6%' and fc.fecha between '"+fechInicial+"' and '"+fechaFinal+"' "+condicionSede+" group by idcuenta "+
                    ")sub group by sub.cuenta " +
                    ")sub0 inner join cuentas_puc cp on cp.cod_cta = sub0.cuenta "
                + ")sub1";
        
        List<BalanceDto> reporte = null;
        try {
            reporte = this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BalanceDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error reporteBalance::"+e.getMessage());
        }
        
        return reporte;
    }

    @Override
    public Long pagosContarjetaTotal(DataSource nameDataSource, String fecha) {
        try {
            this.jdbcTemplate = new JdbcTemplate(nameDataSource);
            return this.jdbcTemplate.queryForObject("select sum(total) as total from (select sum(pago_tarjeta) as total from mesa " +
            "where fecha_orden = '"+fecha+"' and pago_tarjeta <> 0 " +
            "and estado_orden = 'A' " +
            "union all " +
            "select sum(pago_tarjeta) as total from orden " +
            "where fecha_orden = '"+fecha+"' and pago_tarjeta <> 0 " +
            "and estado_orden = 'A' " +
            "union all " +
            "select sum(pago_tarjeta) as total from llevar " +
            "where " +
            "fecha_orden = '"+fecha+"' and pago_tarjeta <> 0 " +
            "and estado_orden = 'A') sub0",Long.class);
        } catch (DataAccessException e) {
            LOGGER.error("Error pagosContarjetaTotal::"+e.getMessage());
        }
        return null;
    }

    @Override
    public Long pagosDescuentoTotal(DataSource nameDataSource, String fecha) {
        try {
            this.jdbcTemplate = new JdbcTemplate(nameDataSource);
            return this.jdbcTemplate.queryForObject("select sum(total) as total from (select sum(descuento_orden) as total from mesa " +
            "where fecha_orden = '"+fecha+"' and descuento_orden <> 0  " +
            "and estado_orden = 'A' " +
            "union " +
            "select sum(descuento_orden) as total from orden " +
            "where fecha_orden = '"+fecha+"' and descuento_orden <> 0 " +
            "and estado_orden = 'A' " +
            "union " +
            "select sum(descuento_orden) as total from llevar " +
            "where " +
            "fecha_orden = '"+fecha+"' and  descuento_orden <> 0 " +
            "and estado_orden = 'A') sub0",Long.class);
        } catch (DataAccessException e) {
            LOGGER.error("Error pagosDescuentoTotal::"+e.getMessage());
        }
        return null;
    }

    @Override
    public List<ComprobanteConsolidadoSedeDto> bucarMovimientoCajaMayor(String nameDataSource, String sfechaInicial, String sfechaFinal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
