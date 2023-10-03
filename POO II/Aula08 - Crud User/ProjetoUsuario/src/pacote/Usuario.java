package pacote;

public class Usuario {
    private int id;
    private String nome;
    private int idade;

    // Construtor
    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
    
    public Usuario(int id, String nome, int idade) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
	}

	// Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", idade=" + idade + "]";
    }
}
