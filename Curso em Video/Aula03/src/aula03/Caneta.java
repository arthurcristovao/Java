/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula03;

/**
 *
 * @author Arthur
 */
public class Caneta {
    public String modelo; //atributos
    public String cor;
    private float ponta;
    protected int carga;
    private boolean tampada;
    
    public void status(){ //metodo
        System.out.println("Uma caneta " + this.cor);
        System.out.println("Esta tampada? " + this.tampada);
        System.out.println("Ponta: " + this.ponta);
        System.out.println("Carga: " + this.carga);
        System.out.println("Esta tampada? " + this.tampada);
        
    }
    
    public void rabiscar(){
        if(this.tampada == true){
            System.out.println("Erro! Não posso rabiscar");
        } else {
            System.out.println("Estou rabiscando...");
        }
    }
    
    protected void tampar(){
        this.tampada = true;
    }
    
    protected void destampar(){
                this.tampada = false;
    }
    
}
