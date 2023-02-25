/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author 10 Spring Creators
 */
@Data
@NoArgsConstructor
public class ProductoDto {
    
    private String name;
    private String description;
    private Double price;
    private Integer qty;
    private String urlImage;
    
}
