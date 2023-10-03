
package servico;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import pessoa.*;
import produto.Produto;


public class Servico {
    private static final AtomicInteger id = new AtomicInteger(0);
    private int codServico;
    private LocalDate dataServico;
    private Cliente cliente;
    private Funcionario atendente;
    private Funcionario mecanico;
    private ArrayList<ServicoItem> peca = new ArrayList<>();
    private Double valorTotalServico;

    public Servico(LocalDate dataServico, Cliente cliente, Funcionario atendente, Funcionario mecanico) {
        this.codServico = generateID();
        this.dataServico = dataServico;
        this.cliente = cliente;
        this.atendente = atendente;
        this.mecanico = mecanico;
        this.valorTotalServico = 0.0;
    }

    public Servico(LocalDate dataServico, Cliente cliente, Funcionario atendente, Funcionario mecanico, Produto produto, Double quantidadeProduto) {
        this(dataServico, cliente, atendente, mecanico);
        this.peca.add(new ServicoItem(produto, quantidadeProduto, produto.getPrecoVenda()));
        this.valorTotalServico += (quantidadeProduto * produto.getPrecoVenda());
    }

    public static int generateID() {
        return id.incrementAndGet();
    }

    public int getCodServico() {
        return codServico;
    }

    public LocalDate getDataServico() {
        return dataServico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getAtendente() {
        return atendente;
    }

    public void setAtendente(Funcionario atendente) {
        this.atendente = atendente;
    }

    public Funcionario getMecanico() {
        return mecanico;
    }

    public void setMecanico(Funcionario mecanico) {
        this.mecanico = mecanico;
    }

    public ArrayList<ServicoItem> getPecas() {
        return peca;
    }

    public Double getValorTotalServico() {
        return valorTotalServico;
    }

    public boolean setPeca(ServicoItem item) {
        try {
            this.peca.add(item);
            this.valorTotalServico += item.getValorTotal();
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public void calculaTotalServico(){
        double valorT = 0.0;
        for(ServicoItem item : this.peca){
            valorT+=item.getValorTotal();
        }
        this.valorTotalServico = valorT;
    }

    @Override
    public String toString() {
        return "Servico{" + "codServico=" + codServico + ", dataServico=" + dataServico + ", cliente=" + cliente + ", atendente=" + atendente + ", mecanico=" + mecanico + ", peca=" + peca + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.codServico;
        hash = 41 * hash + Objects.hashCode(this.dataServico);
        hash = 41 * hash + Objects.hashCode(this.cliente);
        hash = 41 * hash + Objects.hashCode(this.atendente);
        hash = 41 * hash + Objects.hashCode(this.mecanico);
        hash = 41 * hash + Objects.hashCode(this.peca);
        hash = 41 * hash + Objects.hashCode(this.valorTotalServico);
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
        final Servico other = (Servico) obj;
        if (this.codServico != other.codServico) {
            return false;
        }
        if (!Objects.equals(this.dataServico, other.dataServico)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.atendente, other.atendente)) {
            return false;
        }
        if (!Objects.equals(this.mecanico, other.mecanico)) {
            return false;
        }
        if (!Objects.equals(this.peca, other.peca)) {
            return false;
        }
        return Objects.equals(this.valorTotalServico, other.valorTotalServico);
    }

    
    
}
