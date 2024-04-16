
package pacote;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class MotoTest {
    private Moto moto;

    @BeforeEach
    public void setUp() {
        moto = new Moto("Titan 125");
        moto.abastecer(1); 
    }

    @Test
    public void testAcelera() {
        moto.acelera(50);
        assertEquals(50, moto.getVelocidade());
    }

    @Test
    public void testFreia() {
        moto.acelera(50);
        moto.freia(30);
        assertEquals(20, moto.getVelocidade());
    }

    @Test
    public void testAbastecer() {
        moto.abastecer(5);
        assertEquals(6, moto.getGasolina());
    }

    @Test
    public void testAbastecerComQuantidadeNegativa() {
        assertThrows(IllegalArgumentException.class, () -> moto.abastecer(-5));
        
    }

    @Test
    public void testAceleraComVelocidadeNegativa() {
        assertThrows(IllegalArgumentException.class, () -> moto.acelera(-50));
    }

    @Test
    public void testFreiaComVelocidadeNegativa() {
        assertThrows(IllegalArgumentException.class, () -> moto.freia(-30));
    }

    @Test
    public void testAceleraComGasolinaInsuficiente() {
        moto.acelera(100);
        moto.acelera(10);
        assertEquals(0, moto.getGasolina()); 
        assertEquals(0, moto.getVelocidade()); 
    }
}