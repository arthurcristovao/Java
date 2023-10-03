package entidade;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mk
 */
public class Carta {
    
     private String naipe;
     private String cor;
     private int numero;
     private boolean aberta; //true é face para cima

    public Carta(String naipe, String cor, int numero) {
        setNaipe(naipe);
        setCor(cor);
        setNumero(numero);
    }

    public void virar(){
        aberta = !aberta;
    } 
    
//    public static void main(String[] args) {
//        Carta c = new Carta();
//        c.virar();
//        System.out.println(c.aberta);
//        c.virar();
//                System.out.println(c.aberta);
//
//    }

    /**
     * @return the naipe
     */
    public String getNaipe() {
        return naipe;
    }

    /**
     * @param naipe the naipe to set
     */
    private void setNaipe(String naipe) {
        if (naipe != null && (
            naipe.equalsIgnoreCase("copas") || 
            naipe.equalsIgnoreCase("paus") ||
            naipe.equalsIgnoreCase("espadas") ||
            naipe.equalsIgnoreCase("ouros"))) {
                this.naipe = naipe;
        } else {
            this.naipe = "erro";
        }
            
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    private void setCor(String cor) {
        if (cor != null && (
            cor.equalsIgnoreCase("preto") || 
            cor.equalsIgnoreCase("vermelho"))){
                this.cor = cor;
        } else {
            this.cor = "erro";
        }
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    private void setNumero(int numero) {
        if (numero >0 && numero <= 13){
            this.numero = numero;
        } else {
            this.numero = 0;
        }
    }

    /**
     * @return the aberta
     */
    public boolean isAberta() {
        return aberta;
    }

    /**
     * @param aberta the aberta to set
     */
    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    @Override
    public String toString() {
        return "Carta{" + "naipe=" + naipe + ", cor=" + cor + ", numero=" + numero + ", aberta=" + aberta + '}';
    }
    
    
    
}


