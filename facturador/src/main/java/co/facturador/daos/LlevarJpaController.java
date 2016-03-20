/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.daos;

import co.facturador.daos.exceptions.NonexistentEntityException;
import co.facturador.daos.exceptions.PreexistingEntityException;
import co.facturador.entity.Llevar;
import co.facturador.entity.LlevarPK;
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
public class LlevarJpaController implements Serializable {

    public LlevarJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Llevar llevar) throws PreexistingEntityException, Exception {
        if (llevar.getLlevarPK() == null) {
            llevar.setLlevarPK(new LlevarPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(llevar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLlevar(llevar.getLlevarPK()) != null) {
                throw new PreexistingEntityException("Llevar " + llevar + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Llevar llevar) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            llevar = em.merge(llevar);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                LlevarPK id = llevar.getLlevarPK();
                if (findLlevar(id) == null) {
                    throw new NonexistentEntityException("The llevar with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(LlevarPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Llevar llevar;
            try {
                llevar = em.getReference(Llevar.class, id);
                llevar.getLlevarPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The llevar with id " + id + " no longer exists.", enfe);
            }
            em.remove(llevar);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Llevar> findLlevarEntities() {
        return findLlevarEntities(true, -1, -1);
    }

    public List<Llevar> findLlevarEntities(int maxResults, int firstResult) {
        return findLlevarEntities(false, maxResults, firstResult);
    }

    private List<Llevar> findLlevarEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Llevar.class));
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

    public Llevar findLlevar(LlevarPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Llevar.class, id);
        } finally {
            em.close();
        }
    }

    public int getLlevarCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Llevar> rt = cq.from(Llevar.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
