public class Bola{
     String marca;
     double tamanho;
     String tipoEsporte;
     public Bola (String esporte, double tamanho){
         this.tipoEsporte = esporte;
         this.tamanho = tamanho;
     }
     public Bola (String marca, String esporte, double tamanho) {
          this (esporte, tamanho);
          this.marca = marca;
     }
     public Bola (String marca, String esporte){
            this.marca = marca;
            this.tipoEsporte = esporte;
     }
     public void mostrar(){
             System.out.println("Esporte = " + tipoEsporte + ", Marca = " + marca + ", Tamanho = " + tamanho);
     }
}
public class Principal{
    public static void main (String[] args){
    Bola bola2 = new Bola ("Nike", "basquete", 12.0);
    bola2.mostrar();
    }
} 