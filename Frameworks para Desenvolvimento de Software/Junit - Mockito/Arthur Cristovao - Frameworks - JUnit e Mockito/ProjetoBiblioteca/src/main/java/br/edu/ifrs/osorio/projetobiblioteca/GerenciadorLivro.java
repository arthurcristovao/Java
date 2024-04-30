package br.edu.ifrs.osorio.projetobiblioteca;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.time.LocalDate;
import java.util.List;

public class GerenciadorLivro {
    private LivroDAO lDao;
    private EmailService emailService;

    public GerenciadorLivro(LivroDAO lDao, EmailService emailService) {
        this.lDao = lDao;
        this.emailService = emailService;
    }

    public void locarLivro(int id, String email) {
        Livro livro = lDao.get(id);
        if (livro != null && (livro.getStatus() == StatusLivro.DISPONIVEL || (livro.getStatus() == StatusLivro.RESERVADO && livro.getEmailUsuario().equals(email)))) {
            livro.locar(email);
            lDao.alterar(livro);
        }
    }

    public void devolverLivro(int id) {
        Livro livro = lDao.get(id);
        if (livro != null && livro.getStatus() == StatusLivro.LOCADO) {
            livro.devolver();
            lDao.alterar(livro);
        }
    }

    public void reservarLivro(int id, String email) {
        Livro livro = lDao.get(id);
        if (livro != null && livro.getStatus() == StatusLivro.DISPONIVEL) {
            livro.reservar(email);
            lDao.alterar(livro);
        }
    }

    public void avisarReservaNoFinal() {
        List<Livro> reservados = lDao.listarReservados();
        LocalDate hoje = LocalDate.now();
        for (Livro livro : reservados) {
            LocalDate dataReserva = livro.getDataReserva();
            if (dataReserva != null && hoje.minusDays(5).isBefore(dataReserva) && hoje.minusDays(7).isAfter(dataReserva)) {
                emailService.enviaEmail("Seu livro reservado '" + livro.getTitulo() + "' está próximo do prazo de retirada.", "Reserva próxima", livro.getEmailUsuario());
            }
        }
    }

    public void avisarLocacaoFinal() {
        List<Livro> locados = lDao.listarReservados();
        LocalDate hoje = LocalDate.now();
        for (Livro livro : locados) {
            LocalDate dataLocacao = livro.getDataReserva();
            if (dataLocacao != null) {
                if (hoje.minusDays(12).isBefore(dataLocacao) && hoje.minusDays(14).isAfter(dataLocacao)) {
                    emailService.enviaEmail("Seu livro locado '" + livro.getTitulo() + "' está próximo do prazo de devolução.", "Devolução próxima", livro.getEmailUsuario());
                } else if (hoje.minusDays(14).isAfter(dataLocacao)) {
                    emailService.enviaEmail("Seu livro locado '" + livro.getTitulo() + "' está com a devolução atrasada.", "Devolução atrasada", livro.getEmailUsuario());
                }
            }
        }
    }
}

