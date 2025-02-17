/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.dao;

import com.administracion.dto.NotasDetalleDto;
import com.administracion.dto.NotasDto;
import com.administracion.dto.TrasladosDto;
import com.administracion.entidad.NotasCredito;
import com.administracion.util.Formatos;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anlod
 */
@Repository
public class NotasCreditoDaoImpl extends GenericDaoImpl<NotasCredito> implements  NotasCreditoDao{
    
    private JdbcTemplate jdbcTemplate;

    @Override
    public void guardarNotaCreditoDetalle(DataSource dataSource, NotasDto notasCredito) {
        try {
            if(notasCredito.getDetallesNota()!=null){
                if(!notasCredito.getDetallesNota().isEmpty()){
                    String registros ="";
                    int i=0;
                    for (NotasDetalleDto detNota: notasCredito.getDetallesNota()) {
                        if(i==0){
                            registros = insertJdbTemplate("idsede,fecha,concepto,total,cuenta,descripcion,id_control_nota_credito", 
                            "notas_credito",notasCredito.getIdSede()+",'"+notasCredito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"',"+notasCredito.getIdControlNotaCredito());
                        }else{
                            registros = addInsertJdtbTemplate(registros,notasCredito.getIdSede()+",'"+notasCredito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"',"+notasCredito.getIdControlNotaCredito(),i);
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
    
    @Override
    public List<NotasDetalleDto> consultarDetalleNotaCredito(DataSource dataSource, int idComprobante){
        this.jdbcTemplate =  new JdbcTemplate(dataSource);
        List<NotasDetalleDto> notasCredito = new ArrayList<>();
        try {
            notasCredito = this.jdbcTemplate.query(selectJdbTemplate("*", "notas_credito", 
                    String.format(" id_control_nota_credito=%d",idComprobante)),new BeanPropertyRowMapper<>(NotasDetalleDto.class));
        } catch (DataAccessException e) {
            System.out.println("ERROR consultarDetalleNotaCredito::"+e.getMessage());
        }
        
        return notasCredito;
    }
    
}
