package co.edu.uniquindio.flytrack.controller;

import co.edu.uniquindio.flytrack.model.Puerta;
import co.edu.uniquindio.flytrack.service.PuertasService;
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

@WebMvcTest(PuertasController.class)
@DisplayName("PuertasController")
class PuertasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PuertasService puertasService;

    @Test
    @DisplayName("GET /api/puertas/{vuelo} con vuelo existente retorna 200 y JSON")
    void findByVuelo_existente_retorna200() throws Exception {
        Puerta puerta = new Puerta("A12", "AV101", "Terminal Norte");
        when(puertasService.findByVuelo("AV101")).thenReturn(Optional.of(puerta));

        mockMvc.perform(get("/api/puertas/AV101"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.puerta").value("A12"))
                .andExpect(jsonPath("$.vuelo").value("AV101"))
                .andExpect(jsonPath("$.terminal").value("Terminal Norte"));
    }

    @Test
    @DisplayName("GET /api/puertas/{vuelo} con vuelo inexistente retorna 404")
    void findByVuelo_inexistente_retorna404() throws Exception {
        when(puertasService.findByVuelo("XXXX")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/puertas/XXXX"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /api/puertas/{vuelo} retorna Content-Type application/json")
    void findByVuelo_contentTypeJson() throws Exception {
        Puerta puerta = new Puerta("B07", "AV202", "Terminal Sur");
        when(puertasService.findByVuelo("AV202")).thenReturn(Optional.of(puerta));

        mockMvc.perform(get("/api/puertas/AV202"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("GET /api/puertas/{vuelo} respuesta JSON contiene los tres campos esperados")
    void findByVuelo_jsonContieneCampos() throws Exception {
        Puerta puerta = new Puerta("C03", "AV303", "Terminal Internacional");
        when(puertasService.findByVuelo("AV303")).thenReturn(Optional.of(puerta));

        mockMvc.perform(get("/api/puertas/AV303"))
                .andExpect(jsonPath("$.puerta").exists())
                .andExpect(jsonPath("$.vuelo").exists())
                .andExpect(jsonPath("$.terminal").exists());
    }
}
