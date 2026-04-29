package co.edu.uniquindio.flytrack.service;

import co.edu.uniquindio.flytrack.model.Vuelo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("VuelosService")
class VuelosServiceTest {

    private VuelosService vuelosService;

    @BeforeEach
    void setUp() {
        vuelosService = new VuelosService();
    }

    @Test
    @DisplayName("findById con id existente retorna el vuelo correcto")
    void findById_existente_retornaVuelo() {
        Optional<Vuelo> resultado = vuelosService.findById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getId()).isEqualTo(1L);
        assertThat(resultado.get().getNumero()).isEqualTo("AV101");
    }

    @Test
    @DisplayName("findById con id inexistente retorna Optional vacío")
    void findById_inexistente_retornaVacio() {
        Optional<Vuelo> resultado = vuelosService.findById(999L);

        assertThat(resultado).isEmpty();
    }

    @Test
    @DisplayName("findAll retorna lista no vacía al iniciar")
    void findAll_listaNoVacia() {
        List<Vuelo> todos = vuelosService.findAll();

        assertThat(todos).isNotEmpty();
        assertThat(todos).hasSizeGreaterThanOrEqualTo(3);
    }

    @Test
    @DisplayName("findById retorna vuelo con todos sus campos completos")
    void findById_camposCompletos() {
        Optional<Vuelo> resultado = vuelosService.findById(2L);

        assertThat(resultado).isPresent();
        Vuelo vuelo = resultado.get();
        assertThat(vuelo.getNumero()).isNotBlank();
        assertThat(vuelo.getOrigen()).isNotBlank();
        assertThat(vuelo.getDestino()).isNotBlank();
        assertThat(vuelo.getHora()).isNotBlank();
        assertThat(vuelo.getEstado()).isNotBlank();
    }

    @Test
    @DisplayName("cada vuelo en la lista tiene un id único")
    void findAll_idsUnicos() {
        List<Vuelo> todos = vuelosService.findAll();

        long idsDistintos = todos.stream().map(Vuelo::getId).distinct().count();
        assertThat(idsDistintos).isEqualTo(todos.size());
    }

    @Test
    @DisplayName("todos los vuelos en la lista tienen ids existentes al buscarlos")
    void findAll_cadaIdEsBuscable() {
        List<Vuelo> todos = vuelosService.findAll();

        todos.forEach(v ->
            assertThat(vuelosService.findById(v.getId())).isPresent()
        );
    }
}
