package com.clasificados.clasificadosGuamini.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "avisos")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    private LocalDateTime fechaCreacion;
    
    private LocalDateTime fechaExpiracion;

    @Column(nullable = false)
    private String localidad;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoAviso estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario anunciante;

    @ElementCollection
    @CollectionTable(name = "aviso_imagenes", joinColumns = @JoinColumn(name = "aviso_id"))
    @Column(name = "url_imagen", nullable = false)
    @JsonIgnore
    private List<String> imagenes = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.estado = EstadoAviso.APROBADO;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaExpiracion = LocalDateTime.now().plusDays(30);  
    }

}
