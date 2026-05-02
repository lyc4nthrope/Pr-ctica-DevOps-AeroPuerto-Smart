package co.edu.uniquindio.flytrack.controller;

import co.edu.uniquindio.flytrack.model.ReporteEquipaje;
import co.edu.uniquindio.flytrack.service.EquipajeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipaje")
public class EquipajeController {

    private final EquipajeService equipajeService;

    public EquipajeController(EquipajeService equipajeService) {
        this.equipajeService = equipajeService;
    }

    @PostMapping("/reporte")
    public ResponseEntity<ReporteEquipaje> crear(@Valid @RequestBody ReporteEquipaje reporte) {
        ReporteEquipaje guardado = equipajeService.save(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

}
