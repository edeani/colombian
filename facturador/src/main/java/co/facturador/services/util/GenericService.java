/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.services.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author EderArmando
 */
public class GenericService {
    private EntityManagerFactory factory;
    protected static final String PERSISTENCIA ="facturador_jar.0PU";
    public GenericService(){
        factory = Persistence.createEntityManagerFactory(PERSISTENCIA);
    }

    public EntityManagerFactory getFactory() {
        return factory;
    }

    public void setFactory(EntityManagerFactory factory) {
        this.factory = factory;
    }
}
