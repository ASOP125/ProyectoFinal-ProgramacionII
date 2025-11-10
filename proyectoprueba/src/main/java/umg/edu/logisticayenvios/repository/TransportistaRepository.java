package umg.edu.logisticayenvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umg.edu.logisticayenvios.entity.Transportista;

public interface TransportistaRepository extends JpaRepository<Transportista, Long> {
}
