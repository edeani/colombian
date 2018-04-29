/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;


import com.administracion.dto.ReporteGastosDto;
import com.administracion.entidad.Users;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.service.autorizacion.SecurityService;
import com.mycompany.dto.GastosColombianDto;
import com.mycompany.dto.GastosReporteTotalColombialDto;
import com.mycompany.mapper.GastosMapper;
import com.mycompany.util.Formatos;
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

/**
 *
 * @author joseefren
 */
@Service
public class GastosColombianServiceImpl implements GastosColombianService {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private ConnectsAuth connectsAuth;

    private JdbcTemplate jdbctemplate;


    @Override
    public  List<ReporteGastosDto> gastos(String fi, String ff,String subsede) {
        Users user = securityService.getCurrentUser();
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        List<ReporteGastosDto> gastosTotales = new ArrayList<>();
        try {
            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

            String f1 = fi;
            String f2 = ff;

            String query = "select c1.con_cod as cod1,c1.con_nombre as nombre1,c1.con_nivel as nivel1,c1.con_padre as padre1,c2.con_cod as cod2,c2.con_nombre as nombre2,c2.con_nivel as nivel2,c2.con_padre as padre2,c3.con_cod as cod3,c3.con_nombre as nombre3,c3.con_nivel as nivel3,c3.con_padre as padre3"+
             ",sum(g.gas_valor) as valor_gastos,sum(g.gas_cantidad) as cantidad from concepto c1 "
                    + " inner join concepto c2 on c2.con_cod <> c1.con_cod and c2.con_padre = c1.con_cod "
                    + " inner join concepto c3 on c3.con_cod <> c2.con_cod and c3.con_padre = c2.con_cod "
                    + " inner join gastos g on g.gas_concepto = c3.con_cod "
                    + " where (g.gas_fecha between '" + f1 + "'  and '" + f2 + "') "
                    + " group by c1.con_nivel, g.gas_concepto "
                    + " order by c1.con_cod,c2.con_cod";
            gastosTotales = this.jdbctemplate.query(query, new BeanPropertyRowMapper<>(ReporteGastosDto.class));
        } catch (DataAccessException e) {
            System.out.println("Error cuadreDia::" + e.getMessage());
        }
       return  gastosTotales;
    }

}
