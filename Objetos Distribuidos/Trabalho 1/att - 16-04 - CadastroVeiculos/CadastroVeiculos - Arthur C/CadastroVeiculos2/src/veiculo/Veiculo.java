
package veiculo;

public class Veiculo {
    private int idVeiculo;
    private String nome;
    private String cor;
    private String modelo;

    public Veiculo(int idVeiculo, String nome, String cor, String modelo) {
        this.idVeiculo = idVeiculo;
        this.nome = nome;
        this.cor = cor;
        this.modelo = modelo;
    }

    public Veiculo(String nome, String cor, String modelo) {
        this.nome = nome;
        this.cor = cor;
        this.modelo = modelo;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
