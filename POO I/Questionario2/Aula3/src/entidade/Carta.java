package entidade;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 08220186
 */
public class Carta {
    private String naipe;
    private int numero;
    private String cor;
    private boolean aberta; //true é a face pra cima

    public Carta(String naipe, String cor, int numero) { //construtor
        setNaipe(naipe);
        setCor(cor);
        setNumero(numero);
    }
    
    public void virar(){
        aberta = !aberta;
        
    }

    public String getNaipe() {
        return naipe;
    }

    
    private void setNaipe(String naipe) {
        if(naipe != null && (
           naipe.equalsIgnoreCase("copas") ||
           naipe.equalsIgnoreCase("paus") ||
           naipe.equalsIgnoreCase("espadas") ||
           naipe.equalsIgnoreCase("ouros"))){
            this.naipe = naipe;
        } else {
            this.naipe = null;
        }
        
    }

    public int getNumero() {
        return numero;
    }

    private void setNumero(int numero) {
        if(numero > 0 && numero <= 13){
            this.numero = numero;
        } else {
            this.numero = 0;
        }
        
    }

    public String getCor() {
        
        return cor;
    }

    private void setCor(String cor) {
        if( cor != null && (
            cor.equalsIgnoreCase("preto") ||
            cor.equalsIgnoreCase("vermelho"))){
            this.cor = cor;
        } else {        
            this.cor = null;   
        }
    }

    public boolean isAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    @Override
    public String toString() {
        return "Carta{" + "naipe=" + naipe + ", numero=" + numero + ", cor=" + cor + '}';
    }
    
    
    
}
