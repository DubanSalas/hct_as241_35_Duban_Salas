package hct.salas.duban.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("alquiler")
public class Alquiler {

    @Id
    private Long id;
    private Long clienteId;
    private String dias;
    private LocalDate fechainicio;
    private LocalDate fechafin;
    private String total;
    private String status;

    // Campo extra para mostrar datos del cliente
    @Transient
    private ClienteDTO cliente;
}
