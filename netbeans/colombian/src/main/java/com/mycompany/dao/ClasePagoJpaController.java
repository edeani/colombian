/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dao.exceptions.NonexistentEntityException;
import com.mycompany.entidades.ClasePago;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author EderArmando
 */
public class ClasePagoJpaController implements Serializable {

    
    public ClasePagoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClasePago clasePago) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(clasePago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClasePago clasePago) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clasePago = em.merge(clasePago);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = clasePago.getId();
                if (findClasePago(id) == null) {
                    throw new NonexistentEntityException("The clasePago with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClasePago clasePago;
            try {
                clasePago = em.getReference(ClasePago.class, id);
                clasePago.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clasePago with id " + id + " no longer exists.", enfe);
            }
            em.remove(clasePago);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ClasePago> findClasePagoEntities() {
        return findClasePagoEntities(true, -1, -1);
    }

    public List<ClasePago> findClasePagoEntities(int maxResults, int firstResult) {
        return findClasePagoEntities(false, maxResults, firstResult);
    }

    private List<ClasePago> findClasePagoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClasePago.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ClasePago findClasePago(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClasePago.class, id);
        } finally {
            em.close();
        }
    }

    public int getClasePagoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClasePago> rt = cq.from(ClasePago.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
