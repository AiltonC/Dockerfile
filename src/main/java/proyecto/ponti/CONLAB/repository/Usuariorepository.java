package proyecto.ponti.CONLAB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.ponti.CONLAB.model.Usuario;

public interface Usuariorepository extends JpaRepository<Usuario,Integer> {
}
