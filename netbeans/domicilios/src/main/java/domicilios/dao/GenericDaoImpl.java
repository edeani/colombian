/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author user
 * @param <T>
 */
public  class  GenericDaoImpl<T> implements GenericDao<T>{

    
    protected Class<T> entitytClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
             .getGenericSuperclass();
        this.entitytClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    /*
     * Return all of type 'T' (as determined by the generic member variable type 'entityClass')
     */
    @Override
    public List<T> findAll() {

        try {
            CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entitytClass));
            Query q = entityManager.createQuery(cq);
 
                /*q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);*/

            return q.getResultList();
        } finally {
            entityManager.close();
        }
    }
    
    @SuppressWarnings("query-genric-dao")
    @Override
    public List<T> query(String queryString,HashMap<String,Object> parametros){
        
        try {
            Query query = entityManager.createQuery(queryString);
            if(parametros!=null){
                parametros.entrySet().stream().forEach((entry) -> {
                    query.setParameter(entry.getKey(), entry.getValue());
                });
            }
            return (List<T>) query.getResultList();
        } catch (Exception e) {
            System.out.println("query::"+e.getMessage());
            return null;
        }finally{
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
