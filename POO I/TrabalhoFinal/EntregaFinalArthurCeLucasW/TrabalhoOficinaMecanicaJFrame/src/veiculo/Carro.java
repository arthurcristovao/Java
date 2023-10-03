package veiculo;

public class Carro extends Veiculo{
    private int numeroPortas;
    private boolean arCondicionado;

    public Carro(int numeroPortas, boolean arCondicionado, String marca, String modelo, int anoFabricacao, String placa, Cor cor) {
        super(marca, modelo, anoFabricacao, placa, cor);
        this.numeroPortas = numeroPortas;
        this.arCondicionado = arCondicionado;
    }

    public int getNumeroPortas() {
        return numeroPortas;
    }

    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }

    public boolean isArCondicionado() {
        return arCondicionado;
    }

    public void setArCondicionado(boolean arCondicionado) {
        this.arCondicionado = arCondicionado;
    }
    
    @Override
    public String toString() {
        return "Carro{" + "marca=" + super.getMarca() + ", modelo=" + super.getModelo() + ", anoFabricacao=" + super.getAnoFabricacao() + ", placa=" + super.getPlaca() + ", cor=" + super.getCor() + ", numeroPortas=" + numeroPortas + ", arCondicionado=" + arCondicionado + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.numeroPortas;
        hash = 47 * hash + (this.arCondicionado ? 1 : 0);
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
        final Carro other = (Carro) obj;
        if (this.numeroPortas != other.numeroPortas) {
            return false;
        }
        return this.arCondicionado == other.arCondicionado;
    }
    
    
    
}
