/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mk
 */
public class Principal {
    
    public static void main(String[] args) {
        PessoaDAO pdao = new PessoaDAO();
        
        Pessoa p1 = new Pessoa("Maria", 44);
        pdao.inserir(p1);
        Pessoa p2 = new Pessoa("Pedro", 20);
        pdao.inserir(p2);
        pdao.inserir(new Pessoa("Luiz", 12));
        pdao.inserir(new Pessoa("Luiz H", 12));
        pdao.listar();
        
        pdao.excluir(p2);
        
        pdao.listar();
        
        p1.setIdade(45);
        pdao.editar(p1);
        
        pdao.listar();
        
        Pessoa p = (Pessoa) pdao.pesquisar("Luiz");
        System.out.println("Achou = " + p);
    }
    
}
