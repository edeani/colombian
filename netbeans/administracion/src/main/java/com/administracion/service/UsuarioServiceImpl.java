/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.service;

import com.administracion.dao.RolDao;
import com.administracion.dao.UsuarioDao;
import com.administracion.dao.ValidacionUsuarioDao;
import com.administracion.dto.UsuarioDto;
import com.administracion.entidad.Rol;
import com.administracion.entidad.Usuario;
import com.administracion.entidad.ValidacionUsuarios;
import com.administracion.util.LectorPropiedades;
import com.administracion.util.LeerXml;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author user
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private ValidacionUsuarioDao validacionUsuarioDao;

    @Autowired
    private RolDao rolDao;

    @Autowired
    private LeerXml leerXml;

    private static final String SEPARADOR_TOKEN = "_";
    @Value("${token.clave}")
    private String CLAVE_TOKEN;
    private static final String PROPIEDADES_COLOMBIAN = "colombian.properties";
    private static final int ID_ROL_USUARIO = 1;

    @Transactional(readOnly = true)
    @Override
    public Usuario findUsuarioByCorreo(String correo) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("correo", correo);
        return usuarioDao.queryOpjectJpa(leerXml.getQuery("UsuarioJpa.findXcorreo"), parametros);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listUsuarios() {
        return usuarioDao.findAll();
    }

    @Transactional
    @Override
    public void crearUsuario(Usuario usuario) {
        //Guardo Usuario
        Rol rol = rolDao.findById(ID_ROL_USUARIO);
        usuario.setIdrol(rol);
        usuarioDao.save(usuario);
        //Genero Token
        StringBuilder token = new StringBuilder();
        token.append(usuario.getNombreusuario().toLowerCase()).append(SEPARADOR_TOKEN).append(usuario.getCorreo()).append(SEPARADOR_TOKEN).append(CLAVE_TOKEN);
        TimeZone timeZone1 = TimeZone.getTimeZone("America/Bogota");
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(timeZone1);
        calendar.add(Calendar.DAY_OF_MONTH, 7); // agregar 1 semana al token de expiración.
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int hora = calendar.get(Calendar.HOUR);
        int minuto = calendar.get(Calendar.MINUTE);
        String fechaActual = "" + dia + mes + ano + hora + minuto;
        token.append(SEPARADOR_TOKEN).append(fechaActual);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(token.toString());
        

        ValidacionUsuarios vu = new ValidacionUsuarios();
        LectorPropiedades le = new LectorPropiedades();

        vu.setToken(hashedPassword);
        vu.setEstado(le.leerPropiedad(PROPIEDADES_COLOMBIAN, "usuario.estadoregistro"));

        vu.setIdusuario(usuario);

        Calendar cal = Calendar.getInstance();
        cal.set(ano, mes, dia, hora, minuto, 0);
        vu.setFecha(cal.getTime());
        
        validacionUsuarioDao.save(vu);
    }

    @Transactional
    @Override
    public void actualizarUsuario(Usuario usuario) {
        usuarioDao.Update(usuario);
    }

    @Transactional
    @Override
    public void borrarUsuario(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public Rol roles(Integer idrol) {
        return rolDao.findById(idrol);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findUsuarioById(Long idusuario) {
        return usuarioDao.findById(idusuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDto findUsuarioByCorreoDto(String correo) {
        return usuarioDao.findUsuarioXCorreoSql(correo);
    }

    @Override
    @Transactional
    public void actualizarUsuarioAdministracion(UsuarioDto usuarioDto) {
        Usuario usuario = usuarioDao.findById(usuarioDto.getIdusuario());
        usuario.setDireccion(usuarioDto.getDireccion());
        usuario.setEstado(usuarioDto.getEstado());
        usuarioDto.setNombreusuario(usuarioDto.getNombreusuario());
        usuarioDto.setPassword(usuarioDto.getPassword());
        usuarioDto.setTelefono(usuarioDto.getTelefono());
        
        Rol rol = rolDao.findById(usuarioDto.getIdrol());
        usuario.setIdrol(rol);
        
        usuarioDao.Update(usuario);
    }

}
