/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.dao;

import com.adiministracion.rowmapper.FacturaRowMapper;
import com.administracion.dto.NotasDto;
import com.administracion.entidad.ControlNotasCredito;
import com.administracion.entidad.Users;
import com.mycompany.util.ConstantsColombianJsf;
import com.mycompany.util.Formatos;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anlod
 */
@Repository
public class ControlNotasCreditoDaoImpl extends GenericDaoImpl<ControlNotasCredito> implements ControlNotasCreditoDao{
    
    private JdbcTemplate jdbcTemplateControlNC;

    @Override
    public Integer guardarNotaCreditoControl(DataSource dataSource, NotasDto notasCredito, Users user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplateControlNC =  new JdbcTemplate(dataSource);
        
        String paramControlNC ="idsede, fecha, total, idusuario, usuario, fecha_creacion";
        String fechaCreacion = (new Formatos()).dateTostring(new Date(), ConstantsColombianJsf.Formatos.FORMAT_HOUR);
        String valuesControlNC = "?,?,?,?,?,?";
        String sqlSaveControlNC = insertJdbTemplate(paramControlNC, "control_notas_credito", valuesControlNC);
        
        jdbcTemplateControlNC.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sqlSaveControlNC, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, notasCredito.getIdSede());
            ps.setString(2, notasCredito.getFecha());
            ps.setFloat(3, notasCredito.getTotalPago());
            ps.setLong(4, user.getCedula());
            ps.setString(5, user.getUsername());
            ps.setString(6, fechaCreacion);

            return ps;
        }, keyHolder);
        //Long lastId = (long) keyHolder.getKey().intValue();
        return keyHolder.getKey().intValue();
    }    

    @Override
    public ControlNotasCredito getNotaCreditoControl(DataSource dataSource, int idComprobante) {
        this.jdbcTemplateControlNC = new JdbcTemplate(dataSource);
        String sql = "select * from control_notas_credito where id_control_notac = "+idComprobante;
        ControlNotasCredito comprobante = null;
        try {
            comprobante = (ControlNotasCredito) this.jdbcTemplateControlNC.queryForObject(sql,  new BeanPropertyRowMapper<>(ControlNotasCredito.class));
        } catch (DataAccessException e) {
            System.out.println("getNotaCreditoControl::"+e.getMessage());
        }
        return comprobante;
    }
}
