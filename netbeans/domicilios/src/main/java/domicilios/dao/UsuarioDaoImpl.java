/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dao;

import domicilios.dto.UsuarioDto;
import domicilios.entidad.Usuario;
import domicilios.mapper.UsuarioDtoMapper;
import domicilios.util.LeerXml;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao{
    @Autowired
    private LeerXml leerXml;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public UsuarioDto findUsuarioXCorreoSql(String correo) {
        final MapSqlParameterSource namedParameterSource = new MapSqlParameterSource();
        namedParameterSource.addValue("correo", correo);
        try {
            List<UsuarioDto> usuario = namedParameterJdbcTemplate.query(leerXml.getQuery("UsuarioSql.findXcorreo"), namedParameterSource, new UsuarioDtoMapper());
            if (usuario != null) {
                if (usuario.size()>0) {
                    return usuario.get(0);
                } else {
                    throw new DataAccessException("No se encontraron registros") {
                    };
                }
            } else {
                throw new DataAccessException("No se encontraron registros") {
                };
            }
        } catch (DataAccessException e) {
            return null;
        }
    }
}
