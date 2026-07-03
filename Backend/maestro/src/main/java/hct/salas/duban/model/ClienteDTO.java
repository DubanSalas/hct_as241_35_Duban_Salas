package hct.salas.duban.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String dni;
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String licencia;
    private String status;
}