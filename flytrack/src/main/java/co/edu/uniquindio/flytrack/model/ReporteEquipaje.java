package co.edu.uniquindio.flytrack.model;

public class ReporteEquipaje {

    private Long id;
    private String pasajero;
    private String vuelo;
    private String descripcion;

    public ReporteEquipaje() {}

    public ReporteEquipaje(Long id, String pasajero, String vuelo, String descripcion) {
        this.id = id;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.descripcion = descripcion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPasajero() { return pasajero; }
    public void setPasajero(String pasajero) { this.pasajero = pasajero; }

    public String getVuelo() { return vuelo; }
    public void setVuelo(String vuelo) { this.vuelo = vuelo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
