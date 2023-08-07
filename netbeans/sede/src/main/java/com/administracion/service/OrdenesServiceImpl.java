/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.service;

import com.administracion.dao.OrdenesDao;
import com.administracion.dto.OrdenesClienteProdDto;
import com.administracion.service.autorizacion.ConnectsAuth;
import com.administracion.util.Util;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Anlod
 */
@Service
public class OrdenesServiceImpl implements OrdenesService{
    
    @Autowired
    private OrdenesDao ordenesDao;
    
    @Autowired
    private ConnectsAuth connectsAuth;

    @Override
    public List<OrdenesClienteProdDto> ordenesReporteClientesSubSede(String nameDataSource, String fechaInicial, String fechaFinal) {
        try {
            String url = connectsAuth.findSedeXId(connectsAuth.findSubsedeXName(nameDataSource).getIdsede()).getUrl();
            return ordenesDao.ordenesReporteClientesSubSede(connectsAuth.getDataSourceSubSede(nameDataSource), fechaInicial, fechaFinal
                , Util.extractDatabaseFromURL(url));
        } catch (Exception e) {
            System.out.println("Error ordenesReporteClientesSubSede "+e.getMessage());
        }
        return null;
    }
    
}
