/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.mapper;


import domicilios.dto.UsuarioDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author edeani
 */
public class UsuarioDtoMapper implements RowMapper<UsuarioDto>{

    @Override
    public domicilios.dto.UsuarioDto mapRow(ResultSet rs, int i) throws SQLException {
        UsuarioDto usuarioDto = new UsuarioDto();
        
        usuarioDto.setCorreo(rs.getString("correo"));
        usuarioDto.setDireccion(rs.getString("direccion"));
        usuarioDto.setIdentificacion(rs.getString("cedula"));
        usuarioDto.setIdrol(rs.getInt("idrol"));
        usuarioDto.setIdusuario(rs.getLong("idusuario"));
        usuarioDto.setNombrerol(rs.getString("nombrerol"));
        usuarioDto.setNombreusuario(rs.getString("nombreusuario"));
        usuarioDto.setPassword(rs.getString("password"));
        usuarioDto.setEstado(rs.getString("estado"));
        usuarioDto.setTelefono(rs.getString("telefono"));
        return usuarioDto;
        
    }
    
}
