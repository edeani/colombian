/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 10 Spring Creators
 */
@Data
@NoArgsConstructor
public class CategoriaDto {
    
    private Integer idCategory;
    private String name;
    private String urlImage;
    private Integer order;
    
    private List<String> fieldsNamesProduct;
    
    
    private List<ProductoDto> products;
    
}
