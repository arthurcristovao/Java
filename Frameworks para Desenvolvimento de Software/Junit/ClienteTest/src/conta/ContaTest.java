package conta;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import cliente.Cliente;
import cliente.ClienteException;
import conta.Conta;
import conta.ContaException;

import java.time.LocalDate;

class ContaTest {

    private Cliente cliente;
    private Conta conta;

    @BeforeEach
    void setUp() throws ClienteException {
        cliente = new Cliente("123.456.789-01", "Fulano", LocalDate.of(1990, 1, 1));
        conta = new Conta(1, cliente, 1000.0, 500);
    }

    @Test
    void testSacaValorNegativo() {
        assertThrows(ContaException.class, () -> {
            conta.saca(-100.0);
        });
    }

    @Test
    void testSacaSaldoInsuficiente() {
        assertThrows(ContaException.class, () -> {
            conta.saca(2000.0);
        });
    }

    @Test
    void testDepositaValorNegativo() {
        assertThrows(ContaException.class, () -> {
            conta.deposita(-100.0);
        });
    }

    @Test
    void testTransfereParaContaNula() {
        assertThrows(ContaException.class, () -> {
            conta.transfere(null, 100.0);
        });
    }

    @Test
    void testTransfereValorNegativo() {
        assertThrows(ContaException.class, () -> {
            conta.transfere(new Conta(2, cliente, 0.0, 500), -100.0);
        });
    }

    @Test
    void testTransfereSaldoInsuficiente() {
        assertThrows(ContaException.class, () -> {
            conta.transfere(new Conta(2, cliente, 0.0, 500), 2000.0);
        });
    }
}
