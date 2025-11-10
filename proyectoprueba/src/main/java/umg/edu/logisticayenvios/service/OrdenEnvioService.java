package umg.edu.logisticayenvios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umg.edu.logisticayenvios.entity.OrdenEnvio;
import umg.edu.logisticayenvios.repository.OrdenEnvioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenEnvioService {

    private final OrdenEnvioRepository ordenEnvioRepository;

    @Autowired
    public OrdenEnvioService(OrdenEnvioRepository ordenEnvioRepository) {
        this.ordenEnvioRepository = ordenEnvioRepository;
    }

    //  Listar órdenes
    public List<OrdenEnvio> listarOrdenes() {
        return ordenEnvioRepository.findAll();
    }

    //  Buscar orden por ID
    public Optional<OrdenEnvio> obtenerOrdenPorId(Long id) {
        return ordenEnvioRepository.findById(id);
    }

    // Crear orden
    public OrdenEnvio crearOrden(OrdenEnvio orden) {
        return ordenEnvioRepository.save(orden);
    }

    // Actualizar orden
    public Optional<OrdenEnvio> actualizarOrden(Long id, OrdenEnvio ordenActualizada) {
        return ordenEnvioRepository.findById(id).map(orden -> {
            orden.setCliente(ordenActualizada.getCliente());
            orden.setDireccionDestino(ordenActualizada.getDireccionDestino());
            orden.setFechaEnvio(ordenActualizada.getFechaEnvio());
            orden.setEstado(ordenActualizada.getEstado());
            return ordenEnvioRepository.save(orden);
        });
    }

    // Eliminar orden
    public boolean eliminarOrden(Long id) {
        if (ordenEnvioRepository.existsById(id)) {
            ordenEnvioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
