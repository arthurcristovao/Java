/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula02;

/**
 *
 * @author Arthur
 */
public class Caneta {
    String modelo; //atributos
    String cor;
    float ponta;
    int carga;
    boolean tampada;
    
    void status(){ //metodo
        System.out.println("Uma caneta " + this.cor);
        System.out.println("Esta tampada? " + this.tampada);
        System.out.println("Ponta: " + this.ponta);
        System.out.println("Carga: " + this.carga);
        System.out.println("Esta tampada? " + this.tampada);
        
    }
    
    void rabiscar(){
        if(this.tampada == true){
            System.out.println("Erro! Não posso rabiscar");
        } else {
            System.out.println("Rabiscou...");
        }
    }
    
    void tampar(){
        this.tampada = true;
    }
    
    void destampar(){
                this.tampada = false;
    }
    
}
