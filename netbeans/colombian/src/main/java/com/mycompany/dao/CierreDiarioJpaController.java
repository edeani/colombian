/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dao.exceptions.NonexistentEntityException;
import com.mycompany.dao.exceptions.PreexistingEntityException;
import com.mycompany.entidades.CierreDiario;
import com.mycompany.entidades.CierreDiarioPK;
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
public class CierreDiarioJpaController implements Serializable {

    public CierreDiarioJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CierreDiario cierreDiario) throws PreexistingEntityException, Exception {
        if (cierreDiario.getCierreDiarioPK() == null) {
            cierreDiario.setCierreDiarioPK(new CierreDiarioPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cierreDiario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCierreDiario(cierreDiario.getCierreDiarioPK()) != null) {
                throw new PreexistingEntityException("CierreDiario " + cierreDiario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CierreDiario cierreDiario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cierreDiario = em.merge(cierreDiario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CierreDiarioPK id = cierreDiario.getCierreDiarioPK();
                if (findCierreDiario(id) == null) {
                    throw new NonexistentEntityException("The cierreDiario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CierreDiarioPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CierreDiario cierreDiario;
            try {
                cierreDiario = em.getReference(CierreDiario.class, id);
                cierreDiario.getCierreDiarioPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cierreDiario with id " + id + " no longer exists.", enfe);
            }
            em.remove(cierreDiario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CierreDiario> findCierreDiarioEntities() {
        return findCierreDiarioEntities(true, -1, -1);
    }

    public List<CierreDiario> findCierreDiarioEntities(int maxResults, int firstResult) {
        return findCierreDiarioEntities(false, maxResults, firstResult);
    }

    private List<CierreDiario> findCierreDiarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CierreDiario.class));
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

    public CierreDiario findCierreDiario(CierreDiarioPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CierreDiario.class, id);
        } finally {
            em.close();
        }
    }

    public int getCierreDiarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CierreDiario> rt = cq.from(CierreDiario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
