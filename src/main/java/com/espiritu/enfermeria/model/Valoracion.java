package com.espiritu.enfermeria.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "valoraciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación JPA con Paciente (Pregunta 1)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    // Relación JPA con Usuario/Enfermero (Pregunta 1)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enfermero_id", nullable = false)
    private Usuario enfermero;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(length = 500, nullable = false)
    private String motivoConsulta;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(columnDefinition = "TEXT")
    private String planCuidados;

    @PrePersist
    public void prePersist() {
        this.fechaHora = LocalDateTime.now();
    }
}