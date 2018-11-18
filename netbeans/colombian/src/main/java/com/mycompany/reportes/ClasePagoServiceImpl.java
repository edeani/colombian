/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.reportes;

import com.mycompani.bean.util.UserSessionBean;
import com.mycompany.dao.ClasePagoJpaController;
import com.mycompany.entidades.ClasePago;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author EderArmando
 */
public class ClasePagoServiceImpl implements ClasePagoService{

    private ClasePagoJpaController clasePagoJpa;
    private final UserSessionBean user = UserSessionBean.getInstance();
    private final String password = user.getSede().getPassword();
    @Override
    public boolean pagoServiceXSede(String tipoPago) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(user.getNombrePersistencia());
        EntityManager em = factory.createEntityManager();
        
        clasePagoJpa = new  ClasePagoJpaController(factory);
        ClasePago clasePago = null;
        try {
           String consulta = "SELECT c FROM ClasePago c WHERE c.idsede = :idsede and c.descripcion = :tipoPago";
           Query query = em.createQuery(consulta);
           query.setParameter("tipoPago", tipoPago);
           query.setParameter("idsede", user.getSede().getSed_cod());
           clasePago =  (ClasePago) query.getSingleResult();
        }catch(Exception e){
            System.out.println("ERROR pagoServiceXSede::"+e.getMessage());
        } finally {
            em.close();
        }
        return clasePago!=null;
    }
    
}
