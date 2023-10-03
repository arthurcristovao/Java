/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mk
 */
public class CarroCorrida {
    private int numeroCarro;
    private Piloto piloto;
    private String nomeEquipe;
    private float velocidadeMaxima;
    private float velocidadeAtual;
    private boolean ligado;
    private Motor motor;

    public CarroCorrida(int numeroCarro, String nomeEquipe, float velocidadeMaxima, Motor motor) {
        this.numeroCarro = numeroCarro;
        this.nomeEquipe = nomeEquipe;
        this.velocidadeMaxima = velocidadeMaxima;
        this.motor = motor;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }
    
    /**
     * @return the numeroCarro
     */
    public int getNumeroCarro() {
        return numeroCarro;
    }

    /**
     * @param numeroCarro the numeroCarro to set
     */
    public void setNumeroCarro(int numeroCarro) {
        this.numeroCarro = numeroCarro;
    }

    /**
     * @return the piloto
     */
    public Piloto getPiloto() {
        return piloto;
    }

    /**
     * @param piloto the piloto to set
     */
    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    /**
     * @return the nomeEquipe
     */
    public String getNomeEquipe() {
        return nomeEquipe;
    }

    /**
     * @param nomeEquipe the nomeEquipe to set
     */
    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    /**
     * @return the velocidadeMaxima
     */
    public float getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    /**
     * @param velocidadeMaxima the velocidadeMaxima to set
     */
    public void setVelocidadeMaxima(float velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    /**
     * @return the velocidadeAtual
     */
    public float getVelocidadeAtual() {
        return velocidadeAtual;
    }

    /**
     * @param velocidadeAtual the velocidadeAtual to set
     */
    public void setVelocidadeAtual(float velocidadeAtual) {
        this.velocidadeAtual = velocidadeAtual;
    }

    /**
     * @return the ligado
     */
    public boolean isLigado() {
        return ligado;
    }

    /**
     * @param ligado the ligado to set
     */
    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    @Override
    public String toString() {
        return "CarroCorrida{" + "numeroCarro=" + numeroCarro + ", piloto=" + piloto + ", nomeEquipe=" + nomeEquipe + ", velocidadeMaxima=" + velocidadeMaxima + ", velocidadeAtual=" + velocidadeAtual + ", ligado=" + ligado + '}';
    }
    
    
    
}
