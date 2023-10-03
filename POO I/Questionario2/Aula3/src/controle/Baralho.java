package controle;


import entidade.Carta;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 08220186
 */
public class Baralho {

    public static void main(String[] args) {
//        Carta c1 = new Carta("paus", "preto", 9);
//        System.out.println("Carta Cor = " + c1.getCor() + "\nNaipe = " + c1.getNaipe() + "\nNumero = " + c1.getNumero() + "\n");
//        
//        Carta c2 = new Carta("copas", "vermelho", 10);
//        System.out.println("Carta Cor = " + c2.getCor() + "\nNaipe = " + c2.getNaipe() + "\nNumero = " + c2.getNumero() + "\n");
//        
//        Carta c3 = new Carta("paus", "azul", 13);
//        System.out.println("Carta Cor = " + c3.getCor() + "\nNaipe = " + c3.getNaipe() + "\nNumero = " + c3.getNumero() + "\n");

        Carta[] baralhoPaus = criarBaralho("paus");
        Carta[] baralhoCopas = criarBaralho("copas");
        Carta[] baralhoOuros = criarBaralho("ouros");
        Carta[] baralhoEspadas = criarBaralho("espadas");
        
        mostrarBaralho(baralhoPaus);
        
        Carta x = sortearCarta(baralhoPaus);

        System.out.println("Carta sorteada foi " + x);
        
        Carta[] baralhoMisturado = embaralharCartas(baralhoPaus, baralhoCopas, baralhoOuros, baralhoEspadas);

        mostrarBaralho(baralhoMisturado);
        
    }

    public static Carta[] criarBaralho(String naipe) {
        Carta[] baralho = new Carta[13];
        if (naipe.equalsIgnoreCase("copas")
            || naipe.equalsIgnoreCase("ouros")) {
            for (int i = 0; i < 13; i++) {
                baralho[i] = new Carta(naipe, "vermelho", i+1);
            }
        } else if (naipe.equalsIgnoreCase("espadas")
                || naipe.equalsIgnoreCase("paus")) {
            for (int i = 0; i < 13; i++) {
                baralho[i] = new Carta(naipe, "preto", i+1);
            }
        } else {
            return null;
        }
        return baralho;
    }

    public static void mostrarBaralho(Carta[] baralho) {
        if (baralho != null) {
            for (int i = 0; i < 13; i++) {
                System.out.println(baralho[i]);
            }
        }
    }
    
    public static Carta sortearCarta(Carta[] baralho){
        Random rand = new Random();
        int i = rand.nextInt(13);
        System.out.println("O numero sorteado foi " + i);
        return baralho[i];
    } 

}
