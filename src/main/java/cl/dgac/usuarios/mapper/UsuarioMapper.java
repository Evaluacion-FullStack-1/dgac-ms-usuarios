package cl.dgac.usuarios.mapper;

import cl.dgac.usuarios.dto.UsuarioRequest;
import cl.dgac.usuarios.dto.UsuarioResponse;
import cl.dgac.usuarios.model.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setRol(request.getRol());
        usuario.setActivo(true);
        return usuario;
    }

    public static UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol(),
                usuario.getActivo()
        );
    }
}