/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.util;

/**
 *
 * @author user
 */
public class Util {
    public static Integer firstItemPage(Integer page,Integer cantidad){
        return cantidad*(page-1);
    }
    
}
