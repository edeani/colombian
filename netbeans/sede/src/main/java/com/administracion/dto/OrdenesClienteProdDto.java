/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Anlod
 */
@Data
@NoArgsConstructor
public class OrdenesClienteProdDto {

    private String telefono;
    private String nombreCliente;
    private Integer codigoBarrio;
    private Integer unidades;
    private Float valorProducto;
    private String nombreProducto;
    private String codigoProducto;
    private Float valorTotal;
    
}
