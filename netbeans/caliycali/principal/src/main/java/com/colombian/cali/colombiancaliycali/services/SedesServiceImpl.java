/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.cali.colombiancaliycali.services;

import com.colombia.cali.colombiancaliycali.dao.SedesDao;
import com.colombia.cali.colombiancaliycali.dao.SubPrincipalDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import com.colombian.cali.colombiancaliycali.entidades.Subprincipal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author edeani
 */
@Service
public class SedesServiceImpl implements SedesService {

    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    @Autowired
    private SedesDao sedesDao;
    /*@Autowired
    private SubPrincipalDao subPrincipalDao;*/
    @Override
    @Transactional
    public List<ItemsDTO> listaSedesOptions(String nameDatasource) {
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        return jdbctemplate.query("select idsedes as id, sede as label from sedes", new BeanPropertyRowMapper(ItemsDTO.class));
    }

    @Override
    @Transactional
    public List<Sedes> traerSedes(String nameDatasource) {
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        return jdbctemplate.query("select * from sedes", new BeanPropertyRowMapper(Sedes.class));
    }

    @Override
    @Transactional(readOnly = true)
    public Sedes buscarSede(String nameDatasource, Long idSede) {
        jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDatasource));
        Sedes sede = null;
        try {
            sede = (Sedes) jdbctemplate.queryForObject("select * from sedes where idsedes = ?", new Object[]{idSede}, new BeanPropertyRowMapper(Sedes.class));
        } catch (Exception e) {
            System.out.println("Se encontraron 0 registros. ERROR: "+e.getMessage());
        }
        return sede;
    }
    
    /*@Transactional(readOnly = true)
    @Override
    public Subprincipal findSubPrincipalByIdsede(String nameDatasource, Integer idSede) {
        return subPrincipalDao.findSubPrincipalByIdsede(nameDatasource, idSede);
    }*/
    @Transactional(readOnly = true)
    @Override
    public Sedes buscarSedeXNombre(String nameDatasource, String nombresede) {
       return sedesDao.findSedeByName(nameDatasource, nombresede);
    }
}
