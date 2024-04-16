
package autor;

public class Autor {
    private int idAutor;
    private String nome;
    private int anoNascimento;
    private String nacionalidade;

    public Autor(int idAutor, String nome, int anoNascimento, String nacionalidade) {
        this.idAutor = idAutor;
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.nacionalidade = nacionalidade;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    @Override
    public String toString() {
        return "Autor{" + "idAutor=" + idAutor + ", nome=" + nome + ", anoNascimento=" + anoNascimento + ", nacionalidade=" + nacionalidade + '}';
    }
    
    
}
