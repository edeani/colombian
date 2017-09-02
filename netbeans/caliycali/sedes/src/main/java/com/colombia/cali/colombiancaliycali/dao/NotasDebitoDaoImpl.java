/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.DataGenericDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.NotasDetalleDto;
import com.colombian.cali.colombiancaliycali.dto.NotasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author EderArmando
 */
@Repository
public class NotasDebitoDaoImpl extends DataGenericDao implements NotasDebitoDao{

    @Autowired
    private ProjectsDao projectsDao;
    
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void guardarNotaDebito(String dataSource, NotasDto notasDebito) {
        try {
            if(notasDebito.getDetallesNota()!=null){
                if(notasDebito.getDetallesNota().size()>0){
                    String registros ="";
                    int i=0;
                    for (NotasDetalleDto detNota: notasDebito.getDetallesNota()) {
                        if(i==0){
                            registros = caliycaliDao.insertJdbTemplate("idsede,fecha,concepto,total,cuenta,descripcion", 
                            "notas_debito",notasDebito.getIdSede()+",'"+notasDebito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"'");
                        }else{
                            registros = caliycaliDao.addInsertJdtbTemplate(registros,notasDebito.getIdSede()+",'"+notasDebito.getFecha()+"','"+
                            detNota.getConcepto()+"',"+detNota.getTotal()+",'"+detNota.getCuenta()+"','"+detNota.getDetalle()+"'",i);
                        }
                        i++;
                    }
                    this.jdbcTemplate =  new JdbcTemplate(projectsDao.getDatasource(dataSource));
                    this.jdbcTemplate.execute(registros);
                }
            }
            
        } catch (DataAccessException e) {
            System.out.println("ERROR guardarNotaDebito::"+e.getMessage());
        }
    }
    
}
