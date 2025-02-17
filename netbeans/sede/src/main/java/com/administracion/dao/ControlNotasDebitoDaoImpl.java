/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.dao;

import com.adiministracion.rowmapper.FacturaRowMapper;
import com.administracion.dto.NotasDto;
import com.administracion.entidad.ControlNotasCredito;
import com.administracion.entidad.ControlNotasDebito;
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
public class ControlNotasDebitoDaoImpl extends GenericDaoImpl<ControlNotasDebito> implements ControlNotasDebitoDao{
    
    private JdbcTemplate jdbcTemplateControlNC;

    @Override
    public Integer guardarNotaDebitoControl(DataSource dataSource, NotasDto notasDebito, Users user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplateControlNC =  new JdbcTemplate(dataSource);
        
        String paramControlNC ="idsede, fecha, total, idusuario, usuario, fecha_creacion";
        String fechaCreacion = (new Formatos()).dateTostring(new Date(), ConstantsColombianJsf.Formatos.FORMAT_HOUR);
        String valuesControlNC = "?,?,?,?,?,?";
        String sqlSaveControlNC = insertJdbTemplate(paramControlNC, "control_notas_debito", valuesControlNC);
        
        jdbcTemplateControlNC.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sqlSaveControlNC, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, notasDebito.getIdSede());
            ps.setString(2, notasDebito.getFecha());
            ps.setFloat(3, notasDebito.getTotalPago());
            ps.setLong(4, user.getCedula());
            ps.setString(5, user.getUsername());
            ps.setString(6, fechaCreacion);

            return ps;
        }, keyHolder);
        //Long lastId = (long) keyHolder.getKey().intValue();
        return keyHolder.getKey().intValue();
    }    

    @Override
    public ControlNotasDebito getNotaDebitoControl(DataSource dataSource, int idComprobante) {
        this.jdbcTemplateControlNC = new JdbcTemplate(dataSource);
        String sql = "select * from control_notas_debito where id_control_notad = "+idComprobante;
        ControlNotasDebito comprobante = null;
        try {
            comprobante = (ControlNotasDebito) this.jdbcTemplateControlNC.queryForObject(sql,  new BeanPropertyRowMapper<>(ControlNotasDebito.class));
        } catch (DataAccessException e) {
            System.out.println("getNotaDebitoControl::"+e.getMessage());
        }
        return comprobante;
    }
}
