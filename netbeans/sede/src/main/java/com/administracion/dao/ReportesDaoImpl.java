/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import com.administracion.datasources.GenericDataSource;
import com.administracion.dto.ReporteConsolidadoDto;
import com.administracion.entidad.Sedes;
import com.administracion.util.LeerXml;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class ReportesDaoImpl implements ReportesDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportesDaoImpl.class);

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * Servicio que me permite cambiar de conexión
     */
    @Autowired
    private GenericDataSource genericDataSource;

    /**
     * Inicialización de la conexción para este servicio
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
    public List<ReporteConsolidadoDto> reporteConsolidado(List<Sedes> sedes, String fechaInicial, String fechaFinal) {
        /**
         * Construcción de la query
         */
        final String UNION = " union all ";
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
        LOGGER.info("CONSULLTA:: "+queryBuilder.toString());
        sedes.stream().filter((sede) -> (sede.getIdsedes() != 1)).map((Sedes sede) -> {
            /**
             * Cambio de conexión
             */
            genericDataSource.updateGenericDataSource(sede);
            this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(genericDataSource.getGenericDataSource());
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
                reporteConsolidadoDto.setSede(sede.getSede());
            } catch (DataAccessException e) {
                reporteConsolidadoDto.setCompras(0L);
                reporteConsolidadoDto.setConsignacion(0L);
                reporteConsolidadoDto.setSede(sede.getSede() + ": No conecta ");
                reporteConsolidadoDto.setVentas(0L);
                reporteConsolidadoDto.setGastos(0L);
            }
            return reporteConsolidadoDto;
        }).forEachOrdered((reporteConsolidadoDto) -> {
            reporte.add(reporteConsolidadoDto);
        });

        return reporte;

    }

}
