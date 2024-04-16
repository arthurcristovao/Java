/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import jframe.telainicial.Home;


public class Main {
    
   /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //INSERÇÃO EM MASSA:
        
        //veiculo();
        //fabricante();
        //fabricacao();
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }
    
    
    private static void veiculo(){
        Thread thread = new Thread(() -> {
            new CadastrarEmMassa().cadastrarVeiculoEmMassa();
        });
        thread.start();
    }
    
    private static void fabricante(){
        Thread thread = new Thread(() -> {
            new CadastrarEmMassa().cadastrarFabricanteEmMassa();
        });
        thread.start();
    }
    
    private static void fabricacao(){
        Thread thread = new Thread(() -> {
            new CadastrarEmMassa().cadastrarFabricacaoEmMassa();
        });
        thread.start();
    }
    
}
