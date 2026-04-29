package co.edu.uniquindio.flytrack.service;

import co.edu.uniquindio.flytrack.model.Puerta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("PuertasService")
class PuertasServiceTest {

    private PuertasService puertasService;

    @BeforeEach
    void setUp() {
        puertasService = new PuertasService();
    }

    @Test
    @DisplayName("findByVuelo con vuelo existente retorna la puerta correcta")
    void findByVuelo_existente_retornaPuerta() {
        Optional<Puerta> resultado = puertasService.findByVuelo("AV101");

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getVuelo()).isEqualToIgnoringCase("AV101");
        assertThat(resultado.get().getPuerta()).isEqualTo("A12");
    }

    @Test
    @DisplayName("findByVuelo con vuelo inexistente retorna Optional vacío")
    void findByVuelo_inexistente_retornaVacio() {
        Optional<Puerta> resultado = puertasService.findByVuelo("XXXX");

        assertThat(resultado).isEmpty();
    }

    @Test
    @DisplayName("findAll retorna lista no vacía al iniciar")
    void findAll_listaNoVacia() {
        List<Puerta> todas = puertasService.findAll();

        assertThat(todas).isNotEmpty();
        assertThat(todas).hasSizeGreaterThanOrEqualTo(3);
    }

    @Test
    @DisplayName("findByVuelo retorna puerta con todos sus campos completos")
    void findByVuelo_camposCompletos() {
        Optional<Puerta> resultado = puertasService.findByVuelo("AV202");

        assertThat(resultado).isPresent();
        Puerta puerta = resultado.get();
        assertThat(puerta.getPuerta()).isNotBlank();
        assertThat(puerta.getVuelo()).isNotBlank();
        assertThat(puerta.getTerminal()).isNotBlank();
    }

    @Test
    @DisplayName("la búsqueda es insensible a mayúsculas")
    void findByVuelo_insensibleMayusculas() {
        Optional<Puerta> minusculas = puertasService.findByVuelo("av101");
        Optional<Puerta> mayusculas = puertasService.findByVuelo("AV101");

        assertThat(minusculas).isPresent();
        assertThat(mayusculas).isPresent();
        assertThat(minusculas.get().getPuerta()).isEqualTo(mayusculas.get().getPuerta());
    }

    @Test
    @DisplayName("todos los vuelos de la lista son buscables individualmente")
    void findAll_cadaVueloEsBuscable() {
        List<Puerta> todas = puertasService.findAll();

        todas.forEach(p ->
            assertThat(puertasService.findByVuelo(p.getVuelo())).isPresent()
        );
    }
}
