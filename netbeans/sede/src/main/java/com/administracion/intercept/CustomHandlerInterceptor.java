/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.intercept;

import com.administracion.entidad.Sedes;
import com.administracion.service.SedesService;
import com.administracion.service.autorizacion.AccesosSubsedes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author EderArmando
 */
public class CustomHandlerInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AccesosSubsedes accesosSubsedes;
    @Autowired
    private SedesService sedesService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String arrUri[] = request.getRequestURI().split("/");
        final String slash = "/";
        if (arrUri.length > 3) {
            //No hay logins
            HttpSession session = request.getSession();
            if (accesosSubsedes.getSedes().isEmpty()) {
                if (!arrUri[arrUri.length - 1].equals("signin.htm")) {
                    if (existSede(arrUri[2])!=null) {
                        response.sendRedirect(request.getContextPath() + slash + arrUri[2] + "/signin.htm");
                    } else {
                        response.sendRedirect(request.getContextPath() + slash + "404.htm");
                    }
                    return false;
                } else {
                    Sedes sedeManage = existSede(arrUri[2]);
                    if (sedeManage == null) {
                        response.sendRedirect(request.getContextPath() + slash + "404.htm");
                        return false;
                    }
                }
            } else {//Hay Logins
                if (!arrUri[arrUri.length - 1].equals("signin.htm")) {
                    String sedeManage = existSedeAccesos(arrUri[2]);
                    if (sedeManage != null) {//Si esa sede esta logueada
                        session.setAttribute("path", arrUri[2]);
                        return true;
                    } else {//Si esa sede NO esta logueada
                        Sedes sede = existSede(arrUri[2]);
                        if (sede == null) {//Not found
                            response.sendRedirect(request.getContextPath() + slash + "404.htm");
                        } else {//Login de la sede
                            session.setAttribute("path", arrUri[2]);
                            response.sendRedirect(request.getContextPath() + slash + arrUri[2] + "/signin.htm");
                        }
                        return false;
                    }
                } else {
                    if (existSedeAccesos(arrUri[2]) != null) {
                        return true;
                    } else {
                        if (existSede(arrUri[2]) == null) {
                            response.sendRedirect(request.getContextPath() + slash + "404.htm");
                            return false;
                        }
                    }
                }
            }

        }

        return true;
    }

    private Sedes existSede(String nameSede) {
        return sedesService.findSedeXName(nameSede);
    }

    private String existSedeAccesos(String nameSede) {
        return accesosSubsedes.findSedeStringXName(nameSede);
    }

}
