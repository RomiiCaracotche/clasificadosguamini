package com.clasificados.clasificadosGuamini.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El numero de documento es obligatorio")
    private String dni;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min=5, max=12)
    private String nombreUsuario;

    @NotBlank(message = "El email es obligatorio")
    @Email 
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min=8, max=12)
    private String password;

    @NotBlank(message = "El numero de celular es obligatorio")
    private String celular;

    @NotBlank(message = "La localidad es obligatoria")
    private String localidad;

    private Boolean admin = false;

}
