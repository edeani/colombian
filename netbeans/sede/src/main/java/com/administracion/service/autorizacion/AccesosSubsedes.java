/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service.autorizacion;

import com.administracion.dto.SubSedesDto;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author EderArmando
 */
@Component
public class AccesosSubsedes {
    private List<SubSedesDto> subsedes;

    public SubSedesDto findSubsedeXName(String subsede){
        for (SubSedesDto subsede1 : subsedes) {
            if(subsede1.getSede().equals(subsede)){
                return subsede1;
            }
        }
        return null;
    }
    
    public List<SubSedesDto> getSubsedes() {
        return subsedes;
    }

    public void setSubsedes(List<SubSedesDto> subsedes) {
        this.subsedes = subsedes;
    }
    
    
}
