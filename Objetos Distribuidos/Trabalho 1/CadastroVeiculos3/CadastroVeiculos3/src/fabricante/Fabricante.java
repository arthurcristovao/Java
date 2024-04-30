
package fabricante;

public class Fabricante {
    private int idFabricante;
    private String nome;
    private String paisOrigem;

    public Fabricante(int idFabricante, String nome, String paisOrigem) {
        this.idFabricante = idFabricante;
        this.nome = nome;
        this.paisOrigem = paisOrigem;
    }

    public Fabricante(String nome, String paisOrigem) {
        this.nome = nome;
        this.paisOrigem = paisOrigem;
    }

    public int getIdFabricante() {
        return idFabricante;
    }

    public void setIdFabricante(int idFabricante) {
        this.idFabricante = idFabricante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }
}
