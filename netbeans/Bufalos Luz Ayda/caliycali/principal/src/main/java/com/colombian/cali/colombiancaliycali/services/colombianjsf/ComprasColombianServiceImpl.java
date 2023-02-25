/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services.colombianjsf;

import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.OperacionesUtil;
import com.colombian.cali.colombiancaliycali.entidades.Users;
import com.colombian.cali.colombiancaliycali.services.SecurityService;
import com.mycompany.dto.ReporteComprasSedeDto;
import com.mycompany.util.Formatos;
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
public class ComprasColombianServiceImpl implements ComprasColombianService{

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ProjectsDao projectsDao;
    
    private JdbcTemplate jdbctemplate;
    
    
    @Override
    @Transactional(readOnly = true)
    public List<ReporteComprasSedeDto> listadoCompras(Date Finicial, Date Ffinal) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(user.getSede().getSede()));
        List<ReporteComprasSedeDto> compras = new ArrayList<ReporteComprasSedeDto>();
        try {
            Formatos formato = new Formatos();
        
        DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        String query = " SELECT inventario.codigo_producto_inventario, "+   
		       " inventario.descripcion_producto,   SUM(detalle_factura.valor_producto) detalle_factura_valor_producto, "+
                       " sum(detalle_factura.numero_unidades) numero_unidades "+
                       " FROM detalle_factura,   factura,   inventario  "+
                       " WHERE ( factura.numero_factura = detalle_factura.numero_factura ) and  " +
                       " ( inventario.codigo_producto_inventario = detalle_factura.codigo_producto_inventario ) and " +  
                       " ( ( factura.fecha_factura between '"+formato.dateTostring(dfDefault.format(Finicial))+"' and '"+formato.dateTostring(dfDefault.format(Ffinal))+"' ) AND  "+
                       " ( factura.estado_factura = 'A' ) )  "+
	               " group by inventario.codigo_producto_inventario,inventario.descripcion_producto ";
            compras = this.jdbctemplate.query(query, new BeanPropertyRowMapper<ReporteComprasSedeDto>(ReporteComprasSedeDto.class));
            OperacionesUtil.promedioCompras(compras);
        } catch (Exception e) {
            System.out.println("Error listadoCompras::" + e.getMessage());
        }
        return compras;
       
    }

}
