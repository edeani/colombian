/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dto;

import java.util.List;

/**
 *
 * @author user
 */
public class PedidoClienteDto {
    private Long idpedido;
    private List<ProductoClienteDto> productos;
    private Float total;

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
    
    
}
