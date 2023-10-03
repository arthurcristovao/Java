/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula04;

/**
 *
 * @author Arthur
 */
public class Main {
    public static void main(String[] args) {
        Caneta c1 = new Caneta("BIC", 0.4f, "Amarelo");
        
        String texto = c1.toString();
        System.out.println(texto);
    }
}
