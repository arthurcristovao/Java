

import br.edu.ifrs.osorio.projetobiblioteca.EmailService;
import br.edu.ifrs.osorio.projetobiblioteca.LivroDAO;
import br.edu.ifrs.osorio.projetobiblioteca.GerenciadorLivro;
import br.edu.ifrs.osorio.projetobiblioteca.Livro;
import br.edu.ifrs.osorio.projetobiblioteca.StatusLivro;
import java.time.LocalDate;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GerenciadorLivroTest {

    @Test
    @DisplayName("Testa locação de livro")
    public void testaLocarLivro() {
        LivroDAO livroDAO = mock(LivroDAO.class);
        EmailService emailService = mock(EmailService.class);
        Livro livroDisponivel = mock(Livro.class);
        Livro livroReservado = mock(Livro.class);
        when(livroDAO.get(1)).thenReturn(livroDisponivel);
        when(livroDisponivel.getStatus()).thenReturn(StatusLivro.DISPONIVEL);
        when(livroReservado.getStatus()).thenReturn(StatusLivro.RESERVADO);
        when(livroReservado.getEmailUsuario()).thenReturn("email@teste.com");
        GerenciadorLivro gerenciadorLivro = new GerenciadorLivro(livroDAO, emailService);
        gerenciadorLivro.locarLivro(1, "email@teste.com");
        verify(livroDisponivel).locar("email@teste.com");
        verify(livroDAO).alterar(livroDisponivel);
        gerenciadorLivro.locarLivro(2, "email@teste.com");
        verify(livroReservado).locar("email@teste.com");
        verify(livroDAO).alterar(livroReservado);
    }

    @Test
    @DisplayName("Testa devolução de livro")
    public void testaDevolverLivro() {
        LivroDAO livroDAO = mock(LivroDAO.class);
        EmailService emailService = mock(EmailService.class);
        Livro livroLocado = mock(Livro.class);
        when(livroDAO.get(1)).thenReturn(livroLocado);
        when(livroLocado.getStatus()).thenReturn(StatusLivro.LOCADO);
        GerenciadorLivro gerenciadorLivro = new GerenciadorLivro(livroDAO, emailService);
        gerenciadorLivro.devolverLivro(1);
        verify(livroLocado).devolver();
        verify(livroDAO).alterar(livroLocado);
    }

    @Test
    @DisplayName("Testa reserva de livro")
    public void testaReservarLivro() {
        LivroDAO livroDAO = mock(LivroDAO.class);
        EmailService emailService = mock(EmailService.class);
        Livro livroDisponivel = mock(Livro.class);
        when(livroDAO.get(1)).thenReturn(livroDisponivel);
        when(livroDisponivel.getStatus()).thenReturn(StatusLivro.DISPONIVEL);
        GerenciadorLivro gerenciadorLivro = new GerenciadorLivro(livroDAO, emailService);
        gerenciadorLivro.reservarLivro(1, "email@teste.com");
        verify(livroDisponivel).reservar("email@teste.com");
        verify(livroDAO).alterar(livroDisponivel);
    }

    @Test
    @DisplayName("Testa aviso de reserva no final")
    public void testaAvisarReservaNoFinal() {
        LivroDAO livroDAO = mock(LivroDAO.class);
        EmailService emailService = mock(EmailService.class);
        List<Livro> reservados = new ArrayList<>();
        Livro livro1 = mock(Livro.class);
        Livro livro2 = mock(Livro.class);
        when(livro1.getDataReserva()).thenReturn(LocalDate.now().minusDays(6));
        when(livro2.getDataReserva()).thenReturn(LocalDate.now().minusDays(5));
        reservados.add(livro1);
        reservados.add(livro2);
        when(livroDAO.listarReservados()).thenReturn(reservados);
        GerenciadorLivro gerenciadorLivro = new GerenciadorLivro(livroDAO, emailService);
        gerenciadorLivro.avisarReservaNoFinal();
        verify(emailService).enviaEmail(anyString(), anyString(), anyString());
    }

    @Test
    @DisplayName("Testa aviso de locação final")
    public void testaAvisarLocacaoFinal() {
        LivroDAO livroDAO = mock(LivroDAO.class);
        EmailService emailService = mock(EmailService.class);
        List<Livro> locados = new ArrayList<>();
        Livro livro1 = mock(Livro.class);
        Livro livro2 = mock(Livro.class);
        when(livro1.getDataReserva()).thenReturn(LocalDate.now().minusDays(13));
        when(livro2.getDataReserva()).thenReturn(LocalDate.now().minusDays(12));
        locados.add(livro1);
        locados.add(livro2);
        when(livroDAO.listarReservados()).thenReturn(locados);
        GerenciadorLivro gerenciadorLivro = new GerenciadorLivro(livroDAO, emailService);
        gerenciadorLivro.avisarLocacaoFinal();
        verify(emailService, times(1)).enviaEmail(anyString(), anyString(), anyString());
    }
}
