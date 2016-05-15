/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.services;

import co.facturador.entity.Productos;
import java.util.List;

/**
 *
 * @author EderArmando
 */
public interface ProductoService {
   public List<Productos> allProducts();
}
