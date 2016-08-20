/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.dao;

import com.colombian.cali.colombiancaliycali.entidades.Sedes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class SedesDaoImpl implements SedesDao {

    private EntityManager entityManager;

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
            System.out.println("SEDES::PERSIST");
            e.printStackTrace();
        }


    }

    @Override
    public List<Sedes> listSedes() {
         List<Sedes> sedes = null;
         try {
             Query query = entityManager.createQuery("select s from Sedes s");
             sedes = query.getResultList();
        } catch (Exception e) {
            System.out.println("SEDES::LISTA");
            e.printStackTrace();
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
            System.out.println("Error findSede");
        }
        return sede;
    }
    
}
