/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Service;
/**
 *
 * @author EderArmando
 */
@Service
public class CustomAuthenticationSuccessHandler  implements AuthenticationSuccessHandler{
     @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        if(StringUtils.hasText(details.getItemId())) {
            //TODO sanity and security check for itemId needed
            System.out.println("Paso por aqui::::jejeje");
            String redirectUrl = "/home.htm";
            response.sendRedirect(redirectUrl);
        }
        throw new IllegalStateException("itemId in authentication details not found");
    }
}
