package br.edu.ifrs.osorio.projetobiblioteca;




import java.time.LocalDate;

public class Livro {
    private int id;
    private String titulo;
    private int paginasLidas;
    private int paginas;
    private StatusLivro status;
    private String emailUsuario;
    private LocalDate dataReserva;

    public Livro(int id, String titulo, int paginas, int paginasLidas, StatusLivro status, String emailUsuario, LocalDate dataLocalDate) {
        this.id = id;
        this.titulo = titulo;
        this.paginas = paginas;
        this.paginasLidas = paginasLidas;
        this.status = status;
        this.emailUsuario = emailUsuario;
        this.dataReserva = dataLocalDate;
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

    public int getPaginasLidas() {
        return paginasLidas;
    }

    public void setPaginasLidas(int paginasLidas) {
        this.paginasLidas = paginasLidas;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public void setStatus(StatusLivro status) {
        this.status = status;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDate dataReserva) {
        this.dataReserva = dataReserva;
    }
    
    

    

    public void ler(int pags) {
        if (this.paginasLidas + pags < this.paginas) {
            paginasLidas += pags;
        } else {
            this.paginasLidas = this.paginas;
        }
    }

    public void reservar(String email) {
        if (this.status == StatusLivro.DISPONIVEL) {
            this.status = StatusLivro.RESERVADO;
            this.emailUsuario = email;
        }
    }

    public void locar(String email) {
        if (this.status == StatusLivro.DISPONIVEL || (this.status == StatusLivro.RESERVADO && this.emailUsuario.equals(email))) {
            this.status = StatusLivro.LOCADO;
            this.emailUsuario = email;
        }
    }

    public void devolver() {
        if (this.status == StatusLivro.LOCADO) {
            this.status = StatusLivro.DISPONIVEL;
            this.emailUsuario = null;
        }
    }
}