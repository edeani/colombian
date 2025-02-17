/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;


import com.administracion.dto.NotasDetalleDto;
import com.administracion.dto.NotasDto;
import com.administracion.entidad.NotasDebito;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class NotasDebitoDaoImpl extends GenericDaoImpl<NotasDebito> implements NotasDebitoDao{


    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void guardarNotaDebitoDetalle(DataSource dataSource, NotasDto notasDebito) {
        try {
            if(notasDebito.getDetallesNota()!=null){
                if(notasDebito.getDetallesNota().size()>0){
                    String registros ="";
                    int i=0;
                    for (NotasDetalleDto detNota: notasDebito.getDetallesNota()) {
                        if(i==0){
                            registros = insertJdbTemplate("idsede,fecha,concepto,total,cuenta,descripcion,id_control_nota_debito", 
                            "notas_debito",notasDebito.getIdSede()+",'"+notasDebito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"',"+notasDebito.getIdControlNotaDebito());
                        }else{
                            registros = addInsertJdtbTemplate(registros,notasDebito.getIdSede()+",'"+notasDebito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"',"+notasDebito.getIdControlNotaDebito(),i);
                        }
                        i++;
                    }
                    this.jdbcTemplate =  new JdbcTemplate(dataSource);
                    this.jdbcTemplate.execute(registros);
                }
            }
            
        } catch (DataAccessException e) {
            System.out.println("ERROR guardarNotaDebito::"+e.getMessage());
        }
    }

    @Override
    public List<NotasDetalleDto> consultarDetalleNotaDebito(DataSource dataSource, int idComprobante) {
        this.jdbcTemplate =  new JdbcTemplate(dataSource);
        List<NotasDetalleDto> notasDebito = new ArrayList<>();
        try {
            notasDebito = this.jdbcTemplate.query(selectJdbTemplate("*", "notas_debito", 
                    String.format(" id_control_nota_debito=%d",idComprobante)),new BeanPropertyRowMapper<>(NotasDetalleDto.class));
        } catch (DataAccessException e) {
            System.out.println("ERROR consultarDetalleNotaDebito::"+e.getMessage());
        }
        
        return notasDebito;
    }
}
