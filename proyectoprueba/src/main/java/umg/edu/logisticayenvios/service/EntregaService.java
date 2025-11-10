package umg.edu.logisticayenvios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umg.edu.logisticayenvios.dto.EntregaDTO;
import umg.edu.logisticayenvios.entity.Entrega;
import umg.edu.logisticayenvios.entity.OrdenEnvio;
import umg.edu.logisticayenvios.entity.Transportista;
import umg.edu.logisticayenvios.repository.EntregaRepository;
import umg.edu.logisticayenvios.repository.OrdenEnvioRepository;
import umg.edu.logisticayenvios.repository.TransportistaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;
    private final OrdenEnvioRepository ordenEnvioRepository;
    private final TransportistaRepository transportistaRepository;

    @Autowired
    public EntregaService(EntregaRepository entregaRepository,
                          OrdenEnvioRepository ordenEnvioRepository,
                          TransportistaRepository transportistaRepository) {
        this.entregaRepository = entregaRepository;
        this.ordenEnvioRepository = ordenEnvioRepository;
        this.transportistaRepository = transportistaRepository;
    }

    // Listar todas las entregas
    public List<Entrega> listarEntregas() {
        return entregaRepository.findAll();
    }

    // Obtener entrega por ID
    public Optional<Entrega> obtenerEntregaPorId(Long id) {
        return entregaRepository.findById(id);
    }

    // Crear una nueva entrega usando DTO
    public Entrega crearEntrega(EntregaDTO dto) {
        Entrega entrega = new Entrega();
        entrega.setEstado(dto.getEstado());
        entrega.setFechaEntrega(dto.getFechaEntrega());

        // Asignar OrdenEnvio
        OrdenEnvio orden = ordenEnvioRepository.findById(dto.getOrdenEnvioId())
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        entrega.setOrdenEnvio(orden);

        // Asignar Transportista
        Transportista transportista = transportistaRepository.findById(dto.getTransportistaId())
                .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));
        entrega.setTransportista(transportista);

        return entregaRepository.save(entrega);
    }

    // Actualizar una entrega existente usando DTO
    public Optional<Entrega> actualizarEntrega(Long id, EntregaDTO dto) {
        return entregaRepository.findById(id).map(entrega -> {
            entrega.setEstado(dto.getEstado());
            entrega.setFechaEntrega(dto.getFechaEntrega());

            OrdenEnvio orden = ordenEnvioRepository.findById(dto.getOrdenEnvioId())
                    .orElseThrow(() -> new RuntimeException("Orden no encontrada"));
            entrega.setOrdenEnvio(orden);

            Transportista transportista = transportistaRepository.findById(dto.getTransportistaId())
                    .orElseThrow(() -> new RuntimeException("Transportista no encontrado"));
            entrega.setTransportista(transportista);

            return entregaRepository.save(entrega);
        });
    }

    // Eliminar entrega
    public boolean eliminarEntrega(Long id) {
        if (entregaRepository.existsById(id)) {
            entregaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
