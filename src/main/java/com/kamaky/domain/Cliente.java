package com.kamaky.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

//Cliente que realiza pedidos (HU-008) y solicitudes personalizadas (HU-009)
@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(nullable = false, length = 80)
    @NotBlank
    @Size(max = 80)
    private String nombre;

    @Column(nullable = false, length = 20)
    @NotBlank
    @Size(max = 20)
    private String telefono;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    private String correo;
}
