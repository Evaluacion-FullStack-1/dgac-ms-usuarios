package cl.dgac.usuarios.dto;

import cl.dgac.usuarios.model.RolUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "Modelo de petición para la creación o actualización de un usuario")
public class UsuarioRequest {

    @Schema(description = "Nombre completo del usuario", example = "Rodrigo Pérez")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Schema(description = "Correo electrónico institucional o personal", example = "rodrigo.piloto@email.com")
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ingresar un email válido")
    private String email;

    @Schema(description = "Contraseña de acceso al sistema", example = "Segura123!")
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @Schema(description = "Rol asignado dentro de la plataforma de la DGAC", example = "PILOTO")
    @NotNull(message = "El rol es obligatorio")
    private RolUsuario rol;
}