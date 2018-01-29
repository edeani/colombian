/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adiministracion.mapper;

import com.administracion.dto.UserItemDto;
import com.administracion.entidad.Users;
import com.administracion.entidad.Userxsede;

/**
 *
 * @author EderArmando
 */
public class UserMapper {
    public static UserItemDto userToUserItemDto(Userxsede userxsede){
        UserItemDto userItemDto = new UserItemDto();
        userItemDto.setIdSedeUser(userxsede.getIdsede().getIdsedes());
        userItemDto.setUserName(userxsede.getIduser().getUsername());
        userItemDto.setIdUser(userxsede.getIduser().getCedula());
        userItemDto.setNameSede(userxsede.getIdsede().getSede());
        return userItemDto;
    }
}
