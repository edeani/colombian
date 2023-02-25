/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombia.cali.colombiancaliycali.classloading;

import org.springframework.instrument.classloading.SimpleLoadTimeWeaver;

/**
 *
 * @author Eslayfer
 */
public class MyClassLoaderWeaver extends SimpleLoadTimeWeaver{
    
    @Override
    public ClassLoader getInstrumentableClassLoader (){
        return super.getInstrumentableClassLoader().getParent();
    }
    
}
