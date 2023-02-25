/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.service;

import com.colombian.menu.components.MenuData;
import com.colombian.menu.conf.GoogleApiConfigurations;
import com.colombian.menu.dto.CategoriaDto;
import com.colombian.menu.dto.ProductoDto;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    private SheetsServiceUtil sheetsServiceUtil;
    private Sheets sheetService;

    @Override
    public MenuData getMenu() {
        MenuData menu = new MenuData();
        try {
            sheetService = sheetsServiceUtil.getSheetsService();
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

        /**
         * Categories
         */
        final String rangeCategory = String.format("%s%s",
                googleApiConfigurations.getCategorySheet(), googleApiConfigurations.getCategoryRange());
        ValueRange categorySheetRange = sheetService.spreadsheets().values()
                .get(googleApiConfigurations.getIdColombianmenu(), rangeCategory)
                .execute();
        List<List<Object>> valuesSheetCategory = categorySheetRange.getValues();
        
        /**
         * Products
         */
        final String rangeProducts = String.format("%s%s",
                googleApiConfigurations.getProductSheet(), googleApiConfigurations.getProductRange());

        ValueRange productSheetRange = sheetService.spreadsheets().values()
                .get(googleApiConfigurations.getIdColombianmenu(), rangeProducts)
                .execute();
        List<List<Object>> valuesSheetProduct = productSheetRange.getValues();

        /**
         * Processing information
         */
        List<CategoriaDto> valuesCat = new ArrayList<>();
        if (verifyEmptyList(valuesSheetCategory)) {
            valuesSheetCategory.stream().map((List<Object> rowCategory) -> {
                CategoriaDto itemCategory = new CategoriaDto();
                itemCategory.setIdCategory(Integer.valueOf((String)rowCategory.get(0)));
                itemCategory.setName(String.valueOf(rowCategory.get(1)));
                itemCategory.setOrder(Integer.valueOf(String.valueOf(rowCategory.get(2))));
                itemCategory.setUrlImage(String.valueOf(rowCategory.get(3)));
                return itemCategory;
            }).forEachOrdered((itemCategory) -> {
                try {
                    
                    itemCategory.setProducts(loadProductsByCategory(itemCategory.getIdCategory(),valuesSheetProduct));
                    boolean add = valuesCat.add(itemCategory);
                } catch (IOException ex) {
                    Logger.getLogger(MenuServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        return valuesCat;

    }

    private List<ProductoDto> loadProductsByCategory(Integer categoria,List<List<Object>> values) throws IOException {
       
        List<ProductoDto> productoDtos = new ArrayList<>();
        if (verifyEmptyList(values)) {
            values.stream().map((List<Object> rowProduct) -> {
                ProductoDto itemProduct = null;
                if (Objects.equals(Integer.valueOf((String) rowProduct.get(3)), categoria)) {
                    itemProduct = new ProductoDto();
                    itemProduct.setName(String.valueOf(rowProduct.get(1)));
                    itemProduct.setPrice(Double.valueOf((String)rowProduct.get(2)));
                    itemProduct.setUrlImage(String.valueOf(rowProduct.get(5)));

                }
                return itemProduct;
            }).forEachOrdered((itemCategory) -> {
                if (itemCategory != null) {
                    boolean add = productoDtos.add(itemCategory);
                }
            });
        }

        return productoDtos;
    }

    private boolean verifyEmptyList(List<List<Object>> list) {
        return list != null && !list.isEmpty();
    }
}
