package pacote;

class Moto {
    private final String modelo;
    private double velocidade;
    private static final int VEL_FINAL = 150;
    private static final double TAMANHO_TAQUE = 30;
    private double gasolina;

    public Moto(String modelo) {
        this.modelo = modelo;
        this.velocidade = 0;
        this.gasolina = 0;
    }

    public String getModelo() {
        return this.modelo;
    }

    public double getVelocidade() {
        return this.velocidade;
    }

    public double getGasolina() {
        return this.gasolina;
    }

    public void abastecer(double quantidade) {
        if (quantidade < 0) 
            throw new IllegalArgumentException("Quantidade de gasolina não pode ser negativa");
        
        this.gasolina += quantidade;
        
        if (this.gasolina > TAMANHO_TAQUE) 
            this.gasolina = TAMANHO_TAQUE;
        
    }

    void acelera(int vel) {
        if (vel < 0)
            throw new IllegalArgumentException("A velocidade deve ser positiva");

        double consumo = vel * 0.01;

        if (this.gasolina <= 0) {
            System.out.println("Tanque vazio. Não é possível acelerar.");
            this.velocidade = 0; 
            return;
        }

        if (this.gasolina < consumo) {
            this.velocidade += this.gasolina / 0.01;
            this.gasolina = 0;
        } else {
            this.velocidade += vel;
            this.gasolina -= consumo;
        }

        if (this.velocidade > VEL_FINAL)
            this.velocidade = VEL_FINAL;
    }


    void freia(int vel) {
        if (vel < 0)
            throw new IllegalArgumentException("A velocidade deve ser positiva");
        if (this.velocidade - vel < 0)
            this.velocidade = 0;
        else
            this.velocidade -= vel;
    }
}
