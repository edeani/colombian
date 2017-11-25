/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.administracion.dao;


import com.administracion.dto.BeneficiarioAutocompletarDto;
import com.administracion.dto.ItemsDTO;
import com.administracion.entidad.Beneficiarios;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jose Efren
 */
@Repository
public class BeneficiariosDaoImpl extends GenericDaoImpl<Beneficiarios> implements BeneficiariosDao{
    private JdbcTemplate jdbctemplate;
    
    @Override
    public List<BeneficiarioAutocompletarDto> buscarBeneficiarioLikeNombre(DataSource nameDataSource, String nombre) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        List<BeneficiarioAutocompletarDto> beneficiarios = null;
        
        try {
            beneficiarios = this.jdbctemplate.query(selectJdbTemplate("id,tipoidentificacion,identificacion,nombre as value,telefono,celular,direccion,correo", "beneficiarios", "lower(nombre) like lower('%"+nombre+"%')"), new BeanPropertyRowMapper<>(BeneficiarioAutocompletarDto.class));
        } catch(DataAccessException e){
            System.out.println("buscarBeneficiarioLikeNombre::"+e.getMessage());
        }
        return beneficiarios;
    }

    @Override
    public List<ItemsDTO> beneficiariosSelect(DataSource nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(nameDataSource);
        List<ItemsDTO> datos = null;
        try {
            datos = this.jdbctemplate.query(selectJdbTemplate("id, nombre as label", "beneficiarios", ""), new BeanPropertyRowMapper<>(ItemsDTO.class));
        } catch (DataAccessException e) {
            System.out.println("Error beneficiariosSelect::"+e.getMessage());
        }
        
        return datos;
    }
    
    
}
