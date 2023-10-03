/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mk
 */
public class Testador {
    
    public static void main(String[] args) {
        Motor motor = new Motor("ferrari", 1000, 100);
            CarroCorrida ferrari = new CarroCorrida(1000, "Ferrari", 300, motor);
            Piloto piloto = new Piloto("Jack", 23);
            ferrari.setPiloto(piloto);
            
            
            String nome = new Console().input("Digite o nome do piloto");
            String idade = new Console().input("Digite a idade");
            int i = Integer.parseInt(idade); // Wapper Classes
            Piloto piloto2 = new Piloto(nome, i);
            new Console().output("Você digitou " + piloto2, 0);
                    
            
     
            
           
        }
    
}
