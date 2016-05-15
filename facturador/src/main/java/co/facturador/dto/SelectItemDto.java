/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.dto;

/**
 *
 * @author EderArmando
 */
public class SelectItemDto {
    
    private Long id;
    private String texto;
    private Long precio;

    public SelectItemDto(Long id,String texto,Long precio){
        this.id = id;
        this.texto = texto;
        this.precio = precio;
    }

    public SelectItemDto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Long getPrecio() {
        return precio;
    }

    public void setPrecio(Long precio) {
        this.precio = precio;
    }
}
