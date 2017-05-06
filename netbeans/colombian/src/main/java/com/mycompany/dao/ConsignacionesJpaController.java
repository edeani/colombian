/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dao.exceptions.NonexistentEntityException;
import com.mycompany.entidades.Consignaciones;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author joseefren
 */
public class ConsignacionesJpaController implements Serializable {

    public ConsignacionesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Consignaciones consignaciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(consignaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consignaciones consignaciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            consignaciones = em.merge(consignaciones);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consignaciones.getIdconsignacion();
                if (findConsignaciones(id) == null) {
                    throw new NonexistentEntityException("The consignaciones with id " + id + " no longer exists.");
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
            Consignaciones consignaciones;
            try {
                consignaciones = em.getReference(Consignaciones.class, id);
                consignaciones.getIdconsignacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consignaciones with id " + id + " no longer exists.", enfe);
            }
            em.remove(consignaciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consignaciones> findConsignacionesEntities() {
        return findConsignacionesEntities(true, -1, -1);
    }

    public List<Consignaciones> findConsignacionesEntities(int maxResults, int firstResult) {
        return findConsignacionesEntities(false, maxResults, firstResult);
    }

    private List<Consignaciones> findConsignacionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consignaciones.class));
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

    public Consignaciones findConsignaciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consignaciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsignacionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consignaciones> rt = cq.from(Consignaciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
