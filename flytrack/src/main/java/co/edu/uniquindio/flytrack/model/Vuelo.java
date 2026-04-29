package co.edu.uniquindio.flytrack.model;

public class Vuelo {

    private Long id;
    private String numero;
    private String origen;
    private String destino;
    private String hora;
    private String estado;

    public Vuelo() {}

    public Vuelo(Long id, String numero, String origen, String destino, String hora, String estado) {
        this.id = id;
        this.numero = numero;
        this.origen = origen;
        this.destino = destino;
        this.hora = hora;
        this.estado = estado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
