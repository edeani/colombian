/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author EderArmando
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails{
     //private final String itemId;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
       // itemId = request.getParameter("itemId");
    }

   /* public String getItemId() {
        return itemId;
    }*/


}
