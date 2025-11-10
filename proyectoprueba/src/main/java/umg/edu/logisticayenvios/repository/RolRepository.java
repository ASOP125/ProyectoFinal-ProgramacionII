package umg.edu.logisticayenvios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import umg.edu.logisticayenvios.entity.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}
