/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import domicilios.entidad.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

/**
 *
 * @author user
 */
@Repository
public abstract  class  GenericDaoImpl<T> implements GenericDao<T>{

    private Class<T> entitytClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entitytClass = entityClass;
    }

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }
    
    /*
     * Return a list defined by the Criteria
     */
    public List<T> findAll(Criteria criteria) {
        return criteria.list();
    }

    /*
     * Return all of type 'T' (as determined by the generic member variable type 'entityClass')
     */
    
    public List<T> findAll() {

        try {
            CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(this.entitytClass));
            Query q = entityManager.createQuery(cq);
 
                /*q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);*/

            return q.getResultList();
        } finally {
            entityManager.close();
        }
    }
    
     public Class<T> getEntityClass() {
        return this.entitytClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entitytClass = entityClass;
    }
}
