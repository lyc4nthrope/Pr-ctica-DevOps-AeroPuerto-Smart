package co.edu.uniquindio.flytrack.controller;

import co.edu.uniquindio.flytrack.model.Vuelo;
import co.edu.uniquindio.flytrack.service.VuelosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vuelos")
public class VuelosController {

    private final VuelosService vuelosService;

    public VuelosController(VuelosService vuelosService) {
        this.vuelosService = vuelosService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vuelo> findById(@PathVariable Long id) {
        return vuelosService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
