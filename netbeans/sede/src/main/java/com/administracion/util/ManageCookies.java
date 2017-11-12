/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author user
 */
public class ManageCookies {
    /**
	 * 
	 * @param request
	 * @param cookieName
	 * @return : Recupero  el valor de la cookie
	 */
	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
                    for (Cookie cookie : cookies) {
                        if (cookieName.equals(cookie.getName()))
                            return(cookie.getValue());
                    }
		}
		return null;
	}
	
	/**
	 * Dura 1 semana
	 * @param response
	 * @param cookieName
	 * @param value
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, String value){
		Cookie ck = new Cookie(cookieName, value);
		ck.setMaxAge(7*24*60*60);//7 dias
		ck.setPath("/");
		response.addCookie(ck);
	}
	
	/**
	 * Para los días y el path que se necesite
	 * @param response
	 * @param cookieName
	 * @param value
	 * @param dias
	 * @param path
	 */
	public static void setCookie(HttpServletResponse response, String cookieName, String value,int dias,String path){
		Cookie ck = new Cookie(cookieName, value);
		ck.setMaxAge(dias*24*60*60);//n dias
		ck.setPath(path);
		response.addCookie(ck);
	}
	
	/**
	 * Borra cookies
	 * @param cookieName
	 * @param response
	 * @param request
	 */
	public static void eraseCookie(String cookieName,HttpServletResponse response,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
                    for (Cookie cookie : cookies) {
                        if (cookieName.equals(cookie.getName())) {
                            cookie.setValue("");
                            cookie.setPath("/");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
		}
	}
}
