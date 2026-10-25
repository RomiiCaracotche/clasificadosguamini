package com.clasificados.clasificadosGuamini.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min=5, max=12)
    private String nombreUsuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min=8, max=12)  
    private String password;

}
