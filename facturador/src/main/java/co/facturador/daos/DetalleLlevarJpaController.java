/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.daos;

import co.facturador.daos.exceptions.NonexistentEntityException;
import co.facturador.entity.DetalleLlevar;
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
public class DetalleLlevarJpaController implements Serializable {

    public DetalleLlevarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DetalleLlevar detalleLlevar) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(detalleLlevar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DetalleLlevar detalleLlevar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            detalleLlevar = em.merge(detalleLlevar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = detalleLlevar.getId();
                if (findDetalleLlevar(id) == null) {
                    throw new NonexistentEntityException("The detalleLlevar with id " + id + " no longer exists.");
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
            DetalleLlevar detalleLlevar;
            try {
                detalleLlevar = em.getReference(DetalleLlevar.class, id);
                detalleLlevar.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleLlevar with id " + id + " no longer exists.", enfe);
            }
            em.remove(detalleLlevar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DetalleLlevar> findDetalleLlevarEntities() {
        return findDetalleLlevarEntities(true, -1, -1);
    }

    public List<DetalleLlevar> findDetalleLlevarEntities(int maxResults, int firstResult) {
        return findDetalleLlevarEntities(false, maxResults, firstResult);
    }

    private List<DetalleLlevar> findDetalleLlevarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DetalleLlevar.class));
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

    public DetalleLlevar findDetalleLlevar(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DetalleLlevar.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleLlevarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DetalleLlevar> rt = cq.from(DetalleLlevar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
