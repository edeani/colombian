/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domicilios.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author user
 */
public class UsuarioRegistroDto {
    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "[a-zA-Z_áéíóúñÁÉÍÓÚÑ]")
    private String nombre;
    @Email
    @Size(max = 100)
    private String correo;
    @NotEmpty
    @Size(max = 20)
    @Pattern(regexp = "[0-9a-zA-Z_áéíóúñÁÉÍÓÚÑ\\-\\.\\,!\\+$]")
    private String password;
    @NotEmpty
    @Size(max = 20)
    @Pattern(regexp = "[0-9a-zA-Z_áéíóúñÁÉÍÓÚÑ\\-\\.\\,!\\+$]")
    private String confirmarpassword;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmarpassword() {
        return confirmarpassword;
    }

    public void setConfirmarpassword(String confirmarpassword) {
        this.confirmarpassword = confirmarpassword;
    }
    
    
}
