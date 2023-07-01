package proyecto.ponti.CONLAB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.ponti.CONLAB.model.Alumno;
import proyecto.ponti.CONLAB.model.Carrera;

public interface Carrerarepository extends JpaRepository<Carrera,Integer> {
}
