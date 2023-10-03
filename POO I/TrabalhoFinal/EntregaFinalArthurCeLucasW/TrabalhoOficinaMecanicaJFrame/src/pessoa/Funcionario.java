package pessoa;

import java.time.LocalDate;
import java.util.Objects;

public class Funcionario extends Pessoa{
    private Cargo cargo;
    private double salario;

    public Funcionario(Cargo cargo, double salario, String nome, String cpf, LocalDate dataNascimento, String telefone) {
        super(nome, cpf, dataNascimento, telefone);
        this.cargo = cargo;
        this.salario = salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "cargo=" + cargo + ", salario=" + salario + super.toString() +'}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.cargo);
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.salario) ^ (Double.doubleToLongBits(this.salario) >>> 32));
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
        final Funcionario other = (Funcionario) obj;
        if (Double.doubleToLongBits(this.salario) != Double.doubleToLongBits(other.salario)) {
            return false;
        }
        return this.cargo == other.cargo;
    }
    
    
}
