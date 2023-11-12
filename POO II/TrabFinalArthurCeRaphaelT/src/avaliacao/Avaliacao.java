/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avaliacao;

import aluguel.Aluguel;

/**
 *
 * @author Arthur
 */
public class Avaliacao {
    private int idAvaliacao;
    private Aluguel aluguel;
    private int nota;
    private String comentario;

    public Avaliacao(int idAvaliacao, Aluguel aluguel, int nota, String comentario) {
        this.idAvaliacao = idAvaliacao;
        this.aluguel = aluguel;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Avaliacao(Aluguel aluguel, int nota, String comentario) {
        this.aluguel = aluguel;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Avaliacao() {
    }
    
    

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Aluguel getAluguel() {
        return aluguel;
    }

    public void setAluguel(Aluguel aluguel) {
        this.aluguel = aluguel;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    
}
