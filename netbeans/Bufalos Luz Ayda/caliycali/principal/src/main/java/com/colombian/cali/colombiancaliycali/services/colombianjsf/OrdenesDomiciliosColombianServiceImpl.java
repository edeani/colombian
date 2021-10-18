/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.entidades.Users;
import com.colombian.cali.colombiancaliycali.services.SecurityService;
import com.mycompany.util.Formatos;
import com.mycompany.mapper.OrdenesDomiciliosMapper;
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
public class OrdenesDomiciliosColombianServiceImpl implements OrdenesDomiciliosColombianService {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ProjectsDao projectsDao;

    private JdbcTemplate jdbctemplate;

    private Double totalvalor;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenesDomiciliosMapper> domiciliosordenes(Date fi, Date ff) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(user.getSede().getSede()));
        List<OrdenesDomiciliosMapper> domicilios = new ArrayList<OrdenesDomiciliosMapper>();
        try {
            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

            String query = " SELECT orden.numero_telefono as telefono, "
                    + " orden.numero_orden as orden, "
                    + " clientes.descripcion_cliente as cliente, "
                    + " orden.valor_total as valor, "
                    + " orden.fecha_orden as fecha, "
                    + " barrios.descripcion_barrio as barrio"
                    + " FROM clientes,orden,barrios "
                    + " WHERE ( orden.numero_telefono = clientes.numero_telefono ) and "
                    + " ( barrios.codigo_barrio = clientes.codigo_barrio ) and "
                    + " ( ( orden.fecha_orden between '" + formato.dateTostring(dfDefault.format(fi)) + "' and '" + formato.dateTostring(dfDefault.format(ff)) + "' ) AND "
                    + " ( orden.estado_orden = 'A' ) ) "
                    + " ORDER BY orden.numero_orden ASC ";
            domicilios = this.jdbctemplate.query(query, new BeanPropertyRowMapper<OrdenesDomiciliosMapper>(OrdenesDomiciliosMapper.class));
            
        } catch (Exception e) {
            System.out.println("Error mesas::" + e.getMessage());
        }
        return domicilios;
    }
    
    /**
     * Calcula el valor total en los domicilios
     * @param domicilios 
     */
    public void calcularValorTotal(List<OrdenesDomiciliosMapper> domicilios){
        if(domicilios!=null){
            totalvalor=0D;
            for (OrdenesDomiciliosMapper domicilio : domicilios) {
                totalvalor += Double.valueOf(domicilio.getValor());
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
