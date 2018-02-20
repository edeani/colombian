/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class SedesDaoImpl implements SedesDao {

    private EntityManager entityManager;
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private ProjectsDao projectsDao;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext(unitName = "CaliyCali")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void guardarSede(Sedes sede) {
        try {
            entityManager.persist(sede);
        } catch (Exception e) {
            System.out.println("SEDES::PERSIST::"+e.getMessage());
        }


    }

    @Override
    public List<Sedes> listSedes() {
         List<Sedes> sedes = null;
         try {
             Query query = entityManager.createQuery("select s from Sedes s");
             sedes = query.getResultList();
        } catch (Exception e) {
            System.out.println("SEDES::LISTA::"+e.getMessage());
        }
        return sedes;
    }

    @Override
    public Sedes findSede(Long idSede) {
        Sedes sede = null;
        try {
            Query query = entityManager.createQuery("select s from Sedes s where s.idsedes="+idSede);
            sede = (Sedes) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("Error findSede::"+e.getMessage());
        }
        return sede;
    }

    @Override
    public Sedes findSedeByName(String nameDataSource,String nombresede) {
        this.jdbcTemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        try {
            return this.jdbcTemplate.queryForObject("select * from sedes where sede='"+nombresede+"'", new BeanPropertyRowMapper<Sedes>(Sedes.class));
        } catch (DataAccessException e) {
            System.out.println("Error findSedeByName");
        }
        return null;
    }

}
