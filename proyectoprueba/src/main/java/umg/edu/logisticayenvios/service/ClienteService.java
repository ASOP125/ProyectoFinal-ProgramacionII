package umg.edu.logisticayenvios.service;

import org.springframework.stereotype.Service;
import umg.edu.logisticayenvios.entity.Cliente;
import umg.edu.logisticayenvios.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    // Constructor Injection
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar todos los clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Obtener cliente por ID
    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Guardar nuevo cliente
    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Actualizar cliente
    public Optional<Cliente> actualizarCliente(Long id, Cliente clienteActualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setDireccion(clienteActualizado.getDireccion());
            cliente.setTelefono(clienteActualizado.getTelefono());
            return clienteRepository.save(cliente);
        });
    }

    // Eliminar cliente
    public boolean eliminar(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
