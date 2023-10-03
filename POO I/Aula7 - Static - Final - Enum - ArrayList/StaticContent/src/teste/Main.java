/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author Arthur
 */
public class Main {
    static Integer[] dezenas = new Integer[10];
    static {
        for (int i = 0; i < 10; i++) {
            dezenas[i] = (i+1)*10;
        }
    }
    
    public static void main(String[] args) {
        //System.out.println(Test.i);
        for (int i = 0; i < 10; i++) {
            System.out.println(Main.dezenas[i]);
        }
        
    }
}
