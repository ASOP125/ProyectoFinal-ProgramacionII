package umg.edu.logisticayenvios.dto;

import java.time.LocalDate;

public class EntregaDTO {
    private String estado;
    private LocalDate fechaEntrega;
    private Long ordenEnvioId;
    private Long transportistaId;

    // Getters y setters
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public Long getOrdenEnvioId() { return ordenEnvioId; }
    public void setOrdenEnvioId(Long ordenEnvioId) { this.ordenEnvioId = ordenEnvioId; }

    public Long getTransportistaId() { return transportistaId; }
    public void setTransportistaId(Long transportistaId) { this.transportistaId = transportistaId; }
}
