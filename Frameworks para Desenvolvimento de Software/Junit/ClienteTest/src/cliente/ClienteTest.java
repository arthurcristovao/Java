package cliente;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import cliente.Cliente;
import cliente.ClienteException;

class ClienteTest {

    @Test
    void testCriacaoClienteCpfInvalido() {
        assertThrows(ClienteException.class, () -> {
            new Cliente("12345678900", "Fulano", LocalDate.of(1990, 1, 1));
        });
    }

    @Test
    void testCriacaoClienteNomeInvalido() {
        assertThrows(ClienteException.class, () -> {
            new Cliente("123.456.789-01", "Fulano123", LocalDate.of(1990, 1, 1));
        });
    }

    @Test
    void testCriacaoClienteDataNascimentoInvalida() {
        assertThrows(ClienteException.class, () -> {
            new Cliente("123.456.789-01", "Fulano", LocalDate.of(1800, 1, 1));
        });
    }

    @Test
    void testGettersSetters() throws ClienteException {
        Cliente cliente = new Cliente("123.456.789-01", "Fulano", LocalDate.of(1990, 1, 1));
        assertEquals("123.456.789-01", cliente.getCpf());
        assertEquals("Fulano", cliente.getNome());
        assertEquals(LocalDate.of(1990, 1, 1), cliente.getDataNascimento());

        cliente.setNome("Ciclano");
        assertEquals("Ciclano", cliente.getNome());
    }
}
