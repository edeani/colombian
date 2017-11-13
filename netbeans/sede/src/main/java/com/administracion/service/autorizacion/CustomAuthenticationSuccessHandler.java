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

            String path = (String) request.getSession().getAttribute("path");
            String redirectUrl = "";
            if(path!=null){
                redirectUrl = request.getContextPath()+"/"+path+"/home.htm";
            }else{
                redirectUrl = request.getContextPath()+"/404.htm";
            }
            response.sendRedirect(redirectUrl);
        
    }
}
