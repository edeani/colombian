/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.colombian.menu.components;

import com.colombian.menu.dto.CategoriaDto;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 * @author 10 Spring Creators
 */
@Component
@Data
@NoArgsConstructor
public class MenuData {
    
    private List<CategoriaDto> categories;
}
