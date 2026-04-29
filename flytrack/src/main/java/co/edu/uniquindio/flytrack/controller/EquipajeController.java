package co.edu.uniquindio.flytrack.controller;

import co.edu.uniquindio.flytrack.model.ReporteEquipaje;
import co.edu.uniquindio.flytrack.service.EquipajeService;
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
    public ResponseEntity<ReporteEquipaje> crear(@RequestBody ReporteEquipaje reporte) {
        if (reporte.getPasajero() == null || reporte.getPasajero().isBlank()
                || reporte.getVuelo() == null || reporte.getVuelo().isBlank()
                || reporte.getDescripcion() == null || reporte.getDescripcion().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        ReporteEquipaje guardado = equipajeService.save(reporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }
}
