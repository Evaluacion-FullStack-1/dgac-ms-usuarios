package cl.dgac.usuarios.repository;

import cl.dgac.usuarios.model.RolUsuario;
import cl.dgac.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByRol(RolUsuario rol);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
