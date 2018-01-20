/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.intercept;

import com.administracion.dto.SedesDto;
import com.administracion.dto.SubSedesDto;
import com.administracion.entidad.Sedes;
import com.administracion.enumeration.ExtencionesEnum;
import com.administracion.service.SedesService;
import com.administracion.service.autorizacion.AccesosSubsedes;
import com.administracion.service.autorizacion.ConnectsAuth;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
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
    @Autowired 
    private ConnectsAuth connectsAuth;
    @Value("${url.img}")
    String rutaLogo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String arrUri[] = request.getRequestURI().split("/");
        final String slash = "/";
        if (arrUri.length > 3 && !request.getRequestURI().contains("WEB-INF")) {
            //No hay logins
            if (!arrUri[2].equals("sedes")) {
                HttpSession session = request.getSession();
                if (accesosSubsedes.getSedes().isEmpty()) {
                    if (!arrUri[arrUri.length - 1].equals("signin.htm")) {
                        if (existSede(arrUri[2]) != null) {
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
                            //return true;
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
                            response.sendRedirect(request.getContextPath() + slash + arrUri[2] + "/home.htm");
                            session.setAttribute("path", arrUri[2]);
                            return false;
                        } else {
                            if (existSede(arrUri[2]) == null) {
                                response.sendRedirect(request.getContextPath() + slash + "404.htm");
                                return false;
                            }
                        }
                    }
                }

            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        if (session != null) {
            String sedeString = (String) session.getAttribute("path");
            if (sedeString != null) {
                SedesDto sedesDto = connectsAuth.findSedeXNameConn(sedeString);
                if (sedesDto != null) {
                    session.setAttribute("foto", rutaLogo+"/"+sedesDto.getSede().concat(ExtencionesEnum.JPG.getExt()));
                    if (request.getRequestURI().contains("logout")) {
                        accesosSubsedes.getSedes().remove(sedesDto);
                    }
                }
            }
        }
    }

    private Sedes existSede(String nameSede) {
        return sedesService.findSedeXName(nameSede);
    }

    private String existSedeAccesos(String nameSede) {
        return connectsAuth.findSedeStringXName(nameSede);
    }

}
