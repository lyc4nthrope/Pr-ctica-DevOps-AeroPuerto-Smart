package co.edu.uniquindio.flytrack.controller;

import co.edu.uniquindio.flytrack.model.ReporteEquipaje;
import co.edu.uniquindio.flytrack.service.EquipajeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EquipajeController.class)
@DisplayName("EquipajeController")
class EquipajeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipajeService equipajeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/equipaje/reporte con body válido retorna 201 y JSON con id")
    void crear_bodyValido_retorna201() throws Exception {
        ReporteEquipaje entrada = new ReporteEquipaje(null, "Juan Pérez", "AV101", "Maleta perdida");
        ReporteEquipaje guardado = new ReporteEquipaje(1L, "Juan Pérez", "AV101", "Maleta perdida");
        when(equipajeService.save(any(ReporteEquipaje.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/equipaje/reporte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pasajero").value("Juan Pérez"))
                .andExpect(jsonPath("$.vuelo").value("AV101"))
                .andExpect(jsonPath("$.descripcion").value("Maleta perdida"));
    }

    @Test
    @DisplayName("POST /api/equipaje/reporte con campos nulos retorna 400")
    void crear_camposNulos_retorna400() throws Exception {
        ReporteEquipaje invalido = new ReporteEquipaje(null, null, null, null);

        mockMvc.perform(post("/api/equipaje/reporte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/equipaje/reporte sin pasajero retorna 400")
    void crear_sinPasajero_retorna400() throws Exception {
        ReporteEquipaje invalido = new ReporteEquipaje(null, null, "AV101", "Maleta perdida");

        mockMvc.perform(post("/api/equipaje/reporte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/equipaje/reporte sin vuelo retorna 400")
    void crear_sinVuelo_retorna400() throws Exception {
        ReporteEquipaje invalido = new ReporteEquipaje(null, "Ana López", null, "Maleta rota");

        mockMvc.perform(post("/api/equipaje/reporte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/equipaje/reporte sin descripcion retorna 400")
    void crear_sinDescripcion_retorna400() throws Exception {
        ReporteEquipaje invalido = new ReporteEquipaje(null, "Ana López", "AV202", null);

        mockMvc.perform(post("/api/equipaje/reporte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalido)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/equipaje/reporte retorna Content-Type application/json en 201")
    void crear_retornaContentTypeJson() throws Exception {
        ReporteEquipaje entrada = new ReporteEquipaje(null, "Carlos", "AV303", "Objeto olvidado");
        ReporteEquipaje guardado = new ReporteEquipaje(2L, "Carlos", "AV303", "Objeto olvidado");
        when(equipajeService.save(any(ReporteEquipaje.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/equipaje/reporte")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}
