/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula04;

/**
 *
 * @author Arthur
 */
public class Caneta {
    private String modelo; //atributos
    private float ponta;
    private String cor;
    private boolean tampada;

    //construtor

    public Caneta(String modelo, float ponta, String cor) {
        this.modelo = modelo;
        this.ponta = ponta;
        this.cor = cor;
    }
    
    
    public String getModelo() { //getter
        return modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public boolean isTampada() {
        return tampada;
    }

    public void setTampada(boolean tampada) {
        this.tampada = tampada;
    }

    public float getPonta() {
        return ponta;
    }

    public void setModelo(String modelo) { //setter
        this.modelo = modelo;
    }

    public void setPonta(float ponta) {
        this.ponta = ponta;
    }

    public void tampar(){
        this.tampada = false;
    }
    
    public void destampar() {
        this.tampada = false;
    }

    @Override
    public String toString() {
        return "Caneta{" + "modelo=" + modelo + ", ponta=" + ponta + ", cor=" + cor + ", tampada=" + tampada + '}';
    }
    
    
    
    
}
