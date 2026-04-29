package co.edu.uniquindio.flytrack.service;

import co.edu.uniquindio.flytrack.model.ReporteEquipaje;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("EquipajeService")
class EquipajeServiceTest {

    private EquipajeService equipajeService;

    @BeforeEach
    void setUp() {
        equipajeService = new EquipajeService();
    }

    @Test
    @DisplayName("save asigna un id no nulo al reporte")
    void save_asignaId() {
        ReporteEquipaje reporte = new ReporteEquipaje(null, "Juan Pérez", "AV101", "Maleta perdida");

        ReporteEquipaje guardado = equipajeService.save(reporte);

        assertThat(guardado.getId()).isNotNull();
    }

    @Test
    @DisplayName("dos saves consecutivos asignan ids distintos y crecientes")
    void save_idsIncrementales() {
        ReporteEquipaje primero = equipajeService.save(new ReporteEquipaje(null, "Ana", "AV101", "Maleta dañada"));
        ReporteEquipaje segundo = equipajeService.save(new ReporteEquipaje(null, "Luis", "AV202", "Maleta perdida"));

        assertThat(segundo.getId()).isGreaterThan(primero.getId());
    }

    @Test
    @DisplayName("la lista de reportes crece con cada save")
    void save_listaCrece() {
        assertThat(equipajeService.count()).isZero();

        equipajeService.save(new ReporteEquipaje(null, "María", "AV303", "Objeto olvidado"));

        assertThat(equipajeService.count()).isEqualTo(1);

        equipajeService.save(new ReporteEquipaje(null, "Carlos", "AV101", "Maleta rota"));

        assertThat(equipajeService.count()).isEqualTo(2);
    }

    @Test
    @DisplayName("save preserva todos los campos del reporte")
    void save_preservaCampos() {
        ReporteEquipaje reporte = new ReporteEquipaje(null, "Pedro Gómez", "AV202", "Equipaje retrasado");

        ReporteEquipaje guardado = equipajeService.save(reporte);

        assertThat(guardado.getPasajero()).isEqualTo("Pedro Gómez");
        assertThat(guardado.getVuelo()).isEqualTo("AV202");
        assertThat(guardado.getDescripcion()).isEqualTo("Equipaje retrasado");
    }

    @Test
    @DisplayName("múltiples reportes son almacenados con ids únicos")
    void save_multiples_idsUnicos() {
        equipajeService.save(new ReporteEquipaje(null, "A", "AV101", "desc1"));
        equipajeService.save(new ReporteEquipaje(null, "B", "AV202", "desc2"));
        equipajeService.save(new ReporteEquipaje(null, "C", "AV303", "desc3"));

        long idsDistintos = equipajeService.findAll().stream()
                .map(ReporteEquipaje::getId)
                .distinct()
                .count();

        assertThat(idsDistintos).isEqualTo(3);
    }

    @Test
    @DisplayName("lista inicia vacía antes de cualquier save")
    void listaIniciaVacia() {
        assertThat(equipajeService.findAll()).isEmpty();
    }
}
