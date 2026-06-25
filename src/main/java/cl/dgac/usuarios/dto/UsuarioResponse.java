package cl.dgac.usuarios.dto;

import cl.dgac.usuarios.model.RolUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Modelo de respuesta con la información pública del usuario")
public class UsuarioResponse {

    @Schema(description = "Identificador único del usuario en la base de datos", example = "1")
    private Long id;

    @Schema(description = "Nombre completo del usuario", example = "Rodrigo Pérez")
    private String nombre;

    @Schema(description = "Correo electrónico del usuario", example = "rodrigo.piloto@email.com")
    private String email;

    @Schema(description = "Rol del usuario en el sistema", example = "PILOTO")
    private RolUsuario rol;
}