package com.clasificados.clasificadosGuamini.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Embeddable 
public class Direccion {

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String localidad;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String calle;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String numero;

    private String piso;

    private String departamento;

    @NotNull(message = "Este campo es obligatorio")
    @Column(name="codigo_postal", nullable = false)
    private Integer codigoPostal;

    public Direccion() {}

    public Direccion(String localidad, String calle, String numero, String piso, String departamento, Integer codigoPostal) {
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
        this.piso = piso;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

}
