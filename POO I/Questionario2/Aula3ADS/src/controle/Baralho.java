package controle;

import entidade.Carta;
import java.util.Random;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mk
 */
public class Baralho {

    public static void main(String[] args) {
        
        Carta[] baralhoPaus = criarBaralho("paus");
//        mostrarBaralho(baralhoPaus);
//        Carta c = sortearCarta(baralhoPaus);
//        System.out.println("Carta sorteada = " + c);
//        System.out.println(baralhoPaus.length);
        Carta[] baralhoCopas = criarBaralho("copas");
        Carta[] baralhoOuros = criarBaralho("ouros");
        Carta[] baralhoEspadas = criarBaralho("espadas");
        Carta[] baralhoMisturado = embaralharCartas(baralhoPaus, baralhoCopas, baralhoOuros, baralhoEspadas);

        mostrarBaralho(baralhoMisturado);
    } //fecha main

    //O método é estático porque o main é estático
    public static Carta[] criarBaralho(String naipe) {
        Carta[] baralho = new Carta[13];
        if (naipe.equalsIgnoreCase("paus") || naipe.equalsIgnoreCase("espadas")) {
            for (int i = 0; i < 13; i++) {
                baralho[i] = new Carta(naipe, "preto", i + 1);
            }
        } else {
            if (naipe.equalsIgnoreCase("copas") || naipe.equalsIgnoreCase("ouros")) {
                for (int i = 0; i < 13; i++) {
                    baralho[i] = new Carta(naipe, "vermelho", i + 1);
                }
            } else {
                return null;
            }
        }
        return baralho;
    } // fechar criarBaralho

    public static void mostrarBaralho(Carta[] baralho) {
        if (baralho != null) {
            for (int i = 0; i < baralho.length; i++) {
                System.out.println(i + " " + baralho[i]);
            }
        } else {
            System.out.println("O baralho não foi criado !");
        }
    } //fecha mostrarBaralho
    
    public static Carta sortearCarta(Carta[] baralho){
        Random r = new Random();
        int i = r.nextInt(13);
        //System.out.println("numero sorteado = " + i);
        return baralho[i];
    }
    
    
    public static Carta[] embaralharCartas (Carta[] paus, Carta[] copas, Carta[] ouros, Carta[] espadas){
        Carta[] baralho = new Carta[52];
        // Desenvolver uma lógica para juntar os baralhos de modo a misturar as cartas
        int contBaralho = 0;
        for( int i = 0; i < 4; i++){
            for( int j = 0; j < 13; j++){
                if( i == 0 ){ 
                    baralho[contBaralho] = paus[j];
                    contBaralho++;
                } else if ( i == 1) {
                    baralho[contBaralho] = copas[j];
                    contBaralho++;
                } else if ( i == 2) {
                    baralho[contBaralho] = ouros[j];
                    contBaralho++;
                } else {
                    baralho[contBaralho] = espadas[j];
                    contBaralho++;
                }
            }
        }
        
        Carta[] baralhoMisturado = new Carta[52];
        int[] posRand = new int[52];
        Random r = new Random();
        int rand;
        
        //Gera 52 numeros aleatorios diferentes
        for( int i = 0; i< 52; i++){
            rand = r.nextInt(52);
            
            for(int j = 0; j < 52; j++ ){
                if(rand == posRand[j] && j != i){
                    rand = r.nextInt(52);
                } else {
                    posRand[i] = rand;
                }
            }
            //System.out.println("pos " + i + " tem o valor " + posRand[i]);
        }
        //embaralha
        for(int n = 0; n < 52; n++){
            baralhoMisturado[n] = baralho[posRand[n]];
        }

        return baralhoMisturado;
    } //fecha embaralhar
    
}

