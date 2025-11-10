package umg.edu.logisticayenvios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.edu.logisticayenvios.entity.Transportista;
import umg.edu.logisticayenvios.service.TransportistaService;

import java.util.List;

@RestController
@RequestMapping("/api/transportistas")
@CrossOrigin(origins = "*")
public class TransportistaController {

    private final TransportistaService transportistaService;

    public TransportistaController(TransportistaService transportistaService) {
        this.transportistaService = transportistaService;
    }

    @GetMapping
    public List<Transportista> listarTransportistas() {
        return transportistaService.listarTransportistas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportista> obtenerTransportista(@PathVariable Long id) {
        return transportistaService.obtenerTransportistaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Transportista crearTransportista(@RequestBody Transportista transportista) {
        return transportistaService.crearTransportista(transportista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transportista> actualizarTransportista(@PathVariable Long id, @RequestBody Transportista actualizado) {
        return transportistaService.actualizarTransportista(id, actualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTransportista(@PathVariable Long id) {
        boolean eliminado = transportistaService.eliminarTransportista(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
