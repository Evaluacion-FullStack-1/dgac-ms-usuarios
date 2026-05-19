package cl.dgac.usuarios.dto;

import cl.dgac.usuarios.model.RolUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    private Long id;
    private String nombre;
    private String email;
    private RolUsuario rol;
    private Boolean activo;
}