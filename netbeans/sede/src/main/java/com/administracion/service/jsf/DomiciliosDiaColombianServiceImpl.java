/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;


import com.administracion.entidad.Users;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.mycompany.util.Formatos;
import com.mycompany.dto.OrdenesColombianDto;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
public class DomiciliosDiaColombianServiceImpl implements DomiciliosDiaColombianService {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ConnectsAuth connectsAuth;

    private JdbcTemplate jdbctemplate;

    private Long totalDomicilios;
    private Long totalRegistros;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenesColombianDto> domicilioDia(Date fi, Date ff,String subsede) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        List<OrdenesColombianDto> ordenes = new ArrayList<>();
        try {
            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
            String query = " SELECT orden.fecha_orden as Fecha, case when (DAYNAME(orden.fecha_orden))='Monday' then 'Lunes' else (case when "
                    + " (DAYNAME(orden.fecha_orden))='Tuesday' then 'Martes' else (case when "
                    + " (DAYNAME(orden.fecha_orden))='Wednesday' then 'Miercoles' else (case when "
                    + " (DAYNAME(orden.fecha_orden))='Thursday' then 'Jueves' else (case when "
                    + " (DAYNAME(orden.fecha_orden))='Friday' then 'Viernes' else (case when  "
                    + " (DAYNAME(orden.fecha_orden))='Saturday' then 'Sabado' else (case when   (DAYNAME(orden.fecha_orden))='Sunday' then 'Domingo' else (DAYNAME(orden.fecha_orden) )end)end)end)end)end)end)end  as Dia, "
                    + " count(*) Domicilios, sum(orden.valor_total) Valor_Total   FROM        orden   "
                    + " WHERE  ( ( orden.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "'  and '" + formato.dateTostring(dfDefault.format(ff)) + "' ) AND  "
                    + " ( orden.estado_orden = 'A' ) )    "
                    + " group by orden.fecha_orden order by orden.fecha_orden desc";
            ordenes = this.jdbctemplate.query(query, new BeanPropertyRowMapper<>(OrdenesColombianDto.class));
            calcularResumenDomicilios(ordenes);
        } catch (DataAccessException e) {
            System.out.println("Error domicilioDia::" + e.getMessage());
        }
        return ordenes;
    }
    /**
     * Calcula el resumen de los domicilios
     * @param ordenes 
     */
    public void calcularResumenDomicilios(List<OrdenesColombianDto> ordenes) {
        totalDomicilios=0L;
        totalRegistros=0L;
        if (ordenes != null) {
            ordenes.stream().map((orden) -> {
                totalDomicilios += orden.getDomicilios();
                return orden;
            }).forEachOrdered((orden) -> {
                totalRegistros += orden.getValor_total();
            });
        }
    }

    /**
     * @return the totalDomicilios
     */
    @Override
    public Long getTotalDomicilios() {
        return totalDomicilios;
    }

    /**
     * @return the totalRegistros
     */
    @Override
    public Long getTotalRegistros() {
        return totalRegistros;
    }
}
