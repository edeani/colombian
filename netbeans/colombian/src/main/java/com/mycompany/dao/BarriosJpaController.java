/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dao.exceptions.NonexistentEntityException;
import com.mycompany.dao.exceptions.PreexistingEntityException;
import com.mycompany.entidades.Barrios;
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
public class BarriosJpaController implements Serializable {

    public BarriosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Barrios barrios) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(barrios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBarrios(barrios.getCodigoBarrio()) != null) {
                throw new PreexistingEntityException("Barrios " + barrios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Barrios barrios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            barrios = em.merge(barrios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = barrios.getCodigoBarrio();
                if (findBarrios(id) == null) {
                    throw new NonexistentEntityException("The barrios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Barrios barrios;
            try {
                barrios = em.getReference(Barrios.class, id);
                barrios.getCodigoBarrio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The barrios with id " + id + " no longer exists.", enfe);
            }
            em.remove(barrios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Barrios> findBarriosEntities() {
        return findBarriosEntities(true, -1, -1);
    }

    public List<Barrios> findBarriosEntities(int maxResults, int firstResult) {
        return findBarriosEntities(false, maxResults, firstResult);
    }

    private List<Barrios> findBarriosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Barrios.class));
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

    public Barrios findBarrios(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Barrios.class, id);
        } finally {
            em.close();
        }
    }

    public int getBarriosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Barrios> rt = cq.from(Barrios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
