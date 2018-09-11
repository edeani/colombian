/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dao;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author user
 * @param <T>
 */
public class GenericDaoImpl<T> implements GenericDao<T> {

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

    @SuppressWarnings("query-genric-jpa-dao")
    @Override
    public T queryOpjectJpa(String queryString, HashMap<String, Object> parametros) {

        try {
            Query query = entityManager.createQuery(queryString);
            if (parametros != null) {
                parametros.entrySet().stream().forEach((entry) -> {
                    query.setParameter(entry.getKey(), entry.getValue());
                });
            }
            return (T) query.getSingleResult();
        } catch (Exception e) {
            System.out.println("query::" + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

    @SuppressWarnings("query-genric-jpa-dao")
    @Override
    public List<T> queryJpa(String queryString, HashMap<String, Object> parametros) {

        try {
            Query query = entityManager.createQuery(queryString);
            if (parametros != null) {
                parametros.entrySet().stream().forEach((entry) -> {
                    query.setParameter(entry.getKey(), entry.getValue());
                });
            }
            return (List<T>) query.getResultList();
        } catch (Exception e) {
            System.out.println("query::" + e.getMessage());
            return null;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<T> findAll(Integer max, Integer first) {

        try {
            CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entitytClass));
            Query q = entityManager.createQuery(cq);
            q.setMaxResults(max);
            q.setFirstResult(first);
            return q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(T objeto) {
        this.entityManager.persist(objeto);
    }

    @Override
    public void Update(T objeto) {
        this.entityManager.merge(objeto);
    }

    @Override
    public void delete(T objeto) {
        this.entityManager.remove(objeto);
    }

    @Override
    public T findById(Object id) {
        return this.entityManager.find(entitytClass, id);
    }

    public Class<T> getEntityClass() {
        return this.entitytClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entitytClass = entityClass;
    }

    @Override
    public String selectJdbTemplate(String parametros, String tabla, String condiciones) {
        StringBuilder sql = new StringBuilder("");
        sql.append("SELECT ").append(parametros).append(" FROM ").append(tabla);
        if (condiciones != null && !"".equals(condiciones)) {
            sql.append(" WHERE ").append(condiciones);
        }
        System.out.println(sql.toString());
        return sql.toString();
    }

    @Override
    public String updateJdbTemplate(String parametros, String tabla, String condiciones) {
        StringBuilder sql = new StringBuilder("");
        sql.append("UPDATE ").append(tabla).append(" SET ").append(parametros);
        if (condiciones != null && !"".equals(condiciones)) {
            sql.append(" WHERE ").append(condiciones);
        }
        return sql.toString();
    }

    @Override
    public String insertJdbTemplate(String parametros, String tabla, String condiciones) {
        StringBuilder sql = new StringBuilder("");
        sql.append("INSERT INTO ").append(tabla).append(" (").append(parametros).append(") VALUES (").append(condiciones).append(")");
        return sql.toString();
    }

    @Override
    public String deleteJdbTemplate(String tabla, String condiciones) {
        StringBuilder sql = new StringBuilder("");
        sql.append("DELETE FROM ").append(tabla);
        if (condiciones != null && !"".equals(condiciones)) {
            sql.append(" WHERE ").append(condiciones);
        }
        return sql.toString();
    }

    @Override
    public String addInsertJdtbTemplate(String values1, String values2, int iteracion) {
        if (iteracion == 0) {
            return values2;
        }
        return values1 + ",(" + values2 + ")";
    }

}
