/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.daos;

import co.facturador.daos.exceptions.NonexistentEntityException;
import co.facturador.entity.ProductoPorInventario;
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
public class ProductoPorInventarioJpaController implements Serializable {

    public ProductoPorInventarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoPorInventario productoPorInventario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(productoPorInventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoPorInventario productoPorInventario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            productoPorInventario = em.merge(productoPorInventario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productoPorInventario.getId();
                if (findProductoPorInventario(id) == null) {
                    throw new NonexistentEntityException("The productoPorInventario with id " + id + " no longer exists.");
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
            ProductoPorInventario productoPorInventario;
            try {
                productoPorInventario = em.getReference(ProductoPorInventario.class, id);
                productoPorInventario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoPorInventario with id " + id + " no longer exists.", enfe);
            }
            em.remove(productoPorInventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoPorInventario> findProductoPorInventarioEntities() {
        return findProductoPorInventarioEntities(true, -1, -1);
    }

    public List<ProductoPorInventario> findProductoPorInventarioEntities(int maxResults, int firstResult) {
        return findProductoPorInventarioEntities(false, maxResults, firstResult);
    }

    private List<ProductoPorInventario> findProductoPorInventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoPorInventario.class));
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

    public ProductoPorInventario findProductoPorInventario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoPorInventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoPorInventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoPorInventario> rt = cq.from(ProductoPorInventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
