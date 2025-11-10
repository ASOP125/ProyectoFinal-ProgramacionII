package umg.edu.logisticayenvios.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ordenes_envio")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrdenEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direccionDestino;
    private LocalDate fechaEnvio;
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente cliente;

    @OneToOne(mappedBy = "ordenEnvio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("ordenEnvio")
    private Entrega entrega;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDireccionDestino() { return direccionDestino; }
    public void setDireccionDestino(String direccionDestino) { this.direccionDestino = direccionDestino; }

    public LocalDate getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDate fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Entrega getEntrega() { return entrega; }
    public void setEntrega(Entrega entrega) { this.entrega = entrega; }
}
