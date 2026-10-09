package com.espiritu.enfermeria.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auditorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private LocalDateTime fechaHora;
    private String operacion;
    private String entidadAfectada;
    private Long registroId;
}