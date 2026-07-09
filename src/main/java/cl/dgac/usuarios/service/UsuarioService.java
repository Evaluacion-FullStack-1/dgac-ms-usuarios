package cl.dgac.usuarios.service;

import cl.dgac.usuarios.dto.UsuarioRequest;
import cl.dgac.usuarios.dto.UsuarioResponse;
import cl.dgac.usuarios.exception.EmailYaExisteException;
import cl.dgac.usuarios.exception.RecursoNoEncontradoException;
import cl.dgac.usuarios.mapper.UsuarioMapper;
import cl.dgac.usuarios.model.RolUsuario;
import cl.dgac.usuarios.model.Usuario;
import cl.dgac.usuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + id));

        return UsuarioMapper.toResponse(usuario);
    }

    public List<UsuarioResponse> buscarPorRol(RolUsuario rol) {
        return usuarioRepository.findByRol(rol)
                .stream()
                .map(UsuarioMapper::toResponse)
                .toList();
    }

    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new EmailYaExisteException("Ya existe un usuario con el email: " + request.getEmail());
        }

        Usuario usuario = UsuarioMapper.toEntity(request);
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuarioGuardado);
    }

    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + id));

        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setRol(request.getRol());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return UsuarioMapper.toResponse(usuarioActualizado);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + id));

        usuarioRepository.delete(usuario);
    }
} 