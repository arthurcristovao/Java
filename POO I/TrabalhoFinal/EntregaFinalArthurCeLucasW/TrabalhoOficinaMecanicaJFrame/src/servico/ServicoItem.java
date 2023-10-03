package servico;

import java.util.Objects;
import produto.Produto;

public class ServicoItem {
    private Produto produto;
    private Double quantidade;
    private Double valorUnit;
    private Double valorTotal;

    public ServicoItem(Produto produto, Double quantidade, Double valorUnit){
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        calculaTotal();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
        calculaTotal();
    }

    public Double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(Double valorUnit) {
        this.valorUnit = valorUnit;
        calculaTotal();
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void calculaTotal(){
        if(!(quantidade == null || valorUnit == null))
            this.valorTotal = this.quantidade * this.valorUnit;
        else
         this.valorTotal = 0.0; 
    }

    @Override
    public String toString() {
        return "ServicoItem{" + "produto=" + produto + ", quantidade=" + quantidade + ", valorUnit=" + valorUnit + ", valorTotal=" + valorTotal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.produto);
        hash = 89 * hash + Objects.hashCode(this.quantidade);
        hash = 89 * hash + Objects.hashCode(this.valorUnit);
        hash = 89 * hash + Objects.hashCode(this.valorTotal);
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
        final ServicoItem other = (ServicoItem) obj;
        if (!Objects.equals(this.produto, other.produto)) {
            return false;
        }
        if (!Objects.equals(this.quantidade, other.quantidade)) {
            return false;
        }
        if (!Objects.equals(this.valorUnit, other.valorUnit)) {
            return false;
        }
        return Objects.equals(this.valorTotal, other.valorTotal);
    }
    
    
    
}
