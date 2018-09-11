/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;


import com.administracion.dto.NotasDetalleDto;
import com.administracion.dto.NotasDto;
import com.administracion.entidad.NotasDebito;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
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
    public void guardarNotaDebito(DataSource dataSource, NotasDto notasDebito) {
        try {
            if(notasDebito.getDetallesNota()!=null){
                if(notasDebito.getDetallesNota().size()>0){
                    String registros ="";
                    int i=0;
                    for (NotasDetalleDto detNota: notasDebito.getDetallesNota()) {
                        if(i==0){
                            registros = insertJdbTemplate("idsede,fecha,concepto,total,cuenta,descripcion", 
                            "notas_debito",notasDebito.getIdSede()+",'"+notasDebito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"'");
                        }else{
                            registros = addInsertJdtbTemplate(registros,notasDebito.getIdSede()+",'"+notasDebito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"'",i);
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
    public void guardarNotaCredito(DataSource dataSource, NotasDto notasDebito) {
        try {
            if(notasDebito.getDetallesNota()!=null){
                if(notasDebito.getDetallesNota().size()>0){
                    String registros ="";
                    int i=0;
                    for (NotasDetalleDto detNota: notasDebito.getDetallesNota()) {
                        if(i==0){
                            registros = insertJdbTemplate("idsede,fecha,concepto,total,cuenta,descripcion", 
                            "notas_credito",notasDebito.getIdSede()+",'"+notasDebito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"'");
                        }else{
                            registros = addInsertJdtbTemplate(registros,notasDebito.getIdSede()+",'"+notasDebito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"'",i);
                        }
                        i++;
                    }
                    this.jdbcTemplate =  new JdbcTemplate(dataSource);
                    this.jdbcTemplate.execute(registros);
                }
            }
            
        } catch (DataAccessException e) {
            System.out.println("ERROR guardarNotaCredito::"+e.getMessage());
        }
    }
}
