/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.entidades.Users;
import com.colombian.cali.colombiancaliycali.services.SecurityService;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.Mesasyllevar;
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
public class MesasyllevarColombianServiceImpl implements MesasyllevarColombianService {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ProjectsDao projectsDao;

    private JdbcTemplate jdbctemplate;
    private Double totalvalor;

    @Override
    @Transactional(readOnly = true)
    public List<Mesasyllevar> mesas(Date fi, Date ff) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(user.getSede().getSede()));
        List<Mesasyllevar> mesasyllevar = new ArrayList<Mesasyllevar>();
        try {
            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

            String query = " SELECT 'Mesa' as tipo, "
                    + " mesa.numero_orden as orden, "
                    + " mesa.hora as fecha, "
                    + " mesa.valor_total as valor, "
                    + " mesa.numero_mesa as mesa, "
                    + " mesa.codigo_mesera as  cod_mesera"
                    + " FROM mesa "
                    + " WHERE ( mesa.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "' ) AND ( mesa.estado_orden = 'A' ) "
                    + " UNION "
                    + " SELECT 'Llevar' tipo, "
                    + " llevar.numero_orden, "
                    + " llevar.hora, "
                    + " llevar.valor_total, "
                    + " llevar.numero_mesa, "
                    + " llevar.codigo_mesera "
                    + " FROM llevar "
                    + " WHERE ( llevar.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "' ) AND ( llevar.estado_orden = 'A' ) "
                    + " ORDER BY 2";
            mesasyllevar = this.jdbctemplate.query(query, new BeanPropertyRowMapper<Mesasyllevar>(Mesasyllevar.class));
            calcularTotalMesas(mesasyllevar);
        } catch (Exception e) {
            System.out.println("Error mesas::" + e.getMessage());
        }
        return mesasyllevar;
    }

    public void calcularTotalMesas(List<Mesasyllevar> mesasyllevar) {
        if (mesasyllevar != null) {
            totalvalor = 0D;
            for (Mesasyllevar mesa : mesasyllevar) {
                totalvalor += Double.valueOf(mesa.getValor());
            }
        }
    }

    /**
     * @return the totalvalor
     */
    public Double getTotalvalor() {
        return totalvalor;
    }

    /**
     * @param totalvalor the totalvalor to set
     */
    public void setTotalvalor(Double totalvalor) {
        this.totalvalor = totalvalor;
    }

}
