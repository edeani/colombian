/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author user
 */

public class ProductoDetailDto{
    
    
    private Integer idproducto;
    @NotEmpty
    @Size(max = 45)
    @Pattern(regexp = "[A-Za-z0-9 áéíóúñÁÉÍÓÚÑ\\.\\-]*")
    private String nombreproducto;
    @NotNull
    private Float precioproducto;
    @NotNull
    private Integer tipo;
    @NotEmpty
    @Size(max = 500)
    @Pattern(regexp = "[A-Za-z0-9 áéíóúñÁÉÍÓÚÑ\\.#\\-,]*")
    private String descripcion;
    @NotEmpty
    @Size(max = 1)
    private String estado;

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public Float getPrecioproducto() {
        return precioproducto;
    }

    public void setPrecioproducto(Float precioproducto) {
        this.precioproducto = precioproducto;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
