package com.clasificados.clasificadosGuamini.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@Table(name ="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String dni;

    @Column(name="nombre_usuario", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String celular;

    @Column(nullable = false)
    private String localidad;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean activo = true;

    @ManyToMany(cascade ={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name="avisos_favoritos",
        joinColumns=@JoinColumn(name="usuario_id"),
        inverseJoinColumns=@JoinColumn(name="aviso_id")
    )
    private Set<Aviso> favoritos = new HashSet<>();

    @OneToMany (mappedBy = "anunciante", orphanRemoval = true)
    private List<Aviso> avisos = new ArrayList<>();

    @Column(nullable = false)
    private Boolean admin = false;
    
}
