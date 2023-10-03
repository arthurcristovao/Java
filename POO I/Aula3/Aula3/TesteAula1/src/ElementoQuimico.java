/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 08220186
 */
public class ElementoQuimico {
    String tipo;
    String simbolo;
    int numeroAtomico;
    String nome;
    int linhaTabela;
    int colunaTabela;
    
    
    void exibirDados(){
        System.out.println("Tipo = " + tipo);
        System.out.println("Simbolo = " + simbolo);
        System.out.println("Numero Atomico = " + numeroAtomico);
        System.out.println("Nome = " + nome);
        System.out.println("Linha da Tabela = " + linhaTabela);
        System.out.println("Coluna da Tabela = " + colunaTabela);


    }
}
