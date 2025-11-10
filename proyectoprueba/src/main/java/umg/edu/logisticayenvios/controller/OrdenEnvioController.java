package umg.edu.logisticayenvios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.edu.logisticayenvios.entity.OrdenEnvio;
import umg.edu.logisticayenvios.service.OrdenEnvioService;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
@CrossOrigin(origins = "*")
public class OrdenEnvioController {

    private final OrdenEnvioService ordenEnvioService;

    public OrdenEnvioController(OrdenEnvioService ordenEnvioService) {
        this.ordenEnvioService = ordenEnvioService;
    }

    @GetMapping
    public List<OrdenEnvio> listarOrdenes() {
        return ordenEnvioService.listarOrdenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenEnvio> obtenerOrden(@PathVariable Long id) {
        return ordenEnvioService.obtenerOrdenPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public OrdenEnvio crearOrden(@RequestBody OrdenEnvio orden) {
        return ordenEnvioService.crearOrden(orden);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdenEnvio> actualizarOrden(@PathVariable Long id, @RequestBody OrdenEnvio ordenActualizada) {
        return ordenEnvioService.actualizarOrden(id, ordenActualizada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        boolean eliminado = ordenEnvioService.eliminarOrden(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
