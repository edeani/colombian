/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.jsf;

import com.administracion.dto.GastosDto;
import com.administracion.dto.GastosNivel2Dto;
import com.administracion.dto.GastosNivel3Dto;
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
    public List<GastosDto> gastos(String fi, String ff, String subsede) {
        this.jdbctemplate = new JdbcTemplate(connectsAuth.getDataSourceSubSede(subsede));
        List<ReporteGastosDto> gastosTotales = new ArrayList<>();
        List<GastosDto> gastosXNivel = new ArrayList<>();
        try {
            Formatos formato = new Formatos();
            DateFormat dfDefault = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);

            String f1 = fi;
            String f2 = ff;

            String query = "select c1.con_cod as cod1,c1.con_nombre as nombre1,c1.con_nivel as nivel1,c1.con_padre as padre1,c2.con_cod as cod2,c2.con_nombre as nombre2,c2.con_nivel as nivel2,c2.con_padre as padre2,c3.con_cod as cod3,c3.con_nombre as nombre3,c3.con_nivel as nivel3,c3.con_padre as padre3"
                    + ",sum(g.gas_valor) as valor_gastos,sum(g.gas_cantidad) as cantidad from concepto c1 "
                    + " inner join concepto c2 on c2.con_cod <> c1.con_cod and c2.con_padre = c1.con_cod "
                    + " inner join concepto c3 on c3.con_cod <> c2.con_cod and c3.con_padre = c2.con_cod "
                    + " inner join gastos g on g.gas_concepto = c3.con_cod "
                    + " where (g.gas_fecha between '" + f1 + "'  and '" + f2 + "') "
                    + " group by c1.con_nivel, g.gas_concepto "
                    + " order by c1.con_cod,c2.con_cod";
            gastosTotales = this.jdbctemplate.query(query, new BeanPropertyRowMapper<>(ReporteGastosDto.class));

            if (gastosTotales != null) {
                String cod = "";
                String cod2 = "";

                GastosNivel2Dto g2 = new GastosNivel2Dto();
                GastosDto g = new GastosDto();
                for (int i = 0; i < gastosTotales.size(); i++) {
                    //GastosDto g = new GastosDto();
                    ReporteGastosDto gastoTotal = new ReporteGastosDto();
                    if (i < gastosTotales.size()) {
                        gastoTotal = gastosTotales.get(i);
                    } else {
                        gastoTotal = gastosTotales.get(i - 1);
                    }
                    GastosNivel3Dto g3 = new GastosNivel3Dto();
                    g3.setCod(gastoTotal.getCod3());
                    g3.setNivel(gastoTotal.getNivel3());
                    g3.setNombre(gastoTotal.getNombre3());
                    g3.setPadre(gastoTotal.getPadre3());
                    g3.setCantidad(gastoTotal.getCantidad());
                    g3.setValor_gastos(gastoTotal.getValor_gastos());
                    if (!cod2.equals(gastoTotal.getCod2())) {
                        if (g2.getCod() != null) {
                            g.getNivel2().add(g2);
                        }
                        cod2 = gastoTotal.getCod2();
                        g2 = new GastosNivel2Dto();

                        g2.getNivel3().add(g3);
                        g2.setValor_gastos(g3.getValor_gastos());

                        g2.setNivel(gastoTotal.getNivel2());
                        g2.setCod(gastoTotal.getCod2());
                        g2.setNombre(gastoTotal.getNombre2());
                        g2.setPadre(gastoTotal.getPadre2());
                    } else {
                        g2.setValor_gastos(g3.getValor_gastos() + g2.getValor_gastos());
                        g2.getNivel3().add(g3);
                    }

                    if (!cod.equals(gastoTotal.getCod1())) {
                        cod = gastoTotal.getCod1();
                        g = new GastosDto();
                        g.setValor_gastos(g3.getValor_gastos());

                        g.setNivel(gastoTotal.getNivel1());
                        g.setCod(gastoTotal.getCod1());
                        g.setNombre(gastoTotal.getNombre1());
                        g.setPadre(gastoTotal.getPadre1());

                        gastosXNivel.add(g);

                        if (gastosTotales.size() - 1 == i) {
                            cod2 = gastoTotal.getCod2();
                            g2 = new GastosNivel2Dto();

                            g2.getNivel3().add(g3);
                            g2.setValor_gastos(g3.getValor_gastos());

                            g2.setNivel(gastoTotal.getNivel2());
                            g2.setCod(gastoTotal.getCod2());
                            g2.setNombre(gastoTotal.getNombre2());
                            g2.setPadre(gastoTotal.getPadre2());
                            g.getNivel2().add(g2);
                        }
                    } else {
                        g.setValor_gastos(g3.getValor_gastos() + g.getValor_gastos());
                    }

                }
            }
            System.out.println("fin");
        } catch (DataAccessException e) {
            System.out.println("Error cuadreDia::" + e.getMessage());
        }
        return gastosXNivel;
    }

}
