package co.edu.uniquindio.flytrack.model;

public class Puerta {

    private String puerta;
    private String vuelo;
    private String terminal;

    public Puerta() {}

    public Puerta(String puerta, String vuelo, String terminal) {
        this.puerta = puerta;
        this.vuelo = vuelo;
        this.terminal = terminal;
    }

    public String getPuerta() { return puerta; }
    public void setPuerta(String puerta) { this.puerta = puerta; }

    public String getVuelo() { return vuelo; }
    public void setVuelo(String vuelo) { this.vuelo = vuelo; }

    public String getTerminal() { return terminal; }
    public void setTerminal(String terminal) { this.terminal = terminal; }
}
