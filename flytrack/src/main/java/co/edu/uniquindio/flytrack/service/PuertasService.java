package co.edu.uniquindio.flytrack.service;

import co.edu.uniquindio.flytrack.model.Puerta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PuertasService {

    private final List<Puerta> puertas = new ArrayList<>();

    public PuertasService() {
        puertas.add(new Puerta("A12", "AV101", "Terminal Norte"));
        puertas.add(new Puerta("B07", "AV202", "Terminal Sur"));
        puertas.add(new Puerta("C03", "AV303", "Terminal Internacional"));
    }

    public Optional<Puerta> findByVuelo(String vuelo) {
        return puertas.stream()
                .filter(p -> p.getVuelo().equalsIgnoreCase(vuelo))
                .findFirst();
    }

    public List<Puerta> findAll() {
        return List.copyOf(puertas);
    }
}
