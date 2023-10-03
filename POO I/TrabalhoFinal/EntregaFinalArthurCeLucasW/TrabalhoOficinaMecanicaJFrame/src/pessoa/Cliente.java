package pessoa;

import java.time.LocalDate;
import java.util.Objects;

import veiculo.Carro;
import veiculo.Veiculo;

public class Cliente extends Pessoa {
    private LocalDate dataCadastro;
    private LocalDate ultimaCompra;
    private Carro carro;

    public Cliente(LocalDate dataCadastro, Carro carro, String nome, String cpf, LocalDate dataNascimento) {
        super(nome, cpf, dataNascimento);
        this.dataCadastro = dataCadastro;
        this.carro = carro;
    }

    public Cliente(LocalDate dataCadastro, Carro carro, String nome, String cpf, LocalDate dataNascimento, String telefone) {
        super(nome, cpf, dataNascimento, telefone);
        this.dataCadastro = dataCadastro;
        this.carro = carro;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public LocalDate getUltimaCompra() {
        return ultimaCompra;
    }

    public void setUltimaCompra(LocalDate ultimaCompra) {
        this.ultimaCompra = ultimaCompra;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    @Override
    public String toString() {
        return "Cliente{" + "dataCadastro=" + dataCadastro + ", ultimaCompra=" + ultimaCompra + ", /nveiculo=" + carro + "\n" + super.toString() + '}';
    }    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.dataCadastro);
        hash = 97 * hash + Objects.hashCode(this.ultimaCompra);
        hash = 97 * hash + Objects.hashCode(this.carro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.dataCadastro, other.dataCadastro)) {
            return false;
        }
        if (!Objects.equals(this.ultimaCompra, other.ultimaCompra)) {
            return false;
        }
        return Objects.equals(this.carro, other.carro);
    }
    
    
}
