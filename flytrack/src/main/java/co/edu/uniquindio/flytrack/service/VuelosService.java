package co.edu.uniquindio.flytrack.service;

import co.edu.uniquindio.flytrack.model.Vuelo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VuelosService {

    private final List<Vuelo> vuelos = new ArrayList<>();

    public VuelosService() {
        vuelos.add(new Vuelo(1L, "AV101", "Bogotá", "Medellín", "08:00", "A tiempo"));
        vuelos.add(new Vuelo(2L, "AV202", "Cali", "Cartagena", "10:30", "Retrasado"));
        vuelos.add(new Vuelo(3L, "AV303", "Pereira", "Bogotá", "14:15", "Cancelado"));
    }

    public Optional<Vuelo> findById(Long id) {
        return vuelos.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();
    }

    public List<Vuelo> findAll() {
        return List.copyOf(vuelos);
    }
}
