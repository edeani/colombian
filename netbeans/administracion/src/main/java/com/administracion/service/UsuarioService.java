/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dto.UsuarioDto;
import com.administracion.entidad.Rol;
import com.administracion.entidad.Usuario;
import java.util.List;

/**
 *
 * @author user
 */
public interface UsuarioService {
    public List<Usuario> listUsuarios();
    public Usuario findUsuarioByCorreo(String correo);
    public UsuarioDto findUsuarioByCorreoDto(String correo);
    public Usuario findUsuarioById(Long idusuario);
    public void crearUsuario(Usuario usuario);
    public void actualizarUsuario(Usuario usuario);
    public void actualizarUsuarioAdministracion(UsuarioDto usuarioDto);
    public void borrarUsuario(Usuario usuario);
    public Rol roles(Integer idrol);
    public void actualizarUsuarioAdministracionRol(Integer idrol,Long idusuario);
}
