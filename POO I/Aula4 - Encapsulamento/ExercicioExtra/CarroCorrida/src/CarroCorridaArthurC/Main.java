/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarroCorridaArthurC;

/**
 *
 * @author Arthur
 */
public class Main { 
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        
        CarroCorrida c1 = new CarroCorrida(1, "Joao", "Mercedes", 120);
    
        
        //System.out.println(c1.toString());
        c1.acelerar(10);
        c1.frear(50);
        c1.ligar();
        c1.acelerar(10);
        c1.acelerar(10);
        c1.acelerar(75);
        c1.acelerar(40);
        
        //System.out.println("\n\n");
        c1.desligar();
        c1.frear(50); //80 -> diminuir para 80% == diminuir 20%
        //c1.frear(0.2f);
        c1.frear(0);
        c1.desligar();

        //c1.acelerar(101);
        
    }
    
    
    
}
