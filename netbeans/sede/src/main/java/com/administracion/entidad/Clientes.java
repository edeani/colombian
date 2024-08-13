/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.administracion.entidad;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Anlod
 */
@Data
@NoArgsConstructor
public class Clientes implements Serializable{
    
    private String numeroTelefono;
    private String descripcionCliente;
    private String direccionCliente;
    private String fechaIngreso;
    private Integer codigoBarrio;
    
}
