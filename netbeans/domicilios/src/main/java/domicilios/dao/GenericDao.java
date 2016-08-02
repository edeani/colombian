/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class GenericDao{
    private EntityManager entityManager;
    
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }
    
    
    
}
