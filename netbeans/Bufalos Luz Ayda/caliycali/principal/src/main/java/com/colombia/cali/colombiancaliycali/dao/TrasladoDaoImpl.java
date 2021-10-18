/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombia.cali.colombiancaliycali.util.Formatos;
import com.colombian.cali.colombiancaliycali.dto.TrasladosDto;
import com.colombian.cali.colombiancaliycali.entidades.Traslado;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class TrasladoDaoImpl implements TrasladoDao {
    
    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private CaliycaliDao caliycaliDao;
    
    @Override
    public void insertarTraslado(String nameDataSource, Traslado traslado) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            this.jdbctemplate.execute(caliycaliDao.insertJdbTemplate("numero_factura_origen,sede_origen,numero_factura_destino,sede_destino,fecha,usuario", "traslado",
                    traslado.getNumeroFacturaOrigen() + "," + traslado.getIdsedeOrigen() + "," + traslado.getNumeroFacturaDestino() + ","
                    + traslado.getIdsedeDestino() + ",'" + Formatos.dateTostringFechacompleta(traslado.getFecha()) + "'"
                    + "," + traslado.getUsuario()));
        } catch (Exception e) {
            System.out.println("Error de conexión guardarTraslado :" + e.getMessage());
        }
    }
    
    @Override
    public List<TrasladosDto> reporteTraslado(String nameDataSource, Date fechaInicio, Date fechaFin) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<TrasladosDto> traslados = null;
        
        StringBuilder sqlBuilder = new StringBuilder("select date(t.fecha) as fechaTraslado,t.numero_factura_origen as idFacturaOrigen,s1.idsedes as idSedeOrigen,s1.sede as sedeOrigen, "
                + "t.numero_factura_destino as idFacturaDestino ,s2.idsedes as idSedeDestino,s2.sede as sedeDestino "
                + "from traslado t "
                + "inner join sedes s1 on s1.idsedes = t.sede_origen "
                + "inner join sedes s2 on s2.idsedes = t.sede_destino "
                + "where date(t.fecha) between ? and ? "
                + "order by 1 desc");
        try {
            traslados = this.jdbctemplate.query(sqlBuilder.toString(),new Object[]{Formatos.dateTostring(fechaInicio),Formatos.dateTostring(fechaFin)}, new BeanPropertyRowMapper<TrasladosDto>(TrasladosDto.class));
        } catch (DataAccessException e) {
            System.out.println("reporteTraslado ::"+e.getMessage());
        }
        
        return traslados;
    }
}
