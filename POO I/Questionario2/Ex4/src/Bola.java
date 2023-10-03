/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Arthur
 */
public class Bola{
     String marca;
     double tamanho;
     String tipoEsporte;
     public Bola (String esporte, double tamanho){
         this.tipoEsporte = esporte;
         this.tamanho = tamanho;
     }
     public Bola (String marca, String esporte, double tamanho) {
          this (esporte, tamanho);
          this.marca = marca;
     }
     public Bola (String marca, String esporte){
            this.marca = marca;
            this.tipoEsporte = esporte;
     }
     public void mostrar(){
             System.out.println("Esporte = " + tipoEsporte + ", Marca = " + marca + ", Tamanho = " + tamanho);
     }
}