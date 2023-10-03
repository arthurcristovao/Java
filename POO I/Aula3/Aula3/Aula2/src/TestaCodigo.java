/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 08220186
 */
public class TestaCodigo {
    public static void main(String[] args) {
//        Porta p1 = new Porta();
//        
//        p1.altura = 2.2;
//        p1.largura = 0.8;
//        p1.espessura = 0.03;
//        p1.estado = false;
//        p1.cor = "Natural";

        Porta p1 = new Porta(2.2, 0.8, 0.03, "Azul");
        Porta p2 = new Porta(2.2, 0.8, 0.03);
        
        p1.abrir();
        System.out.println("A porta esta aberta? " + p1.estado);
        
        p1.fechar();
        System.out.println("A porta esta aberta? " + p1.estado);
        
        System.out.println("Qual a cor da porta? " + p1.cor);
        p1.pintar("Branca");
        System.out.println("Qual a nova cor da porta? " + p1.cor);
        
        
        System.out.println("Qual a cor da porta? " + p2.cor);
        
        //Essa a maneira de copiar o endereço 
        //Porta p4 = p2; 
        
        //Como duplicar os objetos de uma instancia, com uma nova instancia de memória
        Porta p4 = p2.clonar();
        System.out.println("Cor da P4 " + p4.cor);
        p4.cor = "Azul";
        System.out.println("Cor da P4 " + p4.cor);
        System.out.println("Cor da P2 " + p2.cor);
        
        //quebra o ponteiro de ligação com área de memoria que armazena a instancia
        p2 = null;
        
    }
}
