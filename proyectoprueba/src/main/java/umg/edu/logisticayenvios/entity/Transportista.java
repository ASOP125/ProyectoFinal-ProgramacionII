package umg.edu.logisticayenvios.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "transportistas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transportista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String telefono;
    private String vehiculo;

    @OneToMany(mappedBy = "transportista", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("transportista")
    private List<Entrega> entregas;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getVehiculo() { return vehiculo; }
    public void setVehiculo(String vehiculo) { this.vehiculo = vehiculo; }

    public List<Entrega> getEntregas() { return entregas; }
    public void setEntregas(List<Entrega> entregas) { this.entregas = entregas; }
}
