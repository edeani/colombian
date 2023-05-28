/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.adiministracion.rowmapper.OrdenesDomiciliosDtoRowMapper;
import com.administracion.entidad.Users;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.mycompany.dto.OrdenesDomiciliosDto;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    private ConnectsAuth connectsAuth;

    private JdbcTemplate jdbctemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(OrdenesDomiciliosColombianServiceImpl.class);

    private Double totalvalor;

    @Override
    @Transactional(readOnly = true)
    public List<OrdenesDomiciliosDto> domiciliosordenes(String fi, String ff,String subsede) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        List<OrdenesDomiciliosDto> domicilios = new ArrayList<>();
        try {

            String query = " SELECT orden.numero_telefono as telefono, "
                    + " orden.numero_orden as orden, "
                    + " clientes.descripcion_cliente as cliente, "
                    + " orden.valor_total as valor, "
                    + " orden.fecha_orden as fecha, "
                    + " barrios.descripcion_barrio as barrio"
                    + " FROM clientes,orden,barrios "
                    + " WHERE ( orden.numero_telefono = clientes.numero_telefono ) and "
                    + " ( barrios.codigo_barrio = clientes.codigo_barrio ) and "
                    + " ( ( orden.fecha_orden between '" + fi + "' and '" + ff + "' ) AND "
                    + " ( orden.estado_orden = 'A' ) ) "
                    + " ORDER BY orden.fecha_orden ,orden.numero_orden ";
            domicilios = this.jdbctemplate.query(query, new OrdenesDomiciliosDtoRowMapper());
            
        } catch (DataAccessException e) {
            LOGGER.error("Error mesas::" + e.getMessage());
        }
        return domicilios;
    }
    
    /**
     * Calcula el valor total en los domicilios
     * @param domicilios 
     */
    public void calcularValorTotal(List<OrdenesDomiciliosDto> domicilios){
        if(domicilios!=null){
            totalvalor=0D;
            domicilios.forEach((domicilio) -> {
                totalvalor += Double.valueOf(domicilio.getValor());
            });
        }
    }
    /**
     * @return the totalvalor
     */
    @Override
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
