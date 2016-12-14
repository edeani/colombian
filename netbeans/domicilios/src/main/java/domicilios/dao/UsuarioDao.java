/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import domicilios.dto.UsuarioDto;
import domicilios.entidad.Usuario;

/**
 *
 * @author user
 */
public interface UsuarioDao extends GenericDao<Usuario>{
    UsuarioDto findUsuarioXCorreoSql(String correo);
}
