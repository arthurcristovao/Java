/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import jframe.telainicial.Home;


public class Main {

    public static void main(String args[]) {

        //INSERÇÃO EM MASSA:
        //veiculo();
        //fabricante();
        //fabricacao();
        cadastrarTudo();
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }
    
    private static void cadastrarTudo(){
        Thread thread = new Thread(() -> {
            new CadastrarEmMassa().cadastrarEmMassa();
        });
        thread.start();
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
