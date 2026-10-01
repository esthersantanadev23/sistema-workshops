package br.com.workshops.service;

import br.com.workshops.model.Workshop;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WorkshopServiceTest {

    @Test
    void deveRejeitarWorkshopSemTitulo() {
        WorkshopService service = new WorkshopService();

        Workshop workshop = new Workshop(
                null,
                "",
                "Descrição",
                LocalDate.of(2026, 10, 9),
                LocalTime.of(19, 0),
                "Laboratório",
                30
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.inserir(workshop)
        );
    }

    @Test
    void deveRejeitarWorkshopSemVagas() {
        WorkshopService service = new WorkshopService();

        Workshop workshop = new Workshop(
                null,
                "Workshop de Java",
                "Descrição",
                LocalDate.of(2026, 10, 9),
                LocalTime.of(19, 0),
                "Laboratório",
                0
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.inserir(workshop)
        );
    }

    @Test
    void deveRejeitarAlteracaoSemId() {
        WorkshopService service = new WorkshopService();

        Workshop workshop = new Workshop(
                null,
                "Workshop de Java",
                "Descrição",
                LocalDate.of(2026, 10, 9),
                LocalTime.of(19, 0),
                "Laboratório",
                30
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> service.alterar(workshop)
        );
    }
}