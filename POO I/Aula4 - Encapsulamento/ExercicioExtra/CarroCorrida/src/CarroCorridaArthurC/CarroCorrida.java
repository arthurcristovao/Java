/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarroCorridaArthurC;

/**
 *
 * @author Arthur
 */
public class CarroCorrida {
    private int numeroCarro;
    private String piloto;
    private String equipe;
    private float velocidadeMaxima;
    private float velocidadeAtual;
    private boolean ligado;

    public CarroCorrida(int numeroCarro, String piloto, String equipe, float velocidadeMaxima) {
        this.numeroCarro = numeroCarro;
        this.piloto = piloto;
        this.equipe = equipe;
        this.velocidadeMaxima = velocidadeMaxima;
    }
    
    public int getNumeroCarro() {
        return numeroCarro;
    }

    public void setNumeroCarro(int numeroCarro) {
        this.numeroCarro = numeroCarro;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }
    
    
    public float getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(float velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public float getVelocidadeAtual() {
        return velocidadeAtual;
    }

    public void setVelocidadeAtual(float velocidadeAtual) {
        this.velocidadeAtual = velocidadeAtual;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    @Override
    public String toString() {
        return "CarroCorrida{" + "numeroCarro=" + numeroCarro + ", piloto=" + piloto + ", equipe=" + equipe + ", velocidadeMaxima=" + velocidadeMaxima + ", velocidadeAtual=" + velocidadeAtual + ", ligado=" + ligado + '}';
    }
    
    
    
    public void acelerar(float aumentar){
        if(isLigado() == true){
            if((getVelocidadeAtual() + aumentar) <= getVelocidadeMaxima()){
                setVelocidadeAtual(getVelocidadeAtual() + aumentar);
                System.out.println("Sua velocidade atual esta em "+ getVelocidadeAtual() + "km/h");
            } else {
                System.out.println("Voce nao pode ultrapassar " + getVelocidadeMaxima() + "km/h");
                setVelocidadeAtual(getVelocidadeMaxima());
                System.out.println("Voce esta a " + getVelocidadeMaxima() + "km/h");
            }
        } else {
            System.out.println("O carro esta desligado... voce precisar ligar antes");
        }
        //System.out.println(getVelocidadeAtual());
        
    }
    
    public void frear(float diminuirPorcentagem){
        
        if(isLigado() == true){
            diminuirPorcentagem = (getVelocidadeAtual() * diminuirPorcentagem) / 100;
            
            //verifica numeros negativos
            if( diminuirPorcentagem <= 0){
                setVelocidadeAtual(0);
            } else{
                setVelocidadeAtual(diminuirPorcentagem);
                //System.out.println(diminuirPorcentagem);
            }
            
            //frear
            if(getVelocidadeAtual() <= 0){
                parar();
            } else {
                System.out.println("Diminuiu... sua velocidade esta em " + getVelocidadeAtual() + "km/h");
            }
        } else {
            System.out.println("O carro esta desligado... voce precisar ligar antes");
        }   
    }
    
    public void parar(){
        //System.out.println(getVelocidadeAtual());
        System.out.println("Voce parou o carro");
    }
    
    public void ligar(){
        setLigado(true);
        System.out.println("Voce ligou o carro");
    }
    
    public void desligar(){
        if(getVelocidadeAtual() <= 0){
            setLigado(false);
            System.out.println("Voce desligou o carro");
        }else {        
            System.out.println("Voce precisa parar o carro para poder desligar...");
        }
}
    
// Arthur Cristovão
// POO I - ADS - IFRS Campus Osório
    
}
