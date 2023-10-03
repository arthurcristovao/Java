/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 08220186
 */

//psvm <TAB> cria main function
//sout <TAB> cria print

public class TesteCodigo {
    
    public static void main(String[] args) {
        ElementoQuimico hidrogenio = new ElementoQuimico();
             System.out.println("Dados do elemento quimico "); 
             hidrogenio.exibirDados();
             hidrogenio.colunaTabela = 1;
             hidrogenio.linhaTabela = 1;
             hidrogenio.nome = "Hidrogenio";
             hidrogenio.simbolo = "H";
             hidrogenio.numeroAtomico = 1;
             hidrogenio.tipo = "Nao metal";
             
             System.out.println("\nDados do elemento quimico "); 
             hidrogenio.exibirDados();
    }
    
}
