import br.edu.ifrs.osorio.projetobiblioteca.Livro;
import br.edu.ifrs.osorio.projetobiblioteca.StatusLivro;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LivroTest {

    @Test
    @DisplayName("Testa reserva de livro")
    public void testaReservarLivro() {
        Livro livro = mock(Livro.class);
        when(livro.getStatus()).thenReturn(StatusLivro.DISPONIVEL);
        livro.reservar("email@teste.com");
        verify(livro).setStatus(StatusLivro.RESERVADO);
        verify(livro).setEmailUsuario("email@teste.com");
    }

    @Test
    @DisplayName("Testa locação de livro")
    public void testaLocarLivro() {
        Livro livro = mock(Livro.class);
        when(livro.getStatus()).thenReturn(StatusLivro.DISPONIVEL);
        livro.locar("email@teste.com");
        verify(livro).setStatus(StatusLivro.LOCADO);
        verify(livro).setEmailUsuario("email@teste.com");
    }

    @Test
    @DisplayName("Testa devolução de livro")
    public void testaDevolverLivro() {
        Livro livro = mock(Livro.class);
        when(livro.getStatus()).thenReturn(StatusLivro.LOCADO);
        livro.devolver();
        verify(livro).setStatus(StatusLivro.DISPONIVEL);
        verify(livro).setEmailUsuario(null);
    }
}
