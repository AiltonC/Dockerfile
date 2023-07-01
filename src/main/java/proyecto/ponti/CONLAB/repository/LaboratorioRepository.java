package proyecto.ponti.CONLAB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.ponti.CONLAB.model.Laboratorio;

import java.util.Optional;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer> {

}
