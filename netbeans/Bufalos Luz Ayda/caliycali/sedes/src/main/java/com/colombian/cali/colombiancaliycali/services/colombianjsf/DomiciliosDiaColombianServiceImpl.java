/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.entidades.Users;
import com.colombian.cali.colombiancaliycali.services.SecurityService;
import com.mycompany.util.Formatos;
import com.mycompany.dto.OrdenesColombianDto;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProjectsDao projectsDao;

    private JdbcTemplate jdbctemplate;

    private Double totalDomicilios;
    private Long totalRegistros;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenesColombianDto> domicilioDia(Date fi, Date ff) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(user.getSede().getSede()));
        List<OrdenesColombianDto> ordenes = new ArrayList<OrdenesColombianDto>();
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
                    + " group by orden.fecha_orden";
            ordenes = this.jdbctemplate.query(query, new BeanPropertyRowMapper<OrdenesColombianDto>(OrdenesColombianDto.class));
            calcularResumenDomicilios(ordenes);
        } catch (Exception e) {
            System.out.println("Error domicilioDia::" + e.getMessage());
        }
        return ordenes;
    }
    /**
     * Calcula el resumen de los domicilios
     * @param ordenes 
     */
    public void calcularResumenDomicilios(List<OrdenesColombianDto> ordenes) {
        totalDomicilios=0D;
        totalRegistros=0L;
        if (ordenes != null) {
            for (OrdenesColombianDto orden : ordenes) {
                totalDomicilios += orden.getDomicilios();
                totalRegistros += orden.getValor_total();
            }
        }
    }

    /**
     * @return the totalDomicilios
     */
    public Double getTotalDomicilios() {
        return totalDomicilios;
    }

    /**
     * @return the totalRegistros
     */
    public Long getTotalRegistros() {
        return totalRegistros;
    }
}
