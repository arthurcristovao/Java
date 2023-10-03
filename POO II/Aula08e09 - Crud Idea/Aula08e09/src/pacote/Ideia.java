package pacote;

public class Ideia {
    private int id;
    private String titulo;
    private String descricao;
    private Urgencia urgencia;

    public Ideia(String titulo, String descricao, Urgencia urgencia) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.urgencia = urgencia;
    }
    
    
    public Ideia(int id, String titulo, String descricao, Urgencia urgencia) {
    	this.id = id;
    	this.titulo = titulo;
        this.descricao = descricao;
        this.urgencia = urgencia;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Urgencia getUrgencia() {
		return urgencia;
	}

	public void setUrgencia(Urgencia urgencia) {
		this.urgencia = urgencia;
	}

	@Override
    public String toString() {
        return "Ideia [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", urgencia=" + urgencia + "]";
    }
}
