/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;

import com.administracion.dto.UserItemDto;
import com.administracion.entidad.Users;

/**
 *
 * @author EderArmando
 */
public class UserMapper {
    public static UserItemDto userToUserItemDto(Users user){
        UserItemDto userItemDto = new UserItemDto();
        userItemDto.setIdSedeUser(user.getIdsedes().getIdsedes());
        userItemDto.setUserName(user.getUsername());
        userItemDto.setIdUser(user.getCedula());
        
        return userItemDto;
    }
}
