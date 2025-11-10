package umg.edu.logisticayenvios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umg.edu.logisticayenvios.entity.Transportista;
import umg.edu.logisticayenvios.repository.TransportistaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransportistaService {

    private final TransportistaRepository transportistaRepository;

    @Autowired
    public TransportistaService(TransportistaRepository transportistaRepository) {
        this.transportistaRepository = transportistaRepository;
    }

    // Listar todos los transportistas
    public List<Transportista> listarTransportistas() {
        return transportistaRepository.findAll();
    }

    // Buscar transportista por ID
    public Optional<Transportista> obtenerTransportistaPorId(Long id) {
        return transportistaRepository.findById(id);
    }

    // Crear un nuevo transportista
    public Transportista crearTransportista(Transportista transportista) {
        return transportistaRepository.save(transportista);
    }

    // Actualizar un transportista existente
    public Optional<Transportista> actualizarTransportista(Long id, Transportista transportistaActualizado) {
        return transportistaRepository.findById(id).map(t -> {
            t.setNombre(transportistaActualizado.getNombre());
            t.setTelefono(transportistaActualizado.getTelefono());
            t.setVehiculo(transportistaActualizado.getVehiculo());
            return transportistaRepository.save(t);
        });
    }

    // Eliminar un transportista
    public boolean eliminarTransportista(Long id) {
        if (transportistaRepository.existsById(id)) {
            transportistaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
