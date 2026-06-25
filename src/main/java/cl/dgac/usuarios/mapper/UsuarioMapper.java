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
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombre(usuario.getNombre());
        response.setEmail(usuario.getEmail());
        response.setRol(usuario.getRol());
        
        // Si necesitas que el campo 'activo' viaje en la respuesta de la API, 
        // primero debes agregarlo como 'private Boolean activo;' en UsuarioResponse.java 
        // y luego descomentar la siguiente línea:
        // response.setActivo(usuario.getActivo());
        
        return response;
    }
}