/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.util.ManageCookies;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 *
 * @author EderArmando
 */
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
        String path = ManageCookies.getCookieValue(hsr, "logoutpath");
        String redirectUrl = hsr.getContextPath() + "/" + path + "/home.htm";
        ManageCookies.eraseCookie("logoutpath", hsr1, hsr);
        hsr1.sendRedirect(redirectUrl);
    }

}
