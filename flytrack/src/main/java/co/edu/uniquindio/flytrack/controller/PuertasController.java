package co.edu.uniquindio.flytrack.controller;

import co.edu.uniquindio.flytrack.model.Puerta;
import co.edu.uniquindio.flytrack.service.PuertasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/puertas")
public class PuertasController {

    private final PuertasService puertasService;

    public PuertasController(PuertasService puertasService) {
        this.puertasService = puertasService;
    }

    @GetMapping("/{vuelo}")
    public ResponseEntity<Puerta> findByVuelo(@PathVariable String vuelo) {
        return puertasService.findByVuelo(vuelo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Puerta>> findAll() {
        return ResponseEntity.ok(puertasService.findAll());
    }

}
