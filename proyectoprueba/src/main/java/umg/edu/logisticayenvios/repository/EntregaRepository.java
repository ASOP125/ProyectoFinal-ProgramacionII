package umg.edu.logisticayenvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umg.edu.logisticayenvios.entity.Entrega;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
