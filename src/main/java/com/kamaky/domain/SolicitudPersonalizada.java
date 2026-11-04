package com.kamaky.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

//Solicitud de un producto personalizado hecha por un cliente (HU-009)
@Getter
@Setter
@Entity
@Table(name = "solicitud_personalizada")
public class SolicitudPersonalizada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    //Muchas solicitudes pueden ser del mismo cliente
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(nullable = false, length = 500)
    @NotBlank
    @Size(max = 500)
    private String descripcion;

    @NotNull
    @Min(1)
    private Integer cantidad;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEntrega;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaEntrega;

    @Column(length = 255)
    @Size(max = 255)
    private String preferencias;

    @Column(nullable = false, length = 20)
    private String estado;

    private LocalDateTime fechaSolicitud;
}
