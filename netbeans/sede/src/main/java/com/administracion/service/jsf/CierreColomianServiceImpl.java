/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;


import com.administracion.dao.ReportesDao;
import com.administracion.entidad.Users;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.mycompany.dto.Consignaciones;
import com.mycompany.util.Formatos;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author joseefren
 */
@Service
public class CierreColomianServiceImpl implements CierreColombianService {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ConnectsAuth connectsAuth;
    @Autowired
    private ReportesDao reportesDao;
    
    private JdbcTemplate jdbctemplate;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CierreColomianServiceImpl.class);

    @Transactional
    ////@Async
    @Override
    public Double cierreDiario(Date fechaCierre,String subsede) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        Double cajaInicial = 0D;
        try {
            Formatos formato = new Formatos();

            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            String query = "select caja_real  from cierre_diario  where fecha = '" + formato.fechaMenos(dfDefault.format(fechaCierre), 1) + "'";
            cajaInicial = this.jdbctemplate.queryForObject(query, Double.class);
        } catch (DataAccessException e) {
            LOGGER.error("Error cierreDiario::" + e.getMessage());
        }
        return cajaInicial;
    }

    @Transactional
    ////@Async
    @Override
    public Double cierreVentas(Date fechaCierre,String subsede) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        Double ventas = 0D;
        try {
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            Formatos formato = new Formatos();
            String f = formato.dateTostring(dfDefault.format(fechaCierre));
            String query = "select (totalventas + totalventas2 + totalventas3) as totalventas from( "
                    + " SELECT  case when sum(ll.valor_total) is null then 0 else sum(ll.valor_total) end as totalventas , "
                    + " (SELECT case when sum(o.valor_total) is null then 0 else sum(o.valor_total) end  as vo from orden o "
                    + " where estado_orden = 'A' and fecha_orden = '" + f + "') as totalventas2,"
                    + " (select case when sum(m.valor_total) is null then 0 else sum(m.valor_total) end as totalventas3 from mesa m "
                    + " where estado_orden = 'A' and fecha_orden = '" + f + "' "
                    + " ) as totalventas3 "
                    + " from  llevar ll where estado_orden = 'A' and fecha_orden = '" + f + "' "
                    + " )sub ";

            ventas = this.jdbctemplate.queryForObject(query, Double.class);
        } catch (DataAccessException e) {
            LOGGER.error("Error cierreVentas::" + e.getMessage());
        }
        return ventas;

    }

    @Transactional
    ////@Async
    @Override
    public Double cierreGastos(Date fechaCierre,String subsede) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        Double gastos = 0D;
        try {
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            Formatos formato = new Formatos();
            String f = formato.dateTostring(dfDefault.format(fechaCierre));
            String query = "select SUM(gas_valor) as gastosTotal  from gastos  where gas_fecha ='" + f + "'";

            gastos = this.jdbctemplate.queryForObject(query, Double.class);
            if(gastos==null){
                gastos=0D;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error cierreGastos::" + e.getMessage());
        }
        return gastos;
    }

    @Transactional
    //@Async
    @Override
    public Double cierreConsignaciones(Date fechaCierre,String subsede) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        Double consignaciones = 0D;
        try {
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            Formatos formato = new Formatos();
            String f = formato.dateTostring(dfDefault.format(fechaCierre));
            String query = "select SUM(valor_consignacion) as consignacionTotal  from consignaciones  where fecha ='" + f + "'";
            consignaciones = this.jdbctemplate.queryForObject(query, Double.class);
            if(consignaciones==null){
                consignaciones = 0D;
            }
        } catch (DataAccessException e) {
            LOGGER.error("Error cierreConsignaciones::" + e.getMessage());
        }
        return consignaciones;
    }

    @Transactional
    //@Async
    @Override
    public List<Consignaciones> cierreListaConsignaciones(Date fechaCierre,String subsede) {
        // EntityManagerFactory factory = Persistence.createEntityManagerFactory(user.getSede().getPersistencia());
        List<Consignaciones> listaConsignaciones = null;
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        try {
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            Formatos formato = new Formatos();
            String f = formato.dateTostring(dfDefault.format(fechaCierre));
            String query = "SELECT c.* FROM consignaciones c WHERE c.fecha = '" + f + "' ORDER BY c.fecha_consignacion";
            listaConsignaciones = this.jdbctemplate.query(query, new BeanPropertyRowMapper<>(Consignaciones.class));
        } catch (DataAccessException e) {
            LOGGER.error("Error cierreListaConsignaciones::" + e.getMessage());
        }

        return listaConsignaciones;
        //return null;

    }

    @Transactional(readOnly = true)
    @Override
    public Double cierrePagosConTarjetas(Date fechaCierre,String subsede) {
        Users user = securityService.getCurrentUser();
        Double pagosConTarjeta = 0D;
        String sFecha = com.administracion.util.Formatos.dateTostring(fechaCierre);
        try {
           pagosConTarjeta = reportesDao.pagosContarjetaTotal(connectsAuth.getDataSourceSubSede(subsede),sFecha).doubleValue();
        } catch (Exception e) {
            LOGGER.error("Error cierrePagosConTarjetas::" + e.getMessage());
        }
        
        return pagosConTarjeta;
    }

    @Transactional(readOnly = true)
    @Override
    public Double cierreDescuentos(Date fechaCierre,String subsede) {
        Users user = securityService.getCurrentUser();
        Double descuentos = 0D;
        String sFecha = com.administracion.util.Formatos.dateTostring(fechaCierre);
        try {
           descuentos = reportesDao.pagosDescuentoTotal(connectsAuth.getDataSourceSubSede(subsede),sFecha).doubleValue();
        } catch (Exception e) {
            LOGGER.error("Error cierreDescuentos::" + e.getMessage());
        }
        
        return descuentos;
    }

   

}
