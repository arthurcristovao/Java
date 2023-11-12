package aluguel;

import carro.*;
import cliente.*;
import java.time.LocalDate;

public class Aluguel {
    private int idAluguel;
    private Cliente cliente;
    private Carro carro;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;

    public Aluguel(int idAluguel, Cliente cliente, Carro carro, LocalDate dataAluguel, LocalDate dataDevolucao) {
        this.idAluguel = idAluguel;
        this.cliente = cliente;
        this.carro = carro;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public Aluguel(Cliente cliente, Carro carro, LocalDate dataAluguel, LocalDate dataDevolucao) {
        this.cliente = cliente;
        this.carro = carro;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public Aluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }
    
    

    public int getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    
    
    
}