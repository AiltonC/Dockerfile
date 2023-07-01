package proyecto.ponti.CONLAB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.ponti.CONLAB.model.Equipo;

import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    Optional<Equipo> findOneById(Integer id);
    Optional<Equipo> findOneByIdAndNomequipo(Integer id, String nomequipo);
}
