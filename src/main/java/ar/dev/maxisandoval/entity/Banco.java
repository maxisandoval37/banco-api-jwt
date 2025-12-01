package ar.dev.maxisandoval.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Banco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El pais es obligatorio")
    @Size(min = 2, max = 50, message = "El pais debe tener entre 2 y 50 caracteres")
    private String pais;

    @NotNull(message = "El campo activo, es obligatorio")
    private Boolean activo;
}