package hct.salas.duban.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("cliente")
public class Cliente {

    @Id
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String licencia;
    private String status;
}