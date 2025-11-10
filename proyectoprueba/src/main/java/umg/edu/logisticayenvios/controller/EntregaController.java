package umg.edu.logisticayenvios.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umg.edu.logisticayenvios.dto.EntregaDTO;
import umg.edu.logisticayenvios.entity.Entrega;
import umg.edu.logisticayenvios.service.EntregaService;

import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@CrossOrigin(origins = "*")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    // Listar todas las entregas
    @GetMapping
    public ResponseEntity<List<Entrega>> listarEntregas() {
        List<Entrega> entregas = entregaService.listarEntregas();
        return ResponseEntity.ok(entregas);
    }

    // Obtener entrega por ID
    @GetMapping("/{id}")
    public ResponseEntity<Entrega> obtenerEntrega(@PathVariable Long id) {
        return entregaService.obtenerEntregaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nueva entrega usando DTO
    @PostMapping
    public ResponseEntity<Entrega> crearEntrega(@RequestBody EntregaDTO dto) {
        try {
            Entrega nuevaEntrega = entregaService.crearEntrega(dto);
            return ResponseEntity.status(201).body(nuevaEntrega);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualizar entrega existente usando DTO
    @PutMapping("/{id}")
    public ResponseEntity<Entrega> actualizarEntrega(@PathVariable Long id, @RequestBody EntregaDTO dto) {
        try {
            return entregaService.actualizarEntrega(id, dto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Eliminar entrega
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrega(@PathVariable Long id) {
        boolean eliminado = entregaService.eliminarEntrega(id);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
