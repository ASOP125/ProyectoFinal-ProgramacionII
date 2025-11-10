package umg.edu.logisticayenvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umg.edu.logisticayenvios.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { }
