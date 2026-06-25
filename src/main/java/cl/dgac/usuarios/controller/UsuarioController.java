package cl.dgac.usuarios.controller;

import cl.dgac.usuarios.dto.UsuarioRequest;
import cl.dgac.usuarios.dto.UsuarioResponse;
import cl.dgac.usuarios.model.RolUsuario;
import cl.dgac.usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con la gestión de usuarios del sistema DGAC")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(
        summary = "Listar todos los usuarios",
        description = "Obtiene una lista de todos los usuarios registrados en la plataforma. Permite filtrar opcionalmente por el rol del usuario."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios(
            @RequestParam(required = false) RolUsuario rol
    ) {
        if (rol != null) {
            return ResponseEntity.ok(usuarioService.buscarPorRol(rol));
        }
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @Operation(
        summary = "Buscar usuario por ID",
        description = "Obtiene los detalles de un usuario específico mediante su identificador único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @Operation(
        summary = "Crear nuevo usuario",
        description = "Registra un nuevo usuario en el sistema asignándole un rol (ej. PILOTO, INSPECTOR, EMPRESA)."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto con los datos del nuevo usuario",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioRequest.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de creación de usuario",
                                    value = "{\n  \"nombre\": \"Rodrigo Pérez\",\n  \"email\": \"rodrigo.piloto@email.com\",\n  \"password\": \"Segura123!\",\n  \"rol\": \"PILOTO\"\n}"
                            )
                    )
            )
            @Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse usuarioCreado = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @Operation(
        summary = "Actualizar usuario",
        description = "Actualiza los datos de un usuario existente mediante su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto con los datos a actualizar del usuario",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioRequest.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de actualización de usuario",
                                    value = "{\n  \"nombre\": \"Rodrigo Pérez (Actualizado)\",\n  \"email\": \"rodrigo.piloto@email.com\",\n  \"password\": \"NuevaClave456!\",\n  \"rol\": \"PILOTO\"\n}"
                            )
                    )
            )
            @Valid @RequestBody UsuarioRequest request
    ) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, request));
    }

    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina un usuario del sistema mediante su identificador único."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}