/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.colombia.cali.colombiancaliycali.dao;

import com.colombia.cali.colombiancaliycali.dao.generic.CaliycaliDao;
import com.colombia.cali.colombiancaliycali.dao.generic.DataGenericDao;
import com.colombia.cali.colombiancaliycali.dataSource.ProjectsDao;
import com.colombian.cali.colombiancaliycali.dto.BeneficiarioAutocompletarDto;
import com.colombian.cali.colombiancaliycali.dto.ItemsDTO;
import java.util.List;
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
public class BeneficiariosDaoImpl extends DataGenericDao implements BeneficiariosDao{
    private JdbcTemplate jdbctemplate;
    @Autowired
    private ProjectsDao projectsDao;
    
    @Override
    public List<BeneficiarioAutocompletarDto> buscarBeneficiarioLikeNombre(String nameDataSource, String nombre) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<BeneficiarioAutocompletarDto> beneficiarios = null;
        
        try {
            beneficiarios = this.jdbctemplate.query(caliycaliDao.selectJdbTemplate("id,tipoidentificacion,identificacion,nombre as value,telefono,celular,direccion,correo", "beneficiarios", "lower(nombre) like lower('%"+nombre+"%')"), new BeanPropertyRowMapper<BeneficiarioAutocompletarDto>(BeneficiarioAutocompletarDto.class));
        } catch(DataAccessException e){
            System.out.println("buscarBeneficiarioLikeNombre::"+e.getMessage());
        }
        return beneficiarios;
    }

    @Override
    public List<ItemsDTO> beneficiariosSelect(String nameDataSource) {
        this.jdbctemplate = new JdbcTemplate(projectsDao.getDatasource(nameDataSource));
        List<ItemsDTO> datos = null;
        try {
            datos = this.jdbctemplate.query(caliycaliDao.selectJdbTemplate("id, nombre as label", "beneficiarios", ""), new BeanPropertyRowMapper<ItemsDTO>(ItemsDTO.class));
        } catch (DataAccessException e) {
            System.out.println("Error beneficiariosSelect::"+e.getMessage());
        }
        
        return datos;
    }
    
    
}
