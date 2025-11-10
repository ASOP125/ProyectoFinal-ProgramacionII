package umg.edu.logisticayenvios.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "entregas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private LocalDate fechaEntrega;

    @OneToOne
    @JoinColumn(name = "orden_envio_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "entrega"})
    private OrdenEnvio ordenEnvio;

    @ManyToOne
    @JoinColumn(name = "transportista_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "entregas"})
    private Transportista transportista;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public OrdenEnvio getOrdenEnvio() { return ordenEnvio; }
    public void setOrdenEnvio(OrdenEnvio ordenEnvio) { this.ordenEnvio = ordenEnvio; }

    public Transportista getTransportista() { return transportista; }
    public void setTransportista(Transportista transportista) { this.transportista = transportista; }
}
