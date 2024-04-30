package br.edu.ifrs.osorio.projetobiblioteca;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private List<Livro> livros;

    public LivroDAO() {
        this.livros = new ArrayList<>();
    }

    public void inserir(Livro l) {
        livros.add(l);
    }

    public void deletar(int id) {
        livros.removeIf(livro -> livro.getId() == id);
    }

    public void alterar(Livro l) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId() == l.getId()) {
                livros.set(i, l);
                break;
            }
        }
    }

    public Livro get(int id) {
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> listar(int limit, int offset) {
        int startIndex = Math.min(offset, livros.size());
        int endIndex = Math.min(offset + limit, livros.size());
        return livros.subList(startIndex, endIndex);
    }

    public List<Livro> listarReservados() {
        List<Livro> reservados = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getStatus() == StatusLivro.RESERVADO) {
                reservados.add(livro);
            }
        }
        return reservados;
    }
}
