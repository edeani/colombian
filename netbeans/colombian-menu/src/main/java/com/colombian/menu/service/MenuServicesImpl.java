/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.service;

import com.colombian.menu.components.MenuData;
import com.colombian.menu.conf.GoogleApiConfigurations;
import com.colombian.menu.dto.CategoriaDto;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 10 Spring Creators
 */
@Service
public class MenuServicesImpl implements MenuService {

    @Autowired
    private GoogleApiConfigurations googleApiConfigurations;

    private static Sheets sheetService;

    @Override
    public MenuData getMenu() {
        MenuData menu = new MenuData();
        try {
            sheetService = SheetsServiceUtil.getSheetsService();
            List<CategoriaDto> categories = loadCategories();
            menu.setCategories(categories);
        } catch (IOException ex) {
            Logger.getLogger(MenuServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneralSecurityException ex) {
            Logger.getLogger(MenuServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return menu;
    }

    private List<CategoriaDto> loadCategories() throws IOException {

        final String rangeCategory = String.format("%s%s",
                googleApiConfigurations.getCategorySheet(), googleApiConfigurations.getCategoryRange());
        ValueRange categorySheetRange = sheetService.spreadsheets().values()
                .get(googleApiConfigurations.getIdColombianmenu(), rangeCategory)
                .execute();

        List<List<Object>> values = categorySheetRange.getValues();
        
        List<CategoriaDto> valuesCat = new ArrayList<>();
        if(values != null || !values.isEmpty()){
            values.stream().map((List<Object> rowCategory) -> {
                CategoriaDto itemCategory = new CategoriaDto();
                itemCategory.setName(String.valueOf(rowCategory.get(1)));
                itemCategory.setOrder(Integer.valueOf(String.valueOf(rowCategory.get(2))));
                itemCategory.setUrlImage(String.valueOf(rowCategory.get(3)));
                return itemCategory;                
            }).forEachOrdered((itemCategory) -> {
                boolean add = valuesCat.add(itemCategory);
            });
        }
        
        
     
        
        return valuesCat;
        
        
    }

}
