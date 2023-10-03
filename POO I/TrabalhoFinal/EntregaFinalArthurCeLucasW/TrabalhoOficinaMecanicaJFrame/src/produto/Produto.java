package produto;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import veiculo.*;

public class Produto {
    private static final AtomicInteger id = new AtomicInteger(0);
    private int codProduto;
    private String nome;
    private String marca;
    private String modelo;
    private String unidade;
    private int anoFabricacao;
    private double precoVenda;

    public Produto(String nome, String marca, String modelo, String unidade, int anoFabricacao, double precoVenda) {
        this.codProduto = generateID();
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.unidade = unidade;
        this.anoFabricacao = anoFabricacao;
        this.precoVenda = precoVenda;
    }

    public static int generateID() {
        return id.incrementAndGet();
    }

    public int getCodProduto(){
        return this.codProduto;
    }
    
    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getUnidade() {
        return unidade;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }
    
    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.codProduto;
        hash = 23 * hash + Objects.hashCode(this.nome);
        hash = 23 * hash + Objects.hashCode(this.marca);
        hash = 23 * hash + Objects.hashCode(this.modelo);
        hash = 23 * hash + Objects.hashCode(this.unidade);
        hash = 23 * hash + this.anoFabricacao;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.precoVenda) ^ (Double.doubleToLongBits(this.precoVenda) >>> 32));
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
        final Produto other = (Produto) obj;
        if (this.codProduto != other.codProduto) {
            return false;
        }
        if (this.anoFabricacao != other.anoFabricacao) {
            return false;
        }
        if (Double.doubleToLongBits(this.precoVenda) != Double.doubleToLongBits(other.precoVenda)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        return Objects.equals(this.unidade, other.unidade);
    }

    

    
    
}
