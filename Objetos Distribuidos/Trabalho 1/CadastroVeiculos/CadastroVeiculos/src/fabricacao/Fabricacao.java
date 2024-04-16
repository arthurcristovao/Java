
package fabricacao;

import veiculo.Veiculo;
import fabricante.Fabricante;
import java.time.LocalDate;


public class Fabricacao {
    private int idFabricacao;
    private Veiculo veiculo;
    private Fabricante fabricante;
    private LocalDate dataFabricacao;
    private String paisFabricacao;

    public Fabricacao(int idFabricacao, Veiculo veiculo, Fabricante fabricante, LocalDate dataFabricacao, String paisFabricacao) {
        this.idFabricacao = idFabricacao;
        this.veiculo = veiculo;
        this.fabricante = fabricante;
        this.dataFabricacao = dataFabricacao;
        this.paisFabricacao = paisFabricacao;
    }

    public Fabricacao(Veiculo veiculo, Fabricante fabricante, LocalDate dataFabricacao, String paisFabricacao) {
        this.veiculo = veiculo;
        this.fabricante = fabricante;
        this.dataFabricacao = dataFabricacao;
        this.paisFabricacao = paisFabricacao;
    }

    public int getIdFabricacao() {
        return idFabricacao;
    }

    public void setIdFabricacao(int idFabricacao) {
        this.idFabricacao = idFabricacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getPaisFabricacao() {
        return paisFabricacao;
    }

    public void setPaisFabricacao(String paisFabricacao) {
        this.paisFabricacao = paisFabricacao;
    }
}
