package com.clasificados.clasificadosGuamini.dtos.request.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioActualizadoRequestDto {

    private String nombre;

    private String apellido;

    @Email 
    private String email;

    @Size(min=8, max=12, message = "La contraseña debe tener entre 8 y 12 caracteres")
    private String passwordActual;

    @Size(min=8, max=12, message = "La contraseña debe tener entre 8 y 12 caracteres")
    private String passwordNueva;
    
    private String celular;
    
    private String localidad;

}
