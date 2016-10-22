/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administracion.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author user
 */
public class PedidoClienteDto {
    private Long idpedido;
    private List<ProductoClienteDto> productos;
    private Float total;
    private String estado;
    private Integer idusuario;
    
    @NotEmpty
    @Size(max = 15)
    @Pattern(regexp = "[0-9]*")
    private String cedula;
    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "[A-Za-z áéíóúñÁÉÍÓÚÑ]*")
    private String nombre;
    @NotNull
    private Integer medioPago;
    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "[A-Za-z0-9 áéíóúñÁÉÍÓÚÑ\\.#\\-]*")
    private String direccion;
    @NotEmpty
    @Size(max = 10)
    @Pattern(regexp = "[0-9]*")
    private String telefono;
    @Size(max = 500)
    @Pattern(regexp = "[A-Za-z áéíóúñÁÉÍÓÚÑ\\.#\\-,]*")
    private String comentarios;
    
    
    
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(Integer medioPago) {
        this.medioPago = medioPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public Long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public List<ProductoClienteDto> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoClienteDto> productos) {
        this.productos = productos;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
    
    
}
