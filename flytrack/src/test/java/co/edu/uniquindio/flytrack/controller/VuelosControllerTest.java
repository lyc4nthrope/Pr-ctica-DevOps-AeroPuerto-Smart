package co.edu.uniquindio.flytrack.controller;

import co.edu.uniquindio.flytrack.model.Vuelo;
import co.edu.uniquindio.flytrack.service.VuelosService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VuelosController.class)
@DisplayName("VuelosController")
class VuelosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VuelosService vuelosService;

    @Test
    @DisplayName("GET /api/vuelos/{id} con id existente retorna 200 y JSON")
    void findById_existente_retorna200() throws Exception {
        Vuelo vuelo = new Vuelo(1L, "AV101", "Bogotá", "Medellín", "08:00", "A tiempo");
        when(vuelosService.findById(1L)).thenReturn(Optional.of(vuelo));

        mockMvc.perform(get("/api/vuelos/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numero").value("AV101"))
                .andExpect(jsonPath("$.origen").value("Bogotá"))
                .andExpect(jsonPath("$.destino").value("Medellín"))
                .andExpect(jsonPath("$.hora").value("08:00"))
                .andExpect(jsonPath("$.estado").value("A tiempo"));
    }

    @Test
    @DisplayName("GET /api/vuelos/{id} con id inexistente retorna 404")
    void findById_inexistente_retorna404() throws Exception {
        when(vuelosService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/vuelos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/vuelos/{id} retorna Content-Type application/json")
    void findById_contentTypeJson() throws Exception {
        Vuelo vuelo = new Vuelo(2L, "AV202", "Cali", "Cartagena", "10:30", "Retrasado");
        when(vuelosService.findById(2L)).thenReturn(Optional.of(vuelo));

        mockMvc.perform(get("/api/vuelos/2"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/vuelos/{id} respuesta JSON contiene campo numero")
    void findById_jsonContieneNumero() throws Exception {
        Vuelo vuelo = new Vuelo(3L, "AV303", "Pereira", "Bogotá", "14:15", "Cancelado");
        when(vuelosService.findById(3L)).thenReturn(Optional.of(vuelo));

        mockMvc.perform(get("/api/vuelos/3"))
                .andExpect(jsonPath("$.numero").exists());
    }
}
