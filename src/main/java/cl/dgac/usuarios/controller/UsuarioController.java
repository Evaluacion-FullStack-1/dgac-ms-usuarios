package cl.dgac.usuarios.controller;

import cl.dgac.usuarios.dto.UsuarioRequest;
import cl.dgac.usuarios.dto.UsuarioResponse;
import cl.dgac.usuarios.model.RolUsuario;
import cl.dgac.usuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios(
            @RequestParam(required = false) RolUsuario rol
    ) {
        if (rol != null) {
            return ResponseEntity.ok(usuarioService.buscarPorRol(rol));
        }

        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse usuarioCreado = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request
    ) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}