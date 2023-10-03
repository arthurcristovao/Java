/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estoque;

import java.util.Date;

/**
 *
 * @author Arthur
 */
public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private Date dataValidade;
    private boolean importado;
    private Estoque estoque;
    
    public Produto(int codigo, String nome, double preco, Estoque estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Produto(int codigo, String nome, double preco, Date dataValidade, Estoque estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.dataValidade = dataValidade;
        this.estoque = estoque;
    }

    public Produto(int codigo, String nome, double preco, Date dataValidade, boolean importado, Estoque estoque) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.dataValidade = dataValidade;
        this.importado = importado;
        this.estoque = estoque;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean isImportado() {
        return importado;
    }

    public void setImportado(boolean importado) {
        this.importado = importado;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
    
    
    
    
    
}
