/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContaBanco;

/**
 *
 * @author Arthur
 */
public class Main {
    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");
        ContaBanco c1 = new ContaBanco(0.00, true);
        ContaBanco c2 = new ContaBanco(0.00, true);
        c1.abrirConta("CC");
        c2.abrirConta("CP");
        
        
        
        String text = c1.toString();
        System.out.println(text);
        
        text = c2.toString();
        System.out.println(text);
        
        c1.depositar(300.50);
    }
}
