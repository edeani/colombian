/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.administracion.enumeration;

/**
 *
 * @author Anlod
 */
public enum EnvEnum {
    DEV("dev"),
    TEST("test"),
    PROD("prod");
    
    private final String env;

    private EnvEnum(String env) {
        this.env = env;
    }

    public String getEnv() {
        return env;
    }
    
}
