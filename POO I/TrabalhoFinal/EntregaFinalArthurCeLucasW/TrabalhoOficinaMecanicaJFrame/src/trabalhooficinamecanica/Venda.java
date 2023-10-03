
package trabalhooficinamecanica;

import java.util.Date;
import java.util.Objects;
import pessoa.*;
import produto.Produto;


public class Venda {
    private int codVenda;
    private Date dataVenda;
    private Cliente cliente;
    private Funcionario atendente;
    private Produto peca;

    public Venda(int codVenda, Date dataVenda, Funcionario atendente, Produto peca) {
        this.codVenda = codVenda;
        this.dataVenda = new Date();
        this.atendente = atendente;
        this.peca = peca;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCodVenda() {
        return codVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public Funcionario getAtendente() {
        return atendente;
    }

    public Produto getPeca() {
        return peca;
    }

    @Override
    public String toString() {
        return "Venda{" + "codVenda=" + codVenda + ", dataVenda=" + dataVenda + ", cliente=" + cliente + ", atendente=" + atendente + ", peca=" + peca + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.codVenda;
        hash = 41 * hash + Objects.hashCode(this.dataVenda);
        hash = 41 * hash + Objects.hashCode(this.cliente);
        hash = 41 * hash + Objects.hashCode(this.atendente);
        hash = 41 * hash + Objects.hashCode(this.peca);
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
        final Venda other = (Venda) obj;
        if (this.codVenda != other.codVenda) {
            return false;
        }
        if (!Objects.equals(this.dataVenda, other.dataVenda)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.atendente, other.atendente)) {
            return false;
        }
        return Objects.equals(this.peca, other.peca);
    }
    
    
}
