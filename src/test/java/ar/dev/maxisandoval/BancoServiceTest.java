package ar.dev.maxisandoval;

import ar.dev.maxisandoval.entity.*;
import ar.dev.maxisandoval.exception.BancoNotFoundException;
import ar.dev.maxisandoval.service.BancoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class BancoServiceTest {

    private final BancoService bancoService;
    private final Faker faker = new Faker(Locale.forLanguageTag("es-AR"));

    private Banco construirBanco() {
        String company = faker.company().name();
        String country = faker.country().name();

        log.info("company -> "+company);

        return Banco.builder()
                .nombre(company)
                .pais(country)
                .activo(true)
                .build();
    }

    @BeforeEach
    void setUp() {
        bancoService.deleteAll();
        IntStream.range(0, 3).forEach(i -> bancoService.save(construirBanco()));
    }

    @Test
    void testGetAll() {
        List<Banco> all = bancoService.getAll();
        assertEquals(3, all.size());
        all.forEach(banco -> {
            assertNotNull(banco.getNombre());
            assertNotNull(banco.getActivo());
            assertFalse(banco.getPais().isEmpty());
        });
    }

    @Test
    void testSaveAndGetById() {
        Banco newBanco = construirBanco();
        Banco saved = bancoService.save(newBanco);

        Banco fetched = bancoService.getById(saved.getId());

        assertEquals(saved.getNombre(), fetched.getNombre());
        assertEquals(saved.getPais(), fetched.getPais());
    }

    @Test
    void testUpdate() {
        Banco original = bancoService.getAll().get(0);
        original.setNombre("nombreActualizado");
        original.setPais("paisActualizado");
        original.setActivo(false);

        Banco updated = bancoService.update(original.getId(), original);

        assertEquals("nombreActualizado", updated.getNombre());
        assertEquals("paisActualizado", updated.getPais());
        assertEquals(false, updated.getActivo());
    }

    @Test
    void testDelete() {
        Banco banco = bancoService.getAll().get(0);
        bancoService.deleteById(banco.getId());

        List<Banco> all = bancoService.getAll();
        assertEquals(2, all.size());
    }

    @Test
    void testGetByIdNotFound() {
        assertThrows(BancoNotFoundException.class, () -> bancoService.getById(999L));
    }

}