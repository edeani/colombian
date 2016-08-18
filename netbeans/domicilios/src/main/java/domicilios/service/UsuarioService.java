/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.service;

import domicilios.entidad.Rol;
import domicilios.entidad.Usuario;
import java.util.List;

/**
 *
 * @author user
 */
public interface UsuarioService {
    public List<Usuario> listUsuarios();
    public Usuario findUsuarioByCorreo(String correo);
    public void crearUsuario(Usuario usuario);
    public void actualizarUsuario(Usuario usuario);
    public void borrarUsuario(Usuario usuario);
    public Rol roles(Integer idrol);
}
