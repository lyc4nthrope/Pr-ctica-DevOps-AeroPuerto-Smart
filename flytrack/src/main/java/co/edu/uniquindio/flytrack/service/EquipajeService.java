package co.edu.uniquindio.flytrack.service;

import co.edu.uniquindio.flytrack.model.ReporteEquipaje;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class EquipajeService {

    private final List<ReporteEquipaje> reportes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ReporteEquipaje save(ReporteEquipaje reporte) {
        reporte.setId(idGenerator.getAndIncrement());
        reportes.add(reporte);
        return reporte;
    }

    public List<ReporteEquipaje> findAll() {
        return List.copyOf(reportes);
    }

    public int count() {
        return reportes.size();
    }
}
